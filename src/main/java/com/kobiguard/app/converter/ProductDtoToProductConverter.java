package com.kobiguard.app.converter;

import com.kobiguard.app.dto.ProductDto;
import com.kobiguard.app.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductDtoToProductConverter implements Converter<ProductDto, Product> {

    private final AttributeDtoToAttributeConverter attributeDtoToAttributeConverter;

    public ProductDtoToProductConverter(AttributeDtoToAttributeConverter attributeDtoToAttributeConverter) {
        this.attributeDtoToAttributeConverter = attributeDtoToAttributeConverter;
    }

    @Override
    public Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setAttributes(productDto.getAttributes().stream()
                .map(attributeDtoToAttributeConverter::convert)
                .collect(Collectors.toList()));
        product.setName(productDto.getName());
        product.setPhoto(productDto.getPhoto());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
