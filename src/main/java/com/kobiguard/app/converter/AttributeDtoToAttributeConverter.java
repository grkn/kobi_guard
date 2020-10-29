package com.kobiguard.app.converter;

import com.kobiguard.app.dto.AttributeDto;
import com.kobiguard.app.entity.Attribute;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttributeDtoToAttributeConverter implements Converter<AttributeDto, Attribute> {

    @Override
    public Attribute convert(AttributeDto attributeDto) {
        Attribute attribute = new Attribute();
        attribute.setKey(attributeDto.getKey());
        attribute.setValue(attributeDto.getValue());
        return attribute;
    }
}
