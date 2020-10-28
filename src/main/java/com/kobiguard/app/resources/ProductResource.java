package com.kobiguard.app.resources;

import com.kobiguard.app.entity.KobiFirm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductResource implements  Serializable {
    private String id;
    private String name;
    private BigDecimal price;
    private KobiFirm firm;
    private byte[] photo;
    private List<AttributeResource> attributes;
    private Date createdDate;
    private Date updatedDate;

    public String getId() {return id;}
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {return name;}
    public void setName(String Name) {this.name =Name;}



    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public KobiFirm getFirm() {
        return firm;
    }

    public void setFirm(KobiFirm firm) {
        this.firm = firm;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<AttributeResource> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeResource> attributes) {
        this.attributes = attributes;
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

