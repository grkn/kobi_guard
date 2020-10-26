package com.kobiguard.app.converter;

import com.kobiguard.app.dto.AddressDto;
import com.kobiguard.app.dto.KobiFirmDto;
import com.kobiguard.app.entity.KobiFirm;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class KobiFirmDtoToKobiFirmConverter implements Converter <KobiFirmDto, KobiFirm> {
    private ProductDtoToProductConverter productDtoToProductConverter;
    private AddressDtoToAddressConverter addressDtoToAddressConverter;
    public KobiFirmDtoToKobiFirmConverter(ProductDtoToProductConverter productDtoToProductConverter, AddressDtoToAddressConverter addressDtoToAddressConverter){
        this.productDtoToProductConverter = productDtoToProductConverter;
        this.addressDtoToAddressConverter = addressDtoToAddressConverter;
    }
    @Override
    public KobiFirm convert(KobiFirmDto kobiFirmDto) {
        KobiFirm kobiFirm=new KobiFirm();
        kobiFirm.setName(kobiFirmDto.getName());
        kobiFirm.setProducts(kobiFirmDto.getProducts().stream()
                .map(productDtoToProductConverter::convert)
                .collect(Collectors.toList()));
        kobiFirm.setAddresses(kobiFirmDto.getAddresses().stream()
                .map(addressDtoToAddressConverter::convert)
                .collect(Collectors.toList()));
        kobiFirm.setxCoordinate(kobiFirmDto.getxCoordinate());
        kobiFirm.setyCoordinate(kobiFirmDto.getxCoordinate());
        kobiFirm.setCreatedDate(kobiFirmDto.getCreatedDate());
        kobiFirm.setUpdatedDate(kobiFirmDto.getUpdatedDate());
        return kobiFirm;
    }
}
