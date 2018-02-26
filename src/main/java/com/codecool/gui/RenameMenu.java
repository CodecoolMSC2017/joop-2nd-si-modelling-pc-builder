package com.codecool.gui;

import com.codecool.api.Computer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class RenameMenu extends Menu {

    private TextArea textArea;
    private Computer pc;

    RenameMenu(Main main, Computer pc) {
        this.main = main;
        this.pc = pc;
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

        Button rename = initButton("Rename");
        rename.relocate(575, 320);
        rename.setOnAction(event -> renamePc());
        buttons.add(rename);

        Button back = initButton("Back");
        back.relocate(575, 360);
        back.setOnAction(event -> main.buildMenu());
        buttons.add(back);

        return buttons;
    }

    private void renamePc() {
        String newName = textArea.getText();
        if (newName.equals("")) {
            return;
        } else if (main.getInventory().checkIfNameExists(newName)) {
            main.setScene(new InfoScreen(main, "pcNameExistsRename").getMenu());
            return;
        }
        pc.setName(newName);
        main.setScene(new InfoScreen(main, "pcRenamed").getMenu());
    }
}
