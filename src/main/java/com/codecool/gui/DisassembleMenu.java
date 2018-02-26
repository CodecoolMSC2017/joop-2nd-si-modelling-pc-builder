package com.codecool.gui;

import com.codecool.api.Computer;
import com.codecool.api.UserInventory;
import com.codecool.api.components.PCComponent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class DisassembleMenu extends Menu {

    private Computer pc;

    DisassembleMenu(Main main, Computer pc) {
        this.main = main;
        this.pc = pc;
        init();
    }

    protected void init() {
        Pane pane = new Pane();
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());

        Label label = new Label("Are you sure you want to disassemble this pc?");
        label.relocate(420, 280);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font(20));
        pane.getChildren().add(label);

        menu = new Scene(pane, main.WIDTH, main.HEIGHT);
        menu.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
    }

    protected List<Button> initMenuButtons() {
        List<Button> buttons = new ArrayList<>();

        Button disassemble = initButton("Disassemble");
        disassemble.relocate(575, 320);
        disassemble.setOnAction(event -> disassemblePc());
        buttons.add(disassemble);

        Button back = initButton("Back");
        back.relocate(575, 360);
        back.setOnAction(event -> main.buildMenu());
        buttons.add(back);

        return buttons;
    }

    private void disassemblePc() {
        UserInventory inventory = main.getInventory();
        for (PCComponent component : pc.getAllComponents()) {
            inventory.addItem(component);
        }
        inventory.removeComputer(pc);
        main.setScene(new InfoScreen(main, "pcDisassembled").getMenu());
    }
}
