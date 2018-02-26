package com.codecool.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {

    public MainMenu(Main main) {
        this.main = main;
        init();
    }

    protected void init() {
        Pane pane = new Pane();
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());
        menu = new Scene(pane, main.WIDTH, main.HEIGHT);
        menu.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
    }

    protected List<Button> initMenuButtons() {
        List<Button> buttons = new ArrayList<>();

        Button home = initButton("Home");
        home.relocate(575, 200);
        home.setOnAction(event -> main.homeMenu());
        buttons.add(home);

        Button store = initButton("Store");
        store.relocate(575, 240);
        store.setOnAction(event -> main.storeMenu());
        buttons.add(store);

        Button build = initButton("Build");
        build.relocate(575, 280);
        build.setOnAction(event -> main.buildMenu());
        buttons.add(build);

        Button find = initButton("Computers");
        find.relocate(575, 320);
        find.setOnAction(event -> main.computersMenu());
        buttons.add(find);

        Button inventory = initButton("Inventory");
        inventory.relocate(575, 360);
        inventory.setOnAction(event -> main.inventoryMenu());
        buttons.add(inventory);

        Button save = initButton("Save");
        save.relocate(575, 400);
        save.setOnAction(event -> main.saveMenu());
        buttons.add(save);

        Button help = initButton("Help");
        help.relocate(575, 440);
        help.setOnAction(event -> main.helpMenu());
        buttons.add(help);

        Button exit = initButton("Exit");
        exit.relocate(575, 480);
        exit.setOnAction(event -> main.exitMenu());
        buttons.add(exit);

        return buttons;
    }
}
