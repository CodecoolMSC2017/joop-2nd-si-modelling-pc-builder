package com.codecool.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class BuildMenu extends Menu {

    BuildMenu(Main main) {
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

        Button newPc = initButton("New PC");
        newPc.relocate(575, 280);
        newPc.setOnAction(event -> createNewPc());
        buttons.add(newPc);

        Button modify = initButton("Modify");
        modify.relocate(575, 320);
        modify.setOnAction(event -> modifyPc());
        buttons.add(modify);

        Button rename = initButton("Rename");
        rename.relocate(575, 360);
        rename.setOnAction(event -> renamePc());
        buttons.add(rename);

        Button disassemble = initButton("Disassemble");
        disassemble.relocate(575, 400);
        disassemble.setOnAction(event -> disassemblePc());
        buttons.add(disassemble);

        Button back = initButton("Back");
        back.relocate(575, 440);
        back.setOnAction(event -> main.mainMenu());
        buttons.add(back);

        return buttons;
    }

    private void disassemblePc() {
        main.setScene(new SelectPcMenu(main, getMenu(), "disassemble").getMenu());
    }

    private void createNewPc() {
        if (main.getInventory().getComputers().size() > 2) {
            main.setScene(new InfoScreen(main, "maxPc").getMenu());
            return;
        }
        main.setScene(new CreatePcMenu(main).getMenu());
    }

    private void modifyPc() {
        main.setScene(new SelectPcMenu(main, new BuildMenu(main).getMenu(), "modify").getMenu());
    }

    private void renamePc() {
        main.setScene(new SelectPcMenu(main, getMenu(), "rename").getMenu());
    }

}
