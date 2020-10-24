package com.kobiguard.app.service;

import com.kobiguard.app.entity.Address;
import com.kobiguard.app.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    private final UserService userService;

    public AddressService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public List<Address> findAddressByUserId(String userId) {
        User user = userService.findUserById(userId);
        return user.getAddresses();
    }
}
