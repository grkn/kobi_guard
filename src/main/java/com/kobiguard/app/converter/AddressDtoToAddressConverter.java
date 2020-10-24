package com.kobiguard.app.converter;

import com.kobiguard.app.dto.AddressDto;
import com.kobiguard.app.entity.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddressConverter implements Converter<AddressDto, Address> {
    @Override
    public Address convert(AddressDto addressDto) {
        Address address = new Address();
        address.setAddressLine(addressDto.getAddressLine());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setPhoneNumber(addressDto.getPhoneNumber());
        return address;
    }
}
