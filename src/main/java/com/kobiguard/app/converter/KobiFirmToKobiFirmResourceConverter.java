package com.kobiguard.app.converter;

import com.kobiguard.app.entity.KobiFirm;
import com.kobiguard.app.resources.KobiFirmResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class KobiFirmToKobiFirmResourceConverter implements Converter<KobiFirm, KobiFirmResource> {
    private AddressToAddressResourceConverter addressToAddressResourceConverter;
    private ProductToProductResourceConverter productToProductResourceConverter;
    public KobiFirmToKobiFirmResourceConverter(AddressToAddressResourceConverter addressToAddressResourceConverter, ProductToProductResourceConverter productToProductResourceConverter){
        this.addressToAddressResourceConverter = addressToAddressResourceConverter;
        this.productToProductResourceConverter = productToProductResourceConverter;
    }
    @Override
    public KobiFirmResource convert(KobiFirm kobiFirm) {
        KobiFirmResource kobiFirmResource = new KobiFirmResource();
        kobiFirmResource.setName(kobiFirm.getName());
        kobiFirmResource.setProducts(kobiFirm.getProducts().stream()
                .map(productToProductResourceConverter::convert)
                .collect(Collectors.toList()));
        kobiFirmResource.setAddresses(kobiFirm.getAddresses().stream()
                .map(addressToAddressResourceConverter::convert)
                .collect(Collectors.toList()));

        kobiFirmResource.setxCoordinate(kobiFirm.getxCoordinate());
        kobiFirmResource.setyCoordinate(kobiFirm.getyCoordinate());
        kobiFirmResource.setCreatedDate(kobiFirm.getCreatedDate());
        kobiFirmResource.setUpdatedDate(kobiFirm.getUpdatedDate());
        return kobiFirmResource;
    }
}
