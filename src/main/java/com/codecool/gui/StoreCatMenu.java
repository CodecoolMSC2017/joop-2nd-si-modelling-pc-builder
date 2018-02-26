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

public class StoreCatMenu extends CategoryMenu {

    StoreCatMenu(Main main) {
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

        Button buy = initButton("Buy");
        buy.relocate(200, 240);
        buy.setOnAction(event -> buyItem());
        buttons.add(buy);

        Button details = initButton("Details");
        details.relocate(200, 280);
        details.setOnAction(event -> showDetails());
        buttons.add(details);

        Button back = initButton("Back");
        back.relocate(575, 560);
        back.setOnAction(event -> main.storeMenu());
        buttons.add(back);

        return buttons;
    }

    private void buyItem() {
        PCComponent component = main.getStore().getComponentByName(items.getValue());
        UserInventory inventory = main.getInventory();
        if (inventory.getMoney() < component.getValue()) {
            info.setTextFill(Color.RED);
            info.setText("Not enough money!");
            return;
        }
        inventory.addItem(component);
        inventory.manageMoney(-component.getValue());
        info.setTextFill(Color.GREEN);
        info.setText("Item purchased!");
        money.setText("Money: " + main.getInventory().getMoney());
    }

}
