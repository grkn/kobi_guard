package com.kobiguard.app.converter;

import com.kobiguard.app.entity.User;
import com.kobiguard.app.resources.AddressResource;
import com.kobiguard.app.resources.UserResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserToUserResourceConverter implements Converter<User, UserResource> {

    private final AddressToAddressResourceConverter addressToAddressResourceConverter;

    public UserToUserResourceConverter(AddressToAddressResourceConverter addressToAddressResourceConverter) {
        this.addressToAddressResourceConverter = addressToAddressResourceConverter;
    }

    @Override
    public UserResource convert(User user) {
        UserResource userResource = new UserResource();
        userResource.setAddresses(user.getAddresses()
                .stream()
                .map(addressToAddressResourceConverter::convert)
                .collect(Collectors.toList()));
        userResource.setEmail(user.getEmail());
        userResource.setId(user.getId());
        userResource.setLastName(user.getLastName());
        userResource.setName(user.getName());
        userResource.setNickName(user.getNickName());
        userResource.setUserType(user.getUserType());
        userResource.setxCoordinates(user.getxCoordinates());
        userResource.setyCoordinates(user.getyCoordinates());
        userResource.setId(user.getId());
        userResource.setPassword(user.getPassword());
        return userResource;
    }
}
