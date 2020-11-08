package com.kobiguard.app.resources;

import java.math.BigDecimal;
import java.util.List;

public class SelectedProductResource {

    private String id;
    private String name;
    private BigDecimal price;
    private KobiFirmResource firm;
    private List<AttributeResource> attributes;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public KobiFirmResource getFirm() {
        return firm;
    }

    public void setFirm(KobiFirmResource firm) {
        this.firm = firm;
    }

    public List<AttributeResource> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeResource> attributes) {
        this.attributes = attributes;
    }
}
