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
import javafx.scene.control.Label;
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
    private AnchorPane tableContainer;
    @FXML
    private Label extraLbl, msgLbl;
    private int oszto = 0;
    private List<Integer> left2go;
    private Table table;
    
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
        table = new Table(tableContainer, Wish.class);
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
            
            if(!left2go.isEmpty()){
                showTable();
            }
            
            
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
        int extra = 0;
        System.out.println("left2goSize" + left2goSize);
        if(!left2go.isEmpty())
        {
            msgLbl.setText("Extra: ");
            extraLbl.setText("");
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
                    model.updateProgress(100.00f, id);                  


                    extra = savings - price;
                    String extraTxt = String.valueOf(extra);
                    extraLbl.setText(extraTxt);
                    if(left2goSize > 1)
                    {
                        actualSaving += extra / (left2goSize); 
                    } else {
                        actualSaving += extra;
                    }      
                }                
                else {                
                    model.updateSaving(savings, id);
                    setProgress(price, savings, id);
                }            
            }
        } else {
            msgLbl.setText("You are done with your savings, you've got extra: ");
            String extraTxt = String.valueOf(saveTF.getText());
            extraLbl.setText(extraTxt);
       }
        
    }
    
    public void setProgress(int price, int savings, int id)
    {
        float progress = ((float) savings / price) * 100;
        model.updateProgress(progress, id);        
    }
    
    
}
