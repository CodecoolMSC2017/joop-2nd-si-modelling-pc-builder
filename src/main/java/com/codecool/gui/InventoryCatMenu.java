package com.codecool.gui;

import com.codecool.api.UserInventory;
import com.codecool.api.components.PCComponent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class InventoryCatMenu extends CategoryMenu {

    InventoryCatMenu(Main main) {
        this.main = main;
        init();
    }

    protected void init() {
        super.init();
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());

        Label money = new Label("Money: " + main.getInventory().getMoney());
        this.money = money;
        money.setLayoutX(200);
        money.setLayoutY(150);
        money.setTextFill(Color.WHITE);
        money.setFont(new Font(20));
        pane.getChildren().add(money);

        menu = new Scene(pane, main.WIDTH, main.HEIGHT);
        menu.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
    }

    protected List<Button> initMenuButtons() {
        List<Button> buttons = new ArrayList<>();

        Button buy = initButton("Sell");
        buy.relocate(200, 240);
        buy.setOnAction(event -> sellItem());
        buttons.add(buy);

        Button details = initButton("Details");
        details.relocate(200, 280);
        details.setOnAction(event -> showDetails());
        buttons.add(details);

        Button back = initButton("Back");
        back.relocate(575, 560);
        back.setOnAction(event -> main.inventoryMenu());
        buttons.add(back);

        return buttons;
    }

    private void sellItem() {
        PCComponent component = main.getInventory().getComponentByName(items.getValue());
        if (component == null) {
            info.setTextFill(Color.RED);
            info.setText("No items!");
            return;
        }
        items.getItems().remove(items.getValue());
        items.getSelectionModel().select(0);
        UserInventory inventory = main.getInventory();
        inventory.removeItem(component);
        inventory.manageMoney(component.getValue());
        info.setTextFill(Color.GREEN);
        info.setText("Item sold!");
        money.setText("Money: " + main.getInventory().getMoney());
    }

}
