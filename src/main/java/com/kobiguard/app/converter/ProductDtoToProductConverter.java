package com.kobiguard.app.converter;

import com.kobiguard.app.dto.ProductDto;
import com.kobiguard.app.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToProductConverter implements Converter<ProductDto, Product> {

    private final KobiFirmDtoToKobiFirmConverter kobiFirmDtoToKobiFirmConverter;

    public ProductDtoToProductConverter(KobiFirmDtoToKobiFirmConverter kobiFirmDtoToKobiFirmConverter) {
        this.kobiFirmDtoToKobiFirmConverter = kobiFirmDtoToKobiFirmConverter;
    }

    @Override
    public Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setAttributes(productDto.getAttributes());
        product.setCreatedDate(productDto.getCreatedDate());
        product.setUpdatedDate(productDto.getUpdatedDate());
        product.setFirm(kobiFirmDtoToKobiFirmConverter.convert(productDto.getFirm()));
        product.setName(productDto.getName());
        product.setPhoto(productDto.getPhoto());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
