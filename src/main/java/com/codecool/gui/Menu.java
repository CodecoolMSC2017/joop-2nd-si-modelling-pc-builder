package com.codecool.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.List;

abstract class Menu {

    protected Main main;
    protected Scene menu;

    Scene getMenu() {
        return menu;
    }

    protected abstract void init();

    protected abstract List<Button> initMenuButtons();

    protected Button initButton(String title) {
        Button button = new Button(title);
        button.setPrefSize(130, 20);
        return button;
    }

}
