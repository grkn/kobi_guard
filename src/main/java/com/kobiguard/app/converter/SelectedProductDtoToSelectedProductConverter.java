package com.kobiguard.app.converter;

import com.kobiguard.app.dto.SelectedProductDto;
import com.kobiguard.app.entity.SelectedProduct;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SelectedProductDtoToSelectedProductConverter implements Converter<SelectedProductDto, SelectedProduct> {

    @Override
    public SelectedProduct convert(SelectedProductDto selectedProductDto) {
        SelectedProduct selectedProduct = new SelectedProduct();
        selectedProduct.setId(selectedProductDto.getId());
        selectedProduct.setName(selectedProductDto.getName());
        selectedProduct.setPrice(selectedProduct.getPrice());
        selectedProduct.setProductId(selectedProductDto.getProductId());
        return selectedProduct;
    }
}
