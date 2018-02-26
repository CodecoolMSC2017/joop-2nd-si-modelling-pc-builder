package com.codecool.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class HelpMenu extends Menu {

    HelpMenu(Main main) {
        this.main = main;
        init();
    }

    protected void init() {
        Pane pane = new Pane();
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());

        Label label = new Label("This game is about building pcs from components purchased in the store.\n" +
                "You can then use them to mine cryptocurrency. If you reach $1 000 000 you win the game.\n\n" +
                "Main menu buttons:\n" +
                "Home -> here you can use your pcs\n" +
                "Store -> browse and buy components\n" +
                "Build -> modify or create a pc\n" +
                "Computers -> display your pcs and their components\n" +
                "Inventory -> browse and sell your components that are not built in\n" +
                "Save -> save the current state of the game\n" +
                "Help -> display this tutorial\n" +
                "Exit -> close the game after saving automatically");
        label.setLayoutX(200);
        label.setLayoutY(150);
        label.setFont(new Font(20));
        label.setTextFill(Color.WHITE);
        pane.getChildren().add(label);

        menu = new Scene(pane, main.WIDTH, main.HEIGHT);
        menu.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
    }

    protected List<Button> initMenuButtons() {
        List<Button> buttons = new ArrayList<>();

        Button back = initButton("Back");
        back.relocate(575, 550);
        back.setOnAction(event -> main.mainMenu());
        buttons.add(back);

        return buttons;
    }
}
