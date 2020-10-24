package com.kobiguard.app.converter;

import com.kobiguard.app.entity.Address;
import com.kobiguard.app.resources.AddressResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressResourceConverter implements Converter<Address, AddressResource> {

    @Override
    public AddressResource convert(Address address) {
        AddressResource addressResource = new AddressResource();
        addressResource.setAddressLine(address.getAddressLine());
        addressResource.setCity(address.getCity());
        addressResource.setCountry(address.getCountry());
        addressResource.setPhoneNumber(address.getPhoneNumber());
        return addressResource;
    }
}
