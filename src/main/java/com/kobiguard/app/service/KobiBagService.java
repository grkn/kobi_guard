package com.kobiguard.app.service;

import com.kobiguard.app.entity.KobiBag;
import com.kobiguard.app.entity.Product;
import com.kobiguard.app.entity.SelectedProduct;
import com.kobiguard.app.entity.User;
import com.kobiguard.app.exception.ProductNotFoundException;
import com.kobiguard.app.exception.RemoveOperationException;
import com.kobiguard.app.repository.KobiBagRepository;
import com.kobiguard.app.repository.ProductRepository;
import com.kobiguard.app.repository.SelectedProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KobiBagService {

    private final KobiBagRepository kobiBagRepository;
    private final ProductRepository productRepository;
    private final SelectedProductRepository selectedProductRepository;
    private final UserService userService;

    public KobiBagService(KobiBagRepository kobiBagRepository, ProductRepository productRepository, SelectedProductRepository selectedProductRepository, UserService userService) {
        this.kobiBagRepository = kobiBagRepository;
        this.productRepository = productRepository;
        this.selectedProductRepository = selectedProductRepository;
        this.userService = userService;
    }

    public KobiBag createOrGetBag(String userId) {
        User user = userService.findUserById(userId);
        KobiBag kobiBag = user.getKobiBag();
        if (kobiBag == null) {
            return saveKobiBag(user);
        }
        return kobiBag;
    }

    public KobiBag findById(String bagId) {
        return kobiBagRepository.getOne(bagId);
    }

    @Transactional
    public void addProducts(List<SelectedProduct> add, String userId) {
        User user = userService.findUserById(userId);
        final KobiBag kobiBag = user.getKobiBag();
        add.forEach(item -> {
            String productId = item.getProductId();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Selected Product can not be found"));

            SelectedProduct selectedProduct = saveSelectedProduct(kobiBag, item, productId, product);
            kobiBag.setTotalPrice(kobiBag.getTotalPrice().add(selectedProduct.getPrice()));
            kobiBag.setQuantity(kobiBag.getQuantity() + 1);
            kobiBag.getProducts().add(selectedProduct);
        });
        kobiBagRepository.save(kobiBag);
        user.setKobiBag(kobiBag);
        kobiBag.setUser(user);
        userService.saveUser(user);
    }

    @Transactional
    public void removeProducts(List<SelectedProduct> remove, String userId) {
        User user = userService.findUserById(userId);
        KobiBag kobiBag = user.getKobiBag();
        remove.forEach(item -> {

            if (StringUtils.isEmpty(item.getId())) {
                throw new RemoveOperationException("Selected Product Id can not be null or empty");
            }

            Optional<SelectedProduct> selectedProductOptional = selectedProductRepository.findById(item.getId());

            if (selectedProductOptional.isPresent()) {
                selectedProductOptional.get().setBag(null);
                kobiBag.setTotalPrice(kobiBag.getTotalPrice().subtract(selectedProductOptional.get().getPrice()));
                kobiBag.setQuantity(kobiBag.getQuantity() - 1);
                kobiBag.getProducts().remove(selectedProductOptional.get());
                selectedProductRepository.deleteById(item.getId());
            }
        });

        kobiBagRepository.save(kobiBag);
    }

    private SelectedProduct saveSelectedProduct(KobiBag kobiBag, SelectedProduct item, String productId, Product product) {
        item.setBag(kobiBag);
        item.setPrice(product.getPrice());
        item.setName(product.getName());
        item.setAttributes(new ArrayList<>(product.getAttributes()));
        item.setProductId(productId);
        item.setPhoto(product.getPhoto());
        item.setFirm(product.getFirm());
        return selectedProductRepository.save(item);
    }

    private KobiBag saveKobiBag(User user) {
        KobiBag kobiBag = new KobiBag();
        kobiBag.setTotalPrice(new BigDecimal(0));
        kobiBag.setQuantity(0);
        kobiBag.setUser(user);
        user.setKobiBag(kobiBag);
        return userService.saveUser(user).getKobiBag();
    }

    @Transactional
    public KobiBag completeBag(String userId, String bagId) {
        User user = userService.findUserById(userId);
        if (!user.getKobiBag().getId().equals(bagId)) {
            throw new IllegalArgumentException("User and bag does not match with given userId and bagId");
        }

        KobiBag temp = new KobiBag();
        temp.setQuantity(user.getKobiBag().getQuantity());
        temp.setProducts(user.getKobiBag().getProducts());
        temp.setTotalPrice(user.getKobiBag().getTotalPrice());

        // notify firm
        // TODO gilleez
        // notify firm
        user.getKobiBag().setUser(null);
        user.setKobiBag(null);

        return temp;
    }
}

