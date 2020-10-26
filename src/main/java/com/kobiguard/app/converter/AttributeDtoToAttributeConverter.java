package com.kobiguard.app.converter;

import com.kobiguard.app.dto.AttributeDto;
import com.kobiguard.app.entity.Attribute;
import com.kobiguard.app.entity.Product;
import org.springframework.core.convert.converter.Converter;

public class AttributeDtoToAttributeConverter implements Converter<AttributeDto, Attribute> {
    private String id;
    private String key;
    private String value;
    private Product product;


    @Override
    public Attribute convert(AttributeDto attributeDto) {
        Attribute attribute = new Attribute();
        attribute.setKey(attributeDto.getKey());
        attribute.setValue(attribute.getValue());
        attribute.setProduct(attribute.getProduct());
        return null;
    }
}
