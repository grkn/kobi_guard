package com.kobiguard.app.controller;

import com.kobiguard.app.resources.AddressResource;
import com.kobiguard.app.resources.ProductResource;
import com.kobiguard.app.service.AddressService;
import com.kobiguard.app.service.ProductService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

public class ProductController {

    @RestController
    public class ProductController extends BaseController {

        private final ProductService productService;
        private final ConversionService conversionService;

        public ProductController(ProductService productService, ConversionService conversionService) {
            this.productService = productService;
            this.conversionService = conversionService;
        }

/*
        @GetMapping("/user/{firmId}/firm")
        public ResponseEntity<List<ProductResource>> getUser(@PathVariable("userId") String userId) {
            List<AddressResource> addressResourceList = addressService.findAddressByUserId(userId)
                    .stream()
                    .map(address -> conversionService.convert(address,AddressResource.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(addressResourceList);
       }

 */
    }
}
