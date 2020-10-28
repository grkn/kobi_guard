package com.kobiguard.app.service;

import com.kobiguard.app.entity.User;
import com.kobiguard.app.exception.UserNotFoundException;
import com.kobiguard.app.repository.AddressRepository;
import com.kobiguard.app.repository.UserRepository;
import com.kobiguard.app.resources.UserResource;
import com.kobiguard.app.security.SecurityService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final SecurityService securityService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AddressRepository addressRepository,
                       SecurityService securityService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.securityService = securityService;
    }

    @Transactional
    public User createUser(User user) {
        addressRepository.saveAll(user.getAddresses());
        user.getAddresses().forEach(address -> address.setUser(user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);

        securityService.createUserAccessToken(createdUser);

        return createdUser;
    }

    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found by given id : %s", userId)));
    }

    @Transactional
    public User updateUser(User user, String userId) {
        User persistedUser = findUserById(userId);
        persistedUser.setyCoordinates(user.getyCoordinates());
        persistedUser.setxCoordinates(user.getxCoordinates());
        persistedUser.setUserType(user.getUserType());
        persistedUser.setName(user.getName());
        persistedUser.setLastName(user.getLastName());
        persistedUser.setEmail(user.getEmail());
        if (!persistedUser.getNickName().equals(user.getNickName()) || !persistedUser.getPassword().equals(user.getPassword())) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            securityService.updateNickNameAndPassword(user.getNickName(), encodedPassword, persistedUser.getNickName());
            persistedUser.setNickName(user.getNickName());
            persistedUser.setPassword(encodedPassword);
        }
        return userRepository.save(persistedUser);
    }
}
