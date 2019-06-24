package com.example.myshoppingapp.models;

import java.io.Serializable;

public class Product implements Serializable {
    private String name,unit,image;
    int price;
    public Product(){}

    public Product(String name, String unit, int price, String image) {
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
