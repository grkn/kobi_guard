package com.kobiguard.app.service;

import com.kobiguard.app.entity.Address;
import com.kobiguard.app.entity.KobiFirm;
import com.kobiguard.app.entity.Product;
import com.kobiguard.app.entity.User;
import com.kobiguard.app.exception.ProductNotFoundException;
import com.kobiguard.app.repository.AddressRepository;
import com.kobiguard.app.repository.KobiFirmRepository;
import com.kobiguard.app.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class KobiFirmService {
    private final AddressRepository addressRepository;
    private final KobiFirmRepository kobiFirmRepository;
    private final ProductRepository productRepository;
    public KobiFirmService(AddressRepository addressRepository, KobiFirmRepository kobiFirmRepository, ProductRepository productRepository) {
        this.addressRepository = addressRepository;
        this.kobiFirmRepository = kobiFirmRepository;
        this.productRepository = productRepository;
    }
    public KobiFirm createKobiFirm(KobiFirm kobiFirm){
        addressRepository.saveAll(kobiFirm.getAddresses());
        productRepository.saveAll(kobiFirm.getProducts());
        kobiFirm.getAddresses().forEach(address -> address.setFirm(kobiFirm));
        kobiFirm.getProducts().forEach(product -> product.setFirm(kobiFirm));
        kobiFirm.setName(kobiFirm.getName());
        KobiFirm createdKobiFirm = kobiFirmRepository.save(kobiFirm);

        return createdKobiFirm;
    }
    public KobiFirm findKobiFirmServiceById(String kobiFirmServiceId) {
        return kobiFirmRepository.findById(kobiFirmServiceId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("KobiFirm not found by given id : %s", kobiFirmServiceId)));
    }
}