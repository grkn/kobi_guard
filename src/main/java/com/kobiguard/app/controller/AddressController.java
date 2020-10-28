package com.kobiguard.app.controller;

import com.kobiguard.app.entity.Address;
import com.kobiguard.app.resources.AddressResource;
import com.kobiguard.app.resources.UserResource;
import com.kobiguard.app.service.AddressService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AddressController extends BaseController {

    private final AddressService addressService;
    private final ConversionService conversionService;

    public AddressController(AddressService addressService, ConversionService conversionService) {
        this.addressService = addressService;
        this.conversionService = conversionService;
    }


    @GetMapping("/user/{userId}/address")
    public ResponseEntity<List<AddressResource>> getUser(@PathVariable("userId") String userId) {
        List<AddressResource> addressResourceList = addressService.findAddressByUserId(userId)
                .stream()
                .map(address -> conversionService.convert(address,AddressResource.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(addressResourceList);
    }
    
}
