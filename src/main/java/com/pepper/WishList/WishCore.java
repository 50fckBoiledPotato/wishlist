package com.pepper.WishList;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


public class WishCore extends Application
{
    private static ConfigurableApplicationContext context;
    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception 
    {
        this.stage = stage;
        scene = new Scene(loadFXML("wishlist"));
        stage.setScene(scene);
        stage.setTitle("Wishlist");        
        stage.show();
        
    }

    @Override
    public void init() throws Exception 
    {
        super.init();
        context = new SpringApplicationBuilder(WishListApplication.class).run();
    }
    
    private static Parent loadFXML(String fxml) throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(WishCore.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static ConfigurableApplicationContext getContext()
    {
        return  context;
    }
}
