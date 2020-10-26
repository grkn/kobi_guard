package com.kobiguard.app.resources;

import com.kobiguard.app.entity.Product;

import java.io.Serializable;

public class AttributeResource implements Serializable {
    private String id;
    private String key;
    private String value;
    private Product product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
