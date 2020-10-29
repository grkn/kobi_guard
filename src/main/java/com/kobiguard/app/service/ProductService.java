package com.kobiguard.app.service;

import com.kobiguard.app.entity.KobiFirm;
import com.kobiguard.app.entity.Product;
import com.kobiguard.app.entity.User;
import com.kobiguard.app.exception.ProductNotFoundException;
import com.kobiguard.app.exception.UserNotFoundException;
import com.kobiguard.app.repository.AddressRepository;
import com.kobiguard.app.repository.KobiFirmRepository;
import com.kobiguard.app.repository.ProductRepository;
import com.kobiguard.app.repository.UserRepository;
import com.kobiguard.app.resources.KobiFirmResource;
import com.kobiguard.app.security.SecurityService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final KobiFirmService kobiFirmService;

    public ProductService(ProductRepository productRepository, KobiFirmService firmService) {
        this.productRepository = productRepository;
        this.kobiFirmService = firmService;
    }

    @Transactional
    public Product createProduct(Product product, String firmId) {
        KobiFirm kobiFirm = kobiFirmService.findKobiFirmById(firmId);
        Product createdProduct = productRepository.save(product);
        if(CollectionUtils.isEmpty(kobiFirm.getProducts())){
            kobiFirm.setProducts(new ArrayList<>());
        }
        kobiFirm.getProducts().add(createdProduct);
        createdProduct.setFirm(kobiFirm);
        kobiFirmService.saveKobiFirm(kobiFirm);
        return createdProduct;
    }

    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product not found by given id : %s", productId)));
    }

    public List<Product> findByKobiFirmId(String firmId) {
        return productRepository.findProductsByFirmId(firmId);
    }
}
