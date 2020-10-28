package com.kobiguard.app.dto;

import com.kobiguard.app.entity.Attribute;
import com.kobiguard.app.resources.KobiFirmResource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductDto {
    private String id;
    @NotBlank
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private KobiFirmDto firm;

    private byte[] photo;

    private List<Attribute> attributes;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
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

    public KobiFirmDto getFirm() {
        return firm;
    }

    public void setFirm(KobiFirmDto firm) {
        this.firm = firm;
    }
}
