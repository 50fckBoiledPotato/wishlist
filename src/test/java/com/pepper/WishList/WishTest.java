package com.pepper.WishList;

import com.pepper.WishList.model.Wish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class WishTest 
{
    Wish wish;
    
    @BeforeEach
    public void setUp()
    {
        wish = new Wish("laptop", 500000);
    }
    
    @Test
    public void testGetters()
    {
        assertEquals("laptop", wish.getName(), "Get-Name");
        assertEquals(500000, wish.getPrice(), "Get-Price");
        assertEquals(0, wish.getSavings(), "Get - Savings");
        assertEquals(0.0, wish.getProgress(), "getProgress");
    }
    
    @Test
    public void testSetter()
    {
        wish.setSavings(400);
        assertEquals(400, wish.getSavings(), "getSavings");
        
        wish.setProgress(0);
        
    }

}
