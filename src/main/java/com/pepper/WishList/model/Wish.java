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
    private boolean isPurchased;

    public Wish( String name, int price) 
    {
        this.name = name;
        this.price = price;
        isPurchased = false;
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

    public boolean isIs_purchased() {
        return isPurchased;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIs_purchased(boolean is_purchased) {
        this.isPurchased = is_purchased;
    }

    @Override
    public String toString() {
        return "Wish{" + "id=" + id + ", name=" + name + ", price=" + price + ", is_purchased=" + isPurchased + '}';
    }
    
}
