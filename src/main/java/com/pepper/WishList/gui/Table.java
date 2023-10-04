package com.pepper.WishList.gui;

import jakarta.persistence.Transient;
import java.lang.reflect.Field;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class Table<T> extends TableView<T> 
{
    
    public Table(Pane parent, Class<T> entityClass)
    {
        super();
        parent.getChildren().add(this);
        
        Field[] fields = entityClass.getDeclaredFields(); // lekéri az entitás változóit (aminek van gettere
        for (Field field : fields) 
        {       //!field.isSynthetic(): enumok kiszűrése és minden más változó kiszűrése amit nem én declaráltam osztályváltozóként
            if (!field.isSynthetic() && !field.isAnnotationPresent(Transient.class)) 
            {
                field.setAccessible(true); // private változókhoz hozzáférés
                String header = field.getName(); // field név: oszlop cím
                                
                //TableColumn<Income, Integer> idCol = new TableColumn<>("ID");
                TableColumn<T, String> column = new TableColumn<>(header);
                //idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                column.setCellValueFactory(cellData -> 
                {
                    try 
                    {   // itt derül ki a változók tulajdonsága
                        //Java reflection minden fieldet Object típusként kezel függetlenül a field tulajdonságától(String, int..)
                        //field.get(cellData.getValue()); egy Objectet ad vissza
                        Object value = field.get(cellData.getValue());
                        //azt az esetet kezeli amikor az adatbázisban lehet null értéke egy cellának, akkor egy üres String jelenik meg 'null' helyett
                        return value != null ? new SimpleStringProperty(value.toString()) : new SimpleStringProperty("");
                    }
                    catch (IllegalAccessException e) 
                    {
                        e.printStackTrace();
                        return new SimpleStringProperty("");
                    }
                });                
                column.setMinWidth(USE_PREF_SIZE);
                
                if(field.getType() == Boolean.class || field.getType() == boolean.class)
                {
                   
                }
                
                getColumns().add(column);
            }
        }
    }
    
    public void setItems(List<T> items) {
        getItems().clear();
        getItems().setAll(items);
    }
}