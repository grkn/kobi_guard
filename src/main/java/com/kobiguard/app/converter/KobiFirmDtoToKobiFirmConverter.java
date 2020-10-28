package com.kobiguard.app.converter;

import com.kobiguard.app.dto.KobiFirmDto;
import com.kobiguard.app.entity.KobiFirm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class KobiFirmDtoToKobiFirmConverter implements Converter<KobiFirmDto, KobiFirm> {
    private final AddressDtoToAddressConverter addressDtoToAddressConverter;

    public KobiFirmDtoToKobiFirmConverter(AddressDtoToAddressConverter addressDtoToAddressConverter) {
        this.addressDtoToAddressConverter = addressDtoToAddressConverter;
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
        kobiFirm.setCreatedDate(kobiFirmDto.getCreatedDate());
        kobiFirm.setUpdatedDate(kobiFirmDto.getUpdatedDate());
        return kobiFirm;
    }
}
