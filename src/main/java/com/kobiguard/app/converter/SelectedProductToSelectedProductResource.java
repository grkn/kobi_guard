package com.kobiguard.app.converter;

import com.kobiguard.app.entity.SelectedProduct;
import com.kobiguard.app.resources.SelectedProductResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SelectedProductToSelectedProductResource implements Converter<SelectedProduct, SelectedProductResource> {

    private final KobiFirmToKobiFirmResourceConverter kobiFirmToKobiFirmResourceConverter;

    public SelectedProductToSelectedProductResource(KobiFirmToKobiFirmResourceConverter kobiFirmToKobiFirmResourceConverter) {
        this.kobiFirmToKobiFirmResourceConverter = kobiFirmToKobiFirmResourceConverter;
    }

    @Override
    public SelectedProductResource convert(SelectedProduct selectedProduct) {
        SelectedProductResource selectedProductResource = new SelectedProductResource();
        selectedProductResource.setId(selectedProduct.getId());
        selectedProductResource.setName(selectedProduct.getName());
        selectedProductResource.setPrice(selectedProduct.getPrice());
        if (selectedProduct.getFirm() != null) {
            selectedProductResource.setFirm(kobiFirmToKobiFirmResourceConverter.convert(selectedProduct.getFirm()));
        }
        return selectedProductResource;
    }
}
