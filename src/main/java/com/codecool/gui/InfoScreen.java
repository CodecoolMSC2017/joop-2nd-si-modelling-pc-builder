package com.codecool.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class InfoScreen extends Menu {

    private String type;
    private Button back;

    InfoScreen(Main main, String type) {
        this.main = main;
        this.type = type;
        main.save();
        init();
    }

    protected void init() {
        Pane pane = new Pane();
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());

        Label label;
        switch (type) {
            case "saved":
                label = savedLabel();
                break;
            case "loadFail":
                label = loadFailLabel();
                break;
            case "home":
                label = homeLabel();
                break;
            case "pcCreated":
                label = createdLabel();
                back.setOnAction(event -> main.buildMenu());
                break;
            case "maxPc":
                label = maxPcLabel();
                back.setOnAction(event -> main.buildMenu());
                break;
            case "pcNameExistsCreate":
                label = pcNameExists();
                back.setOnAction(event -> main.setScene(new CreatePcMenu(main).getMenu()));
                break;
            case "pcNameExistsRename":
                label = pcNameExists();
                back.setOnAction(event -> main.buildMenu());
                break;
            case "pcRenamed":
                label = pcRenamed();
                back.setOnAction(event -> main.buildMenu());
                break;
            case "pcDisassembled":
                label = pcDisassembled();
                back.setOnAction(event -> main.buildMenu());
                break;
            default:
                label = null;
                break;
        }
        pane.getChildren().add(label);

        menu = new Scene(pane, main.WIDTH, main.HEIGHT);
        menu.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
    }

    protected List<Button> initMenuButtons() {
        List<Button> buttons = new ArrayList<>();

        Button back = initButton("Back");
        back.relocate(575, 400);
        back.setOnAction(event -> main.mainMenu());
        this.back = back;
        buttons.add(back);

        return buttons;
    }

    private Label loadFailLabel() {
        Label label = new Label("Load failed, starting new game");
        label.setLayoutX(490);
        label.setLayoutY(350);
        label.setFont(new Font(20));
        label.setTextFill(Color.RED);
        return label;
    }

    private Label savedLabel() {
        Label label = new Label("Game saved!");
        label.setLayoutX(575);
        label.setLayoutY(350);
        label.setFont(new Font(20));
        label.setTextFill(Color.GREEN);
        return label;
    }

    private Label homeLabel() {
        Label label = new Label("You don't have functional pcs!");
        label.setLayoutX(490);
        label.setLayoutY(350);
        label.setFont(new Font(20));
        label.setTextFill(Color.RED);
        return label;
    }

    private Label createdLabel() {
        Label label = new Label("Pc created! Check Modify menu to start building.");
        label.setLayoutX(400);
        label.setLayoutY(350);
        label.setFont(new Font(20));
        label.setTextFill(Color.GREEN);
        return label;
    }

    private Label maxPcLabel() {
        Label label = new Label("You can have only 3 pcs at a time!");
        label.setLayoutX(480);
        label.setLayoutY(350);
        label.setFont(new Font(20));
        label.setTextFill(Color.RED);
        return label;
    }

    private Label pcNameExists() {
        Label label = new Label("This name already exists!");
        label.setLayoutX(510);
        label.setLayoutY(350);
        label.setFont(new Font(20));
        label.setTextFill(Color.RED);
        return label;
    }

    private Label pcRenamed() {
        Label label = new Label("Pc has been renamed!");
        label.setLayoutX(520);
        label.setLayoutY(350);
        label.setFont(new Font(20));
        label.setTextFill(Color.GREEN);
        return label;
    }

    private Label pcDisassembled() {
        Label label = new Label("Pc has been disassembled!");
        label.setLayoutX(510);
        label.setLayoutY(350);
        label.setFont(new Font(20));
        label.setTextFill(Color.GREEN);
        return label;
    }
}
