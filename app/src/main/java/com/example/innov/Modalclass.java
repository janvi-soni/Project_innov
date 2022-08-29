package com.example.innov;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Modalclass {


    String Name, Price ,Category;
    public Modalclass()
    {}

    public Modalclass(String Name, String Price, String Category) {
        this.Name = Name;
        this.Price = Price;
        this.Category = Category;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }
    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }
}