package com.codecool.gui;

import com.codecool.api.Inventory;
import com.codecool.api.components.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public abstract class CategoryMenu extends Menu {

    protected Inventory inventory;

    protected Pane pane = new Pane();
    protected Label money;
    protected ChoiceBox<String> items;
    protected Label details;
    protected Label info;

    protected void init() {
        Label details = new Label();
        details.setLayoutX(800);
        details.setLayoutY(200);
        details.setFont(new Font(20));
        details.setTextFill(Color.WHITE);
        this.details = details;
        pane.getChildren().add(details);

        Label info = new Label();
        info.setLayoutX(200);
        info.setLayoutY(330);
        info.setFont(new Font(20));
        this.info = info;
        pane.getChildren().add(info);
    }

    protected void showDetails() {
        PCComponent component = main.getStore().getComponentByName(items.getValue());
        if (component == null) {
            info.setTextFill(Color.RED);
            info.setText("No items!");
            return;
        }
        details.setText(component.details());
        info.setText("");
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public <T extends PCComponent> void setItems(List<T> items) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        for (PCComponent component : items) {
            choiceBox.getItems().add(component.toString());
        }
        choiceBox.getSelectionModel().select(0);
        choiceBox.relocate(200, 200);
        choiceBox.setMinWidth(130);
        this.items = choiceBox;
        pane.getChildren().add(choiceBox);
    }

}
