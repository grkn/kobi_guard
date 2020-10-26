package com.kobiguard.app.converter;

import com.kobiguard.app.dto.AddressDto;
import com.kobiguard.app.dto.ProductDto;
import com.kobiguard.app.entity.Address;
import com.kobiguard.app.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToProductConverter implements Converter<ProductDto, Product> {

    public ProductDtoToProductConverter() {

    }
    @Override
    public Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setAttributes(productDto.getAttributes());
        product.setCreatedDate(productDto.getCreatedDate());
        product.setUpdatedDate(productDto.getUpdatedDate());
        product.setFirm(productDto.getFirm());
        product.setName(productDto.getName());
        product.setPhoto(productDto.getPhoto());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
