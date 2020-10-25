package com.kobiguard.app.controller;

import com.kobiguard.app.entity.Address;
import com.kobiguard.app.entity.Product;
import com.kobiguard.app.entity.User;
import com.kobiguard.app.resources.AddressResource;
import com.kobiguard.app.resources.ProductResource;
import com.kobiguard.app.resources.UserResource;
import com.kobiguard.app.service.AddressService;
import com.kobiguard.app.service.ProductService;
import com.kobiguard.app.service.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController extends BaseController {

    private final ProductService productService;
    private final ConversionService conversionService;

    public ProductController(ProductService productService, ConversionService conversionService) {
        this.productService = productService;
        this.conversionService = conversionService;
    }


    @GetMapping("/products")
    public ResponseEntity<Page<ProductResource>> getProducts(@PageableDefault Pageable pageable) {
        Page<Product> product = productService.findAllProducts(pageable);
        List<ProductResource> productResourceList = new ArrayList<>();
        product.get().forEach(item -> productResourceList.add(conversionService.convert(item, ProductResource.class)));
        return ResponseEntity.ok(new PageImpl<>(productResourceList, product.getPageable(), product.getTotalElements()));
    }
}
