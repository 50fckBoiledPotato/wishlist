package com.pepper.WishList.gui;

import com.pepper.WishList.model.Doneable;
import jakarta.persistence.Transient;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class Table<T> extends TableView<T> 
{
    
    public Table(Pane parent, Class<T> entityClass)
    {
        super();
        parent.getChildren().add(this);
        
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) 
        {       
            if (!field.isSynthetic() && !field.isAnnotationPresent(Transient.class)) 
            {
                field.setAccessible(true); // private változókhoz hozzáférés
                String header = field.getName(); // field név: oszlop cím
                
                
                TableColumn<T, String> column = new TableColumn<>(header);                
                column.setCellValueFactory(cellData -> 
                {
                    try 
                    {  
                        Object value = field.get(cellData.getValue());                        
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
                {}                
                getColumns().add(column);
            }
        }
    }
    
    public void addActionColumn(String buttonText, BiConsumer<T, Integer> onClick) // gomb hozzáadása
    {
        TableColumn<T, String> column = new TableColumn<>();
        
        Callback<TableColumn<T, String>, TableCell<T, String>> factory;
        factory = new Callback<>() 
        {
            
            @Override
            public TableCell<T, String> call(TableColumn<T, String> param) 
            {
                TableCell<T, String> cell = new TableCell<>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        super.updateItem(item, empty); 

                        if(empty)
                        {
                            setGraphic(null);
                        }
                        else
                        {
                            Button button = new Button(buttonText);
                            
                            T ent = getTableRow().getItem();
                            if(ent != null && ent instanceof Doneable)
                            {
                                button.setDisable(((Doneable) ent).isDone());
                            }
                            
                            button.setOnAction(evt -> 
                            {
                                int index = getIndex();
                                T entity = getTableRow().getItem();

                                onClick.accept(entity, index);
                                
                            });
                            
                            setGraphic(button);
                        }
                        setText(null);
                    }
                };
                return cell;
            }
            
        };
        column.setCellFactory(factory);
        getColumns().add(column);
    }
    final List<Button> buttonList = new ArrayList<>();
    public void addActionColumn(String buttonText, BiConsumer<T, Integer> onClick, BooleanBinding enableCondition) // gomb hozzáadása
    {
        TableColumn<T, String> column = new TableColumn<>();
        buttonList.clear();
        
        Callback<TableColumn<T, String>, TableCell<T, String>> factory;
        factory = new Callback<>() 
        {
            
            @Override
            public TableCell<T, String> call(TableColumn<T, String> param) 
            {
                TableCell<T, String> cell = new TableCell<>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        super.updateItem(item, empty); 

                        if(empty)
                        {
                            setGraphic(null);
                        }
                        else
                        {
                            Button button = new Button(buttonText);
                            
                            button.setOnAction(evt -> 
                            {
                                int index = getIndex();
                                T entity = getTableRow().getItem();

                                onClick.accept(entity, index);
                            });
                            
                            button.disableProperty().bind(enableCondition.not());

                            buttonList.add(button);
                            setGraphic(button);
                        }
                        setText(null);
                    }
                };
                return cell;
            }
            
        };
        column.setCellFactory(factory);
        getColumns().add(column);
        System.out.println("table, action col added, buttonList.isEmpty: " + buttonList.isEmpty());
    }

    public List<Button> getButtonList() {
        return buttonList;
    }
    
    public void setItems(List<T> items) {
        getItems().clear();
        getItems().setAll(items);
    }
}