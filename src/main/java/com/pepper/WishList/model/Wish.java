package com.pepper.WishList.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wishlist")
public class Wish 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int price;
    private int savings;
    
    private Float progress;
    

    public Wish( String name, int price) 
    {
        this.name = name;
        this.price = price;
        savings = 0;
        
        progress = 0.0f;
    }
    public Wish(){}

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }    
    public int getSavings() {
        return savings;
    }    
    public float getProgress() {
        return progress;
    }
    

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    
    public void setSavings(int savings) {
        this.savings = savings;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
    

    @Override
    public String toString() {
        return "Wish{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }
    
}
