package com.codecool.gui;

import com.codecool.api.Computer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class CreatePcMenu extends Menu {

    private TextArea textArea;

    CreatePcMenu(Main main) {
        this.main = main;
        init();
    }

    protected void init() {
        Pane pane = new Pane();
        pane.setId("pane");
        pane.getChildren().addAll(initMenuButtons());

        TextArea textArea = new TextArea();
        textArea.setPrefSize(200, 0);
        textArea.relocate(540, 260);
        this.textArea = textArea;
        pane.getChildren().add(textArea);

        menu = new Scene(pane, main.WIDTH, main.HEIGHT);
        menu.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
    }

    protected List<Button> initMenuButtons() {
        List<Button> buttons = new ArrayList<>();

        Button create = initButton("Create");
        create.relocate(575, 320);
        create.setOnAction(event -> createPc());
        buttons.add(create);

        Button back = initButton("Back");
        back.relocate(575, 360);
        back.setOnAction(event -> main.buildMenu());
        buttons.add(back);

        return buttons;
    }

    private void createPc() {
        String name = textArea.getText();
        if (name.equals("")) {
            return;
        } else if (main.getInventory().checkIfNameExists(name)) {
            main.setScene(new InfoScreen(main, "pcNameExistsCreate").getMenu());
            return;
        }
        Computer pc = new Computer(name);
        main.getInventory().addComputer(pc);
        main.setScene(new InfoScreen(main, "pcCreated").getMenu());
    }
}
