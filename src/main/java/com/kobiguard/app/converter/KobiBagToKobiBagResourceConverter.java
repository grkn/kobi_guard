package com.kobiguard.app.converter;

import com.kobiguard.app.entity.KobiBag;
import com.kobiguard.app.resources.KobiBagResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
public class KobiBagToKobiBagResourceConverter implements Converter<KobiBag, KobiBagResource> {

    private final SelectedProductToSelectedProductResource selectedProductToSelectedProductResource;

    public KobiBagToKobiBagResourceConverter(SelectedProductToSelectedProductResource selectedProductToSelectedProductResource) {
        this.selectedProductToSelectedProductResource = selectedProductToSelectedProductResource;
    }

    @Override
    public KobiBagResource convert(KobiBag kobiBag) {
        KobiBagResource kobiBagResource = new KobiBagResource();
        kobiBagResource.setId(kobiBag.getId());
        kobiBagResource.setCreatedDate(kobiBag.getCreatedDate());
        kobiBagResource.setUpdatedDate(kobiBag.getUpdatedDate());
        if (!CollectionUtils.isEmpty(kobiBag.getProducts())) {
            kobiBagResource.setProducts(kobiBag
                    .getProducts()
                    .stream()
                    .map(selectedProductToSelectedProductResource::convert).collect(Collectors.toList()));
        }
        kobiBagResource.setQuantity(kobiBag.getQuantity());
        kobiBagResource.setTotalPrice(kobiBag.getTotalPrice());
        return kobiBagResource;
    }
}
