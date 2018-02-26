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

public class HomeMenu extends Menu {

    private Pane pane;
    private Label left, middle, right;

    HomeMenu(Main main) {
        this.main = main;
        init();
    }

    protected void init() {
        Pane pane = new Pane();
        this.pane = pane;
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());

        Label money = new Label("Money: $" + main.getInventory().getMoney());
        money.relocate(575, 500);
        money.setFont(new Font(20));
        money.setTextFill(Color.WHITE);
        pane.getChildren().add(money);

        Label left = new Label();
        left.relocate(40, 110);
        left.setTextFill(Color.WHITE);
        left.setFont(new Font(16));
        left.setMaxWidth(380);
        this.left = left;

        Label middle = new Label();
        middle.relocate(450, 110);
        middle.setTextFill(Color.WHITE);
        middle.setFont(new Font(16));
        middle.setMaxWidth(380);
        this.middle = middle;

        Label right = new Label();
        right.relocate(860, 110);
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

        Button mine = initButton("Mine");
        mine.relocate(575, 540);
        mine.setOnAction(event -> mine());
        buttons.add(mine);

        Button back = initButton("Back");
        back.relocate(575, 580);
        back.setOnAction(event -> main.mainMenu());
        buttons.add(back);

        return buttons;
    }

    private void mine() {
        main.getInventory().manageMoney(main.getInventory().mine());
        main.setScene(new HomeMenu(main).getMenu());
    }

    private void showPcs() {
        List<Computer> pcs = main.getInventory().getComputers();
        try {
            if (pcs.get(0).getFunctional()) {
                left.setText(pcs.get(0).getHomeInfo());
                initPcButton1();
            } else {
                left.setText("This pc is not functional.");
            }
            if (pcs.get(1).getFunctional()) {
                middle.setText(pcs.get(1).getHomeInfo());
                initPcButton2();
            } else {
                middle.setText("This pc is not functional.");
            }
            if (pcs.get(2).getFunctional()) {
                right.setText(pcs.get(2).getHomeInfo());
                initPcButton3();
            } else {
                right.setText("This pc is not functional.");
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    private void initPcButton1() {
        Button button = initPcButton(main.getInventory().getComputers().get(0));
        button.relocate(165, 70);
        pane.getChildren().add(button);
    }

    private void initPcButton2() {
        Button button = initPcButton(main.getInventory().getComputers().get(1));
        button.relocate(575, 70);
        pane.getChildren().add(button);
    }

    private void initPcButton3() {
        Button button = initPcButton(main.getInventory().getComputers().get(2));
        button.relocate(985, 70);
        pane.getChildren().add(button);
    }

    private Button initPcButton(Computer pc) {
        Button button;
        if (pc.isTurnedOn()) {
            button = initButton("Turn off");
            button.setOnAction(event -> turnOffPc(pc));
        } else {
            button = initButton("Turn on");
            button.setOnAction(event -> turnOnPc(pc));
        }
        return button;
    }

    private void turnOffPc(Computer pc) {
        pc.turnOff();
        pc.updateTemperature(false);
        main.setScene(new HomeMenu(main).getMenu());
    }

    private void turnOnPc(Computer pc) {
        pc.turnOn();
        pc.updateTemperature(false);
        main.setScene(new HomeMenu(main).getMenu());
    }

}
