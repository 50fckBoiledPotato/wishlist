package com.pepper.WishList.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WishRepository extends CrudRepository<Wish, Integer>
{
    @Override
    Wish save(Wish wish);
    
    @Override
    List<Wish> findAll();
    
    List<Wish> findByIsPurchased(boolean isPurchased);
}
