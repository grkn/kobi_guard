package com.kobiguard.app.converter;

import com.kobiguard.app.entity.Attribute;
import com.kobiguard.app.resources.AttributeResource;
import org.springframework.core.convert.converter.Converter;

public class AttributeToAttributeResourceConverter implements Converter<Attribute, AttributeResource> {

    @Override
    public AttributeResource convert(Attribute attribute) {
        AttributeResource attributeResource = new AttributeResource();
        attributeResource.setKey(attribute.getKey());
        attributeResource.setValue(attribute.getValue());
        attributeResource.setProduct(attribute.getProduct());
        return attributeResource;
    }
}
