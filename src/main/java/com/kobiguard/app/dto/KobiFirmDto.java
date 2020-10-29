package com.kobiguard.app.dto;

import com.kobiguard.app.entity.Address;
import com.kobiguard.app.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class KobiFirmDto {

    @NotNull
    private String name;
    private Double xCoordinate;
    private Double yCoordinate;
    @NotNull
    private List<AddressDto> addresses;
    private List<ProductDto> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

}
