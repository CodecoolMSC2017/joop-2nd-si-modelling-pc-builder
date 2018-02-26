package com.codecool.gui;

import com.codecool.api.Computer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class SelectPcMenu extends Menu {

    private Pane pane;
    private ChoiceBox<String> pcs;

    private Scene back;
    private String forward;

    SelectPcMenu(Main main, Scene back, String forward) {
        this.main = main;
        this.back = back;
        this.forward = forward;
        init();
        setItems();
    }

    protected void init() {
        Pane pane = new Pane();
        this.pane = pane;
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());
        menu = new Scene(pane, main.WIDTH, main.HEIGHT);
        menu.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
    }

    protected List<Button> initMenuButtons() {
        List<Button> buttons = new ArrayList<>();

        Button select = initButton("Select");
        select.relocate(575, 360);
        select.setOnAction(event -> selectPc());
        buttons.add(select);

        Button back = initButton("Back");
        back.relocate(575, 400);
        back.setOnAction(event -> stepBack());
        buttons.add(back);

        return buttons;
    }

    private void stepBack() {
        main.setScene(back);
    }

    private void selectPc() {
        Computer pc = main.getInventory().getPcByName(pcs.getValue());
        if (pc == null) {
            return;
        }
        switch (forward) {
            case "modify":
                main.setScene(new ModifyMenu(main, pc).getMenu());
                break;
            case "rename":
                main.setScene(new RenameMenu(main, pc).getMenu());
                break;
            case "disassemble":
                main.setScene(new DisassembleMenu(main, pc).getMenu());
                break;
        }
    }

    private void setItems() {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        for (Computer pc : main.getInventory().getComputers()) {
            choiceBox.getItems().add(pc.getName());
        }
        choiceBox.getSelectionModel().select(0);
        choiceBox.relocate(550, 300);
        choiceBox.setMinWidth(180);
        this.pcs = choiceBox;
        pane.getChildren().add(choiceBox);
    }

}
