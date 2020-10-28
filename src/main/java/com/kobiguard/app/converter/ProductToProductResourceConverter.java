package com.kobiguard.app.converter;

import org.springframework.core.convert.converter.Converter;
import com.kobiguard.app.entity.Product;
import com.kobiguard.app.resources.ProductResource;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductToProductResourceConverter implements Converter<Product, ProductResource> {
    private AttributeToAttributeResourceConverter attributeToAttributeResourceConverter ;
    public ProductToProductResourceConverter(AttributeToAttributeResourceConverter attributeToAttributeResourceConverter){
        this.attributeToAttributeResourceConverter = attributeToAttributeResourceConverter;
    }
    @Override
    public ProductResource convert(Product product) {
        ProductResource productResource=new ProductResource();
        productResource.setName(product.getName());
        productResource.setPhoto(product.getPhoto());
        productResource.setPrice(product.getPrice());
        productResource.setCreatedDate(product.getCreatedDate());
        productResource.setUpdatedDate(product.getUpdatedDate());
        productResource.setFirm(product.getFirm());
        productResource.setAttributes(product.getAttributes().stream()
                .map(attributeToAttributeResourceConverter::convert)
                .collect(Collectors.toList()));
        return productResource;
    }
}
