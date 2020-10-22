package com.kobiguard.app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class SelectedProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "firm_id")
    private KobiFirm firm;

    @ManyToOne
    @JoinColumn(name = "bag_id")
    private KobiBag bag;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attribute> attributes;

    @Lob
    private byte[] photo;

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

    public KobiFirm getFirm() {
        return firm;
    }

    public void setFirm(KobiFirm firm) {
        this.firm = firm;
    }

    public KobiBag getBag() {
        return bag;
    }

    public void setBag(KobiBag bag) {
        this.bag = bag;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }


}
