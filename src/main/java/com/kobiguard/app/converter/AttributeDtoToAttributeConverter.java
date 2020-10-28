package com.kobiguard.app.converter;

import com.kobiguard.app.dto.AttributeDto;
import com.kobiguard.app.entity.Attribute;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttributeDtoToAttributeConverter implements Converter<AttributeDto, Attribute> {

    private final ProductDtoToProductConverter productDtoToProductConverter;

    public AttributeDtoToAttributeConverter(ProductDtoToProductConverter productDtoToProductConverter) {
        this.productDtoToProductConverter = productDtoToProductConverter;
    }

    @Override
    public Attribute convert(AttributeDto attributeDto) {
        Attribute attribute = new Attribute();
        attribute.setKey(attributeDto.getKey());
        attribute.setValue(attribute.getValue());
        attribute.setProduct(productDtoToProductConverter.convert(attributeDto.getProduct()));
        return null;
    }
}
