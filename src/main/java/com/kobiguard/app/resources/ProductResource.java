package com.kobiguard.app.resources;

import com.kobiguard.app.entity.Attribute;
import com.kobiguard.app.entity.KobiFirm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductResource implements  Serializable {
    private String Id;
    private String Name;
    private BigDecimal Price;
    private KobiFirm firm;
    private byte[] photo;
    private List<AttributeResource> attributes;
    private Date createdDate;
    private Date updatedDate;

    public String getId() {return Id;}
    public void setId(String id) {
        Id = id;
    }
    public String getName() {return Name;}
    public void setName(String Name) {this.Name=Name;}



    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
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

