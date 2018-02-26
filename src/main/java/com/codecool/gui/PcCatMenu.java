package com.codecool.gui;

import com.codecool.api.Computer;
import com.codecool.api.components.PCComponent;
import com.codecool.api.exceptions.ComponentsDoNotMatchException;
import com.codecool.api.exceptions.NoMoreRoomException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class PcCatMenu extends CategoryMenu {

    private ChoiceBox<String> inventoryItems;
    private String type;
    private Label invInfo;

    PcCatMenu(Main main) {
        this.main = main;
        init();
    }

    protected void init() {
        super.init();
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());

        Label builtInLabel = new Label("Built in components");
        builtInLabel.setLayoutX(200);
        builtInLabel.setLayoutY(150);
        builtInLabel.setTextFill(Color.WHITE);
        builtInLabel.setFont(new Font(20));
        pane.getChildren().add(builtInLabel);

        Label inventoryLabel = new Label("Inventory");
        inventoryLabel.setLayoutX(200);
        inventoryLabel.setLayoutY(400);
        inventoryLabel.setTextFill(Color.WHITE);
        inventoryLabel.setFont(new Font(20));
        pane.getChildren().add(inventoryLabel);

        Label invInfo = new Label();
        invInfo.setLayoutX(200);
        invInfo.setLayoutY(580);
        invInfo.setFont(new Font(20));
        this.invInfo = invInfo;
        pane.getChildren().add(invInfo);

        menu = new Scene(pane, main.WIDTH, main.HEIGHT);
        menu.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
    }

    protected List<Button> initMenuButtons() {
        List<Button> buttons = new ArrayList<>();

        Button remove = initButton("Remove");
        remove.relocate(200, 240);
        remove.setOnAction(event -> removeItem());
        buttons.add(remove);

        Button details = initButton("Details");
        details.relocate(200, 280);
        details.setOnAction(event -> showDetails());
        buttons.add(details);

        Button add = initButton("Add");
        add.relocate(200, 490);
        add.setOnAction(event -> addItem());
        buttons.add(add);

        Button invDetails = initButton("Details");
        invDetails.relocate(200, 530);
        invDetails.setOnAction(event -> showInvDetails());
        buttons.add(invDetails);

        Button back = initButton("Back");
        back.relocate(575, 530);
        back.setOnAction(event -> main.setScene(new ModifyMenu(main, (Computer) inventory).getMenu()));
        buttons.add(back);

        return buttons;
    }

    private void showInvDetails() {
        PCComponent component = main.getStore().getComponentByName(inventoryItems.getValue());
        if (component == null) {
            invInfo.setTextFill(Color.RED);
            invInfo.setText("No items!");
            return;
        }
        details.setText(component.details());
        invInfo.setText("");
    }

    private void addItem() {
        PCComponent component = main.getInventory().getComponentByName(inventoryItems.getValue());
        if (component == null) {
            invInfo.setTextFill(Color.RED);
            invInfo.setText("No items!");
            return;
        }
        try {
            main.currentPc.addComponent(component);
        } catch (NoMoreRoomException | ComponentsDoNotMatchException e) {
            invInfo.setTextFill(Color.RED);
            invInfo.setText(e.getMessage());
            return;
        }
        main.getInventory().removeItem(component);
        inventoryItems.getItems().remove(component.toString());
        inventoryItems.getSelectionModel().select(0);

        items.getItems().add(component.toString());
        items.getSelectionModel().select(0);

        invInfo.setTextFill(Color.GREEN);
        invInfo.setText("Item added!");
    }

    private void removeItem() {
        PCComponent component = inventory.getComponentByName(items.getValue());
        if (component == null) {
            info.setTextFill(Color.RED);
            info.setText("No items!");
            return;
        }
        main.getInventory().addItem(component);
        main.currentPc.removeItem(component);
        inventoryItems.getItems().add(component.toString());
        inventoryItems.getSelectionModel().select(0);

        items.getItems().remove(component.toString());
        items.getSelectionModel().select(0);

        info.setTextFill(Color.GREEN);
        info.setText("Item removed!");
    }

    public void setType(String type) {
        this.type = type;
        setInventoryItems();
    }

    private void setInventoryItems() {
        List<? extends PCComponent> items;
        switch (type) {
            case "case":
                items = main.getInventory().getCases();
                break;
            case "psu":
                items = main.getInventory().getPsus();
                break;
            case "motherboard":
                items = main.getInventory().getMotherboards();
                break;
            case "cpu":
                items = main.getInventory().getCpus();
                break;
            case "heatsink":
                items = main.getInventory().getHeatsinks();
                break;
            case "fan":
                items = main.getInventory().getFans();
                break;
            case "memory":
                items = main.getInventory().getMemories();
                break;
            case "gpu":
                items = main.getInventory().getGpus();
                break;
            case "ssd":
                items = main.getInventory().getSsds();
                break;
            case "hdd":
                items = main.getInventory().getHdds();
                break;
            default:
                items = new ArrayList<>();
                break;
        }
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        for (PCComponent component : items) {
            choiceBox.getItems().add(component.toString());
        }
        choiceBox.getSelectionModel().select(0);
        choiceBox.relocate(200, 450);
        choiceBox.setMinWidth(130);
        this.inventoryItems = choiceBox;
        pane.getChildren().add(choiceBox);
    }
}
