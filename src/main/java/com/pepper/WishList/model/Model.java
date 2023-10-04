package com.pepper.WishList.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    
    public void updateSaving(int saving, int id)
    {
        wishRepo.updateSavingsById(saving, id);
    }
    
    @Transactional
    public void updateProgress(float progress, int id)
    {
        wishRepo.updateProgress(id, progress);
    }
    
    @Transactional
    public void updateIsPurchased(boolean value, int id)
    {
        wishRepo.updateIsPurchased(value, id);
    }

}
