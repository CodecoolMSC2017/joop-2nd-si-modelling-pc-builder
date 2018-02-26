package com.codecool.gui;

import com.codecool.api.Computer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class ComputersMenu extends Menu {

    protected Label left, middle, right;

    ComputersMenu(Main main) {
        this.main = main;
        init();
    }

    protected void init() {
        Pane pane = new Pane();
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());

        Label left = new Label();
        left.relocate(40, 10);
        left.setTextFill(Color.WHITE);
        left.setFont(new Font(16));
        left.setMaxWidth(380);
        this.left = left;

        Label middle = new Label();
        middle.relocate(450, 10);
        middle.setTextFill(Color.WHITE);
        middle.setFont(new Font(16));
        middle.setMaxWidth(380);
        this.middle = middle;

        Label right = new Label();
        right.relocate(860, 10);
        right.setTextFill(Color.WHITE);
        right.setFont(new Font(16));
        right.setMaxWidth(380);
        this.right = right;

        showPcs();

        pane.getChildren().addAll(left, middle, right);
        menu = new Scene(pane, main.WIDTH, main.HEIGHT);
        menu.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
    }

    protected List<Button> initMenuButtons() {
        List<Button> buttons = new ArrayList<>();

        Button back = initButton("Back");
        back.relocate(575, 680);
        back.setOnAction(event -> main.mainMenu());
        buttons.add(back);

        return buttons;
    }

    private void showPcs() {
        List<Computer> pcs = main.getInventory().getComputers();
        try {
            left.setText(pcs.get(0).toString());
            middle.setText(pcs.get(1).toString());
            right.setText(pcs.get(2).toString());
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

}
