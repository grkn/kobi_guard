package com.kobiguard.app.resources;

import java.io.Serializable;

public class ProductResource implements  Serializable {
    private String Id;
    private String Name;
    private String Price;


    public String getId() {return Id;}
    public void GetId (String Id) {this.Id=Id}

    public String getName() {return Name;}
    public void setName(String Name) {this.Name=Name}

    public String getName() {return Name;}
    public void setPrice(String Price) {this.Price =Price}

}

