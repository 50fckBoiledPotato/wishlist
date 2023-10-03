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
    private TextField itemTF, priceTF;
    @FXML
    AnchorPane tableContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        model = WishCore.getContext().getBean(Model.class);
        wishList = new ArrayList<>();
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
        List<Wish> wishList = model.findAll();
        table.setItems(wishList);
    }
}
