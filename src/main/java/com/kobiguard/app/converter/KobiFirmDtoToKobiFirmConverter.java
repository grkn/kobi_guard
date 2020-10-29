package com.kobiguard.app.converter;

import com.kobiguard.app.dto.KobiFirmDto;
import com.kobiguard.app.entity.KobiFirm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class KobiFirmDtoToKobiFirmConverter implements Converter<KobiFirmDto, KobiFirm> {
    private final AddressDtoToAddressConverter addressDtoToAddressConverter;
    private final ProductDtoToProductConverter productDtoToProductConverter;

    public KobiFirmDtoToKobiFirmConverter(AddressDtoToAddressConverter addressDtoToAddressConverter,
                                          ProductDtoToProductConverter productDtoToProductConverter) {
        this.addressDtoToAddressConverter = addressDtoToAddressConverter;
        this.productDtoToProductConverter = productDtoToProductConverter;
    }

    @Override
    public KobiFirm convert(KobiFirmDto kobiFirmDto) {
        KobiFirm kobiFirm = new KobiFirm();
        kobiFirm.setName(kobiFirmDto.getName());
        kobiFirm.setAddresses(kobiFirmDto.getAddresses().stream()
                .map(addressDtoToAddressConverter::convert)
                .collect(Collectors.toList()));
        kobiFirm.setxCoordinate(kobiFirmDto.getxCoordinate());
        kobiFirm.setyCoordinate(kobiFirmDto.getxCoordinate());
        kobiFirm.setProducts(kobiFirmDto.getProducts().stream()
                .map(productDtoToProductConverter::convert).collect(Collectors.toList()));
        return kobiFirm;
    }
}
