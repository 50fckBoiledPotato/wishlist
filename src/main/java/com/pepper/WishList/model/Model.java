package com.pepper.WishList.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Model 
{
    @Autowired
    private WishRepository wishRepo;
    
    public void save(Wish wish)
    {
        wishRepo.save(wish);
    }
    
    public List<Wish> findAll()
    {
        List<Wish> wishList = wishRepo.findAll();
        
        return wishList;
    }

}
