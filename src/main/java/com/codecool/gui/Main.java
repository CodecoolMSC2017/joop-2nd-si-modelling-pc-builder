package com.codecool.gui;

import com.codecool.api.Computer;
import com.codecool.api.Inventory;
import com.codecool.api.Store;
import com.codecool.api.UserInventory;
import com.codecool.api.components.PCComponent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    final int WIDTH = 1280;
    final int HEIGHT = 720;

    private Stage primaryStage;

    private UserInventory inventory = new UserInventory(1000);
    private Store store = new Store();
    Computer currentPc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PC Builder");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(new LoadMenu(this).getMenu());
        this.primaryStage.show();
    }

    public UserInventory getInventory() {
        return inventory;
    }

    public Store getStore() {
        return store;
    }

    void setScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    void mainMenu() {
        primaryStage.setScene(new MainMenu(this).getMenu());
    }

    public void homeMenu() {
        if (inventory.getAmountOfFunctionalPCs() == 0) {
            primaryStage.setScene(new InfoScreen(this, "home").getMenu());
        } else {
            primaryStage.setScene(new HomeMenu(this).getMenu());
        }
    }

    public void storeMenu() {
        primaryStage.setScene(new StoreMenu(this).getMenu());
    }

    public void buildMenu() {
        primaryStage.setScene(new BuildMenu(this).getMenu());
    }

    public void computersMenu() {
        primaryStage.setScene(new ComputersMenu(this).getMenu());
    }

    public void inventoryMenu() {
        primaryStage.setScene(new InventoryMenu(this).getMenu());
    }

    public void saveMenu() {
        primaryStage.setScene(new InfoScreen(this, "saved").getMenu());
    }

    public void helpMenu() {
        primaryStage.setScene(new HelpMenu(this).getMenu());
    }

    public void exitMenu() {
        primaryStage.setScene(new ExitMenu(this).getMenu());
    }

    List<Button> getCategoryButtons(Inventory inv, CategoryMenu menu) {
        if (inv instanceof Computer) {
            currentPc = (Computer) inv;
        }
        List<Button> buttons = new ArrayList<>();
        menu.setInventory(inv);

        Button cases = initButton("Cases (" + inv.getCases().size() + ")");
        cases.relocate(560, 160);
        cases.setOnAction(event -> listItems(inv.getCases(), menu, "case"));
        buttons.add(cases);

        Button psus = initButton("Power supplies (" + inv.getPsus().size() + ")");
        psus.relocate(560, 200);
        psus.setOnAction(event -> listItems(inv.getPsus(), menu, "psu"));
        buttons.add(psus);

        Button mobos = initButton("Motherboards (" + inv.getMotherboards().size() + ")");
        mobos.relocate(560, 240);
        mobos.setOnAction(event -> listItems(inv.getMotherboards(), menu, "motherboard"));
        buttons.add(mobos);

        Button cpus = initButton("Processors (" + inv.getCpus().size() + ")");
        cpus.relocate(560, 280);
        cpus.setOnAction(event -> listItems(inv.getCpus(), menu, "cpu"));
        buttons.add(cpus);

        Button heatsinks = initButton("Heatsinks (" + inv.getHeatsinks().size() + ")");
        heatsinks.relocate(560, 320);
        heatsinks.setOnAction(event -> listItems(inv.getHeatsinks(), menu, "heatsink"));
        buttons.add(heatsinks);

        Button fans = initButton("Fans (" + inv.getFans().size() + ")");
        fans.relocate(560, 360);
        fans.setOnAction(event -> listItems(inv.getFans(), menu, "fan"));
        buttons.add(fans);

        Button rams = initButton("Memories (" + inv.getMemories().size() + ")");
        rams.relocate(560, 400);
        rams.setOnAction(event -> listItems(inv.getMemories(), menu, "memory"));
        buttons.add(rams);

        Button gpus = initButton("Graphics cards (" + inv.getGpus().size() + ")");
        gpus.relocate(560, 440);
        gpus.setOnAction(event -> listItems(inv.getGpus(), menu, "gpu"));
        buttons.add(gpus);

        Button ssds = initButton("SSDs (" + inv.getSsds().size() + ")");
        ssds.relocate(560, 480);
        ssds.setOnAction(event -> listItems(inv.getSsds(), menu, "ssd"));
        buttons.add(ssds);

        Button hdds = initButton("HDDs (" + inv.getHdds().size() + ")");
        hdds.relocate(560, 520);
        hdds.setOnAction(event -> listItems(inv.getHdds(), menu, "hdd"));
        buttons.add(hdds);

        return buttons;
    }

    private <T extends PCComponent> void listItems(List<T> items, CategoryMenu menu, String type) {
        menu.setItems(items);
        if (menu instanceof PcCatMenu) {
            PcCatMenu pcMenu = (PcCatMenu) menu;
            pcMenu.setType(type);
        }
        primaryStage.setScene(menu.getMenu());
    }

    private Button initButton(String title) {
        Button button = new Button(title);
        button.setPrefSize(160, 20);
        return button;
    }

    void load() {
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + "/pc-builder-save.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            inventory = (UserInventory) in.readObject();
            in.close();
            fileIn.close();
            mainMenu();
        } catch (IOException | ClassNotFoundException e) {
            primaryStage.setScene(new InfoScreen(this, "loadFail").getMenu());
        }
    }

    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home") + "/pc-builder-save.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(inventory);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
