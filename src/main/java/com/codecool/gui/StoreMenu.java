package com.codecool.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class StoreMenu extends Menu {

    StoreMenu(Main main) {
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
        List<Button> buttons = new ArrayList<>(main.getCategoryButtons(main.getStore(), new StoreCatMenu(main)));

        Button back = initButton("Back");
        back.relocate(575, 560);
        back.setOnAction(event -> main.mainMenu());
        buttons.add(back);

        return buttons;
    }

}
