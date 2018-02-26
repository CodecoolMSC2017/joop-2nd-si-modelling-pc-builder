package com.codecool.gui;

import com.codecool.api.Computer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ModifyMenu extends Menu {

    private Computer pc;

    public ModifyMenu(Main main, Computer pc) {
        this.main = main;
        this.pc = pc;
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
        List<Button> buttons = new ArrayList<>(main.getCategoryButtons(pc, new PcCatMenu(main)));

        Button back = initButton("Back");
        back.relocate(575, 560);
        back.setOnAction(event -> main.buildMenu());
        buttons.add(back);

        return buttons;
    }

}
