package com.kobiguard.app.dto;

import com.kobiguard.app.entity.Address;
import com.kobiguard.app.enumurate.UserType;
import com.kobiguard.app.resources.AddressResource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserDto {

    private String id;
    private List<AddressDto> addresses;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String nickName;
    private Double xCoordinates;
    private Double yCoordinates;
    @NotBlank
    private String email;
    @NotNull
    private UserType userType;
    @NotBlank
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
    }
}
