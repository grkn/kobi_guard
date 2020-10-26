package com.kobiguard.app.resources;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class KobiFirmResource implements Serializable {
    private String id;
    private String name;
    private Double xCoordinate;
    private Double yCoordinate;
    private List<AddressResource> addresses;

    private List<ProductResource> products;
    private Date createdDate;
    private Date updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<AddressResource> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResource> addresses) {
        this.addresses = addresses;
    }

    public List<ProductResource> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResource> products) {
        this.products = products;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
