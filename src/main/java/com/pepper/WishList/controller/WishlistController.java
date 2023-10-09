package com.pepper.WishList.controller;

import com.pepper.WishList.WishCore;
import com.pepper.WishList.gui.Table;
import com.pepper.WishList.model.Model;
import com.pepper.WishList.model.Wish;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Controller;


@Controller
public class WishlistController implements Initializable 
{
    
    private Model model;
    private List<Wish> wishList;
    @FXML
    private TextField itemTF, priceTF, saveTF;
    @FXML
    AnchorPane tableContainer;
    private int oszto = 0;
    private List<Integer> left2go;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        model = WishCore.getContext().getBean(Model.class);
        wishList = new ArrayList<>();
        left2go = new ArrayList<>();
        showTable();
    }    
    
    @FXML
    public void addItem()
    {
        if(!itemTF.getText().isEmpty() && !priceTF.getText().isEmpty())
        {
            int price = Integer.parseInt(priceTF.getText());
            Wish newWish = new Wish(itemTF.getText(), price);
            model.save(newWish);
            showTable();
        }        
    }

    private void showTable() 
    {
        tableContainer.getChildren().clear();
        Table table = new Table(tableContainer, Wish.class);
        wishList = model.findAll();
        table.setItems(wishList);
        
        table.addActionColumn("Delete", (wish, index) ->
        {
            model.deleteWish((Wish) wish);
            showTable();
            
        });
    }
    
    public void saveUp()
    {        
        if(!saveTF.getText().isEmpty())
        {
            int osztando = Integer.parseInt(saveTF.getText());
            
            int hanyados = getQuotient(osztando);
            divideSaving(hanyados);
            
            showTable();
        }
    }
    
    public int getQuotient(int osztando)
    {
        oszto = 0;
        left2go.clear();
        
        for(int i = 0; i < wishList.size(); i++)
        {
            Wish wish = wishList.get(i);
            if(wish.getPrice() > wish.getSavings())
            {
                oszto++;
                left2go.add(i);
            }
        }
        if(oszto == 0){
            return 0;
        }
        
        int hanyados = osztando / oszto;  
        
        return hanyados;
    }
    
    public void divideSaving(int hanyados)
    {
        int actualSaving = hanyados;
        int left2goSize = left2go.size();
        
        for(int i = 0; i < left2go.size(); i++)
        {            
            left2goSize--;
            Wish wish = wishList.get(left2go.get(i));
            int id = wish.getId();
            int price = wish.getPrice();
            int currentSaving = wish.getSavings();
            
            int savings = currentSaving + actualSaving;
                
            if(savings > price)
            {
                model.updateSaving(price, id);
                
                int extra = savings - price; System.out.println("extra: " + extra);
                
                actualSaving += extra / (left2goSize); 
                // TUDNOM KELL H EBBEN A KÖRBEN ELÉRTE-E VALAMELYIK A CÉLÖSSZEGET
                                            
            }                
            else {                
                model.updateSaving(savings, id);
            }
            setProgress(price, savings, id);
        }
    }
    
    public void setProgress(int price, int savings, int id)
    {
        float progress = ((float) savings / price) * 100;
        model.updateProgress(progress, id);        
    }
    
    
}
