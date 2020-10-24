package com.kobiguard.app.resources;

import com.kobiguard.app.enumurate.UserType;

import java.io.Serializable;
import java.util.List;

public class UserResource implements Serializable {

    private String id;
    private List<AddressResource> addresses;
    private String name;
    private String lastName;
    private String nickName;
    private Double xCoordinates;
    private Double yCoordinates;
    private String email;
    private UserType userType;
    private String password;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Double getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(Double xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public Double getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(Double yCoordinates) {
        this.yCoordinates = yCoordinates;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<AddressResource> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResource> addresses) {
        this.addresses = addresses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
