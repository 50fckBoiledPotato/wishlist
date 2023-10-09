package com.pepper.WishList.model;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Button;


public class ActionButton 
{
    private Button button;
    private BooleanBinding enableCondition;

    public ActionButton(Button button, BooleanBinding enableCondition) {
        this.button = button;
        this.enableCondition = enableCondition;
    }

    public Button getButton() {
        return button;
    }

    public BooleanBinding getEnableCondition() {
        return enableCondition;
    }
}
