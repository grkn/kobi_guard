package com.kobiguard.app.resources;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class KobiBagResource {

    private String id;
    private List<SelectedProductResource> products;

    private BigDecimal totalPrice;
    private int quantity;

    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;

    public List<SelectedProductResource> getProducts() {
        return products;
    }

    public void setProducts(List<SelectedProductResource> products) {
        this.products = products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
