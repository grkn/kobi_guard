package com.kobiguard.app.converter;

import com.kobiguard.app.dto.UserDto;
import com.kobiguard.app.entity.Address;
import com.kobiguard.app.entity.User;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    private final AddressDtoToAddressConverter addressDtoToAddressConverter;

    public UserDtoToUserConverter(AddressDtoToAddressConverter addressDtoToAddressConverter) {
        this.addressDtoToAddressConverter = addressDtoToAddressConverter;
    }

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setNickName(userDto.getNickName());
        user.setAddresses(userDto.getAddresses().stream()
                .map(addressDtoToAddressConverter::convert)
                .collect(Collectors.toList()));
        user.setLastName(userDto.getLastName());
        user.setName(userDto.getName());
        user.setUserType(userDto.getUserType());
        user.setxCoordinates(userDto.getxCoordinates());
        user.setyCoordinates(userDto.getyCoordinates());
        return user;
    }
}
