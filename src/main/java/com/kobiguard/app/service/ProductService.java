package com.kobiguard.app.service;

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

@Service
public class ProductService {

    private final ConversionService conversionService;
    private final ProductRepository productRepository;
    private final SecurityService securityService;
    private final KobiFirmService kobiFirmService;

    public ProductService(ConversionService conversionService, ProductRepository productRepository
                       , SecurityService securityService, KobiFirmService firmService) {
        this.conversionService = conversionService;
        this.productRepository = productRepository;
        this.securityService = securityService;
        this.kobiFirmService = firmService;
    }

    @Transactional
    public Product createProduct(Product product) {
        //productRepository.saveAll(user.getAddresses());
        Product createdProduct = productRepository.save(product);
        return createdProduct;
    }

    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product not found by given id : %s", productId)));
    }
}
