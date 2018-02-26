package com.codecool.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class LoadMenu extends Menu {

    LoadMenu(Main main) {
        this.main = main;
        init();
    }

    protected void init() {
        Pane pane = new Pane();
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());

        Label label = new Label("Load last saved game?");
        label.setLayoutX(530);
        label.setLayoutY(350);
        label.setFont(new Font(20));
        label.setTextFill(Color.RED);
        pane.getChildren().add(label);

        menu = new Scene(pane, main.WIDTH, main.HEIGHT);
        menu.getStylesheets().addAll(getClass().getResource("/style.css").toExternalForm());
    }

    protected List<Button> initMenuButtons() {
        List<Button> buttons = new ArrayList<>();

        Button yes = initButton("Yes");
        yes.relocate(575, 440);
        yes.setOnAction(event -> main.load());
        buttons.add(yes);

        Button no = initButton("No");
        no.relocate(575, 480);
        no.setOnAction(event -> main.mainMenu());
        buttons.add(no);

        return buttons;
    }
}
