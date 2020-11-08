package com.kobiguard.app.controller;

import com.kobiguard.app.dto.KobiBagDto;
import com.kobiguard.app.entity.KobiBag;
import com.kobiguard.app.entity.SelectedProduct;
import com.kobiguard.app.resources.KobiBagResource;
import com.kobiguard.app.service.KobiBagService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class KobiBagController extends BaseController {

    private final KobiBagService kobiBagService;
    private final ConversionService conversionService;

    public KobiBagController(KobiBagService kobiBagService, ConversionService conversionService) {
        this.kobiBagService = kobiBagService;
        this.conversionService = conversionService;
    }

    @PatchMapping("/user/{userId}/bag")
    public ResponseEntity<KobiBagResource> addAndRemoveProduct(@RequestBody @Valid KobiBagDto kobiBagDto, @PathVariable String userId) {
        KobiBagResource kobiBagResource = conversionService.convert(kobiBagService.createOrGetBag(userId), KobiBagResource.class);
        addProducts(kobiBagDto, userId);
        removeProducts(kobiBagDto, userId);
        return ResponseEntity.ok(conversionService.convert(kobiBagService.findById(kobiBagResource.getId()), KobiBagResource.class));
    }

    @GetMapping("/user/{userId}/bag")
    public ResponseEntity<KobiBagResource> getBag(@PathVariable String userId) {
        return ResponseEntity.ok(conversionService.convert(kobiBagService.createOrGetBag(userId), KobiBagResource.class));
    }

    @PostMapping("/user/{userId}/bag/{bagId}/complete")
    public ResponseEntity<KobiBagResource> completeBag(@PathVariable String userId, @PathVariable String bagId) {
        return ResponseEntity.accepted().body(conversionService.convert(kobiBagService.completeBag(userId, bagId), KobiBagResource.class));
    }


    private void removeProducts(KobiBagDto kobiBagDto, String userId) {
        if (!CollectionUtils.isEmpty(kobiBagDto.getRemove())) {
            List<SelectedProduct> remove = kobiBagDto.getRemove()
                    .stream()
                    .map(selectedProductDto -> conversionService.convert(selectedProductDto, SelectedProduct.class))
                    .collect(Collectors.toList());
            kobiBagService.removeProducts(remove, userId);
        }
    }

    private void addProducts(KobiBagDto kobiBagDto, String userId) {
        if (!CollectionUtils.isEmpty(kobiBagDto.getAdd())) {
            List<SelectedProduct> add = kobiBagDto.getAdd()
                    .stream()
                    .map(selectedProductDto -> conversionService.convert(selectedProductDto, SelectedProduct.class))
                    .collect(Collectors.toList());
            kobiBagService.addProducts(add, userId);
        }
    }

}
