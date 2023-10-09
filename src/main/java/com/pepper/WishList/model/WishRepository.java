package com.pepper.WishList.model;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface WishRepository extends CrudRepository<Wish, Integer>
{
    @Override
    Wish save(Wish wish);
    
    void delete(Wish wish);
    
    @Override
    List<Wish> findAll();
    
    @Modifying
    @Transactional
    @Query("UPDATE Wish w SET w.savings = :saving WHERE w.id = :itemId")
    void updateSavingsById(@Param("saving") double newPrice, @Param("itemId") Integer itemId);
    
    @Modifying
    @Query("UPDATE Wish w SET w.progress = :progress WHERE w.id = :id")
    int updateProgress(@Param("id") Integer id, @Param("progress") Float progress);    

    @Query("SELECT w.price FROM Wish w WHERE w.id = :id")
    Integer findPriceById(@Param("id") Integer id);
    
    @Query("SELECT w.savings FROM Wish w WHERE w.id = :id")
    Integer findSavingsById(@Param("id") Integer id);
}
