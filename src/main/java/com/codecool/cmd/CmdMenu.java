package com.codecool.cmd;

import com.codecool.api.Inventory;
import com.codecool.api.Store;
import com.codecool.api.UserInventory;
import com.codecool.api.components.PCComponent;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class CmdMenu {

    private String saveFilePath = System.getProperty("user.home") + "/pc-builder-save.ser";

    private UserInventory inventory = new UserInventory(1000);
    private Store store = new Store();

    private Scanner userInput = new Scanner(System.in);

    CmdMenu() {
        if (new File(saveFilePath).exists()) {
            load();
        } else {
            System.out.println("New game started!");
        }
    }

    void start() {
        String[] commands = new String[]{
                "home",
                "store",
                "build",
                "computers",
                "inventory",
                "save",
                "help",
                "exit"
        };

        while (true) {
            showMenu("Main Menu", commands);
            String choice = getInput();

            switch (choice) {
                case "home":
                    homeMenu();
                    break;
                case "store":
                    storeMenu();
                    break;
                case "build":
                    buildMenu();
                    break;
                case "computers":
                    computersMenu();
                    break;
                case "inventory":
                    inventoryMenu();
                    break;
                case "save":
                    saveMenu();
                    break;
                case "help":
                    helpMenu();
                    break;
                case "exit":
                    saveGame();
                    System.exit(0);
                default:
                    System.out.println("Unknown command: " + choice);
                    break;
            }
        }
    }

    private void homeMenu() {

    }

    private void storeMenu() {
        List<? extends PCComponent> items;
        while (true) {
            String choice = chooseCategory(store);
            switch (choice) {
                case "back":
                    return;
                case "0":
                    items = store.getCases();
                    break;
                case "1":
                    items = store.getPsus();
                    break;
                case "2":
                    items = store.getMotherboards();
                    break;
                case "3":
                    items = store.getCpus();
                    break;
                case "4":
                    items = store.getHeatsinks();
                    break;
                case "5":
                    items = store.getFans();
                    break;
                case "6":
                    items = store.getMemories();
                    break;
                case "7":
                    items = store.getGpus();
                    break;
                case "8":
                    items = store.getSsds();
                    break;
                case "9":
                    items = store.getHdds();
                    break;
                default:
                    System.out.println("Wrong input!");
                    continue;
            }
            storeItemsMenu(items);
        }
    }

    private void storeItemsMenu(List<? extends PCComponent> items) {
        String[] options = new String[]{"buy", "details", "back"};
        while (true) {
            displayCategory(items);
            showMenu("Money: " + inventory.getMoney(), options);
            String choice = getInput();
            switch (choice) {
                case "back":
                    return;
                case "buy":
                    buyItem(items);
                    break;
                case "details":
                    showDetails(items);
                    break;
                default:
                    System.out.println("Unknown command: " + choice);
            }
        }
    }

    private void buyItem(List<? extends PCComponent> items) {
        PCComponent component = chooseItem(items);
        if (component == null) {
            return;
        }
        if (inventory.getMoney() < component.getValue()) {
            System.out.println("Not enough money!");
            return;
        }
        inventory.addItem(component);
        inventory.manageMoney(-component.getValue());
        System.out.println("Item purchased!");
    }

    private void showDetails(List<? extends PCComponent> items) {
        PCComponent component = chooseItem(items);
        if (component == null) {
            return;
        }
        System.out.println("\n" + component.details());
    }

    private PCComponent chooseItem(List<? extends PCComponent> items) {
        while (true) {
            try {
                displayCategory(items);
                System.out.print("\nChoose an item by it's number (or back): ");
                String choice = getInput();
                if (choice.equals("back")) {
                    return null;
                }
                int choiceAsInt = Integer.parseInt(choice);
                return items.get(choiceAsInt);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
            }
        }
    }

    private void buildMenu() {

    }

    private void computersMenu() {

    }

    private void inventoryMenu() {
        List<? extends PCComponent> items;
        while (true) {
            String choice = chooseCategory(inventory);
            switch (choice) {
                case "back":
                    return;
                case "0":
                    items = inventory.getCases();
                    break;
                case "1":
                    items = inventory.getPsus();
                    break;
                case "2":
                    items = inventory.getMotherboards();
                    break;
                case "3":
                    items = inventory.getCpus();
                    break;
                case "4":
                    items = inventory.getHeatsinks();
                    break;
                case "5":
                    items = inventory.getFans();
                    break;
                case "6":
                    items = inventory.getMemories();
                    break;
                case "7":
                    items = inventory.getGpus();
                    break;
                case "8":
                    items = inventory.getSsds();
                    break;
                case "9":
                    items = inventory.getHdds();
                    break;
                default:
                    System.out.println("Wrong input!");
                    continue;
            }
            inventoryItemsMenu(items);
        }
    }

    private void inventoryItemsMenu(List<? extends PCComponent> items) {
        String[] options = new String[]{"sell", "details", "back"};
        while (true) {
            displayCategory(items);
            showMenu("Money: " + inventory.getMoney(), options);
            String choice = getInput();
            switch (choice) {
                case "back":
                    return;
                case "sell":
                    sellItem(items);
                    break;
                case "details":
                    showDetails(items);
                    break;
                default:
                    System.out.println("Unknown command: " + choice);
            }
        }
    }

    private void sellItem(List<? extends PCComponent> items) {
        PCComponent component = chooseItem(items);
        if (component == null) {
            return;
        }
        inventory.removeItem(component);
        inventory.manageMoney(component.getValue());
        System.out.println("Item sold!");
    }

    private void saveMenu() {
        saveGame();
    }

    private void helpMenu() {
        System.out.println("\n" +
                "This game is about building pcs from components purchased in the store.\n" +
                "You can then use them to mine cryptocurrency. If you reach $1 000 000 you win the game.\n\n" +
                "Main menu options:\n" +
                "Home -> here you can use your pcs\n" +
                "Store -> browse and buy components\n" +
                "Build -> modify or create a pc\n" +
                "Computers -> display your pcs and their components\n" +
                "Inventory -> browse and sell your components that are not built in\n" +
                "Save -> save the current state of the game\n" +
                "Help -> display this tutorial\n" +
                "Exit -> close the game after saving automatically"
        );
    }

    private void displayCategory(List<? extends PCComponent> components) {
        int counter = 0;
        System.out.println();
        for (PCComponent component : components) {
            System.out.println(counter + ") " + component.toString());
            counter++;
        }
    }

    private String chooseCategory(Inventory inventory) {
        System.out.println("\nCommands: back (or type a number)\n" +
                "0) Cases (" + inventory.getCases().size() + ")\n" +
                "1) Power supplies (" + inventory.getPsus().size() + ")\n" +
                "2) Motherboards (" + inventory.getMotherboards().size() + ")\n" +
                "3) Processors (" + inventory.getCpus().size() + ")\n" +
                "4) Heatsinks (" + inventory.getHeatsinks().size() + ")\n" +
                "5) Fans (" + inventory.getFans().size() + ")\n" +
                "6) Memories (" + inventory.getMemories().size() + ")\n" +
                "7) Graphics cards (" + inventory.getGpus().size() + ")\n" +
                "8) Solid state drives (" + inventory.getSsds().size() + ")\n" +
                "9) Hard disk drives (" + inventory.getHdds().size() + ")");
        return getInput();
    }

    private void showMenu(String title, String[] commands) {
        System.out.println("\n" + title);
        System.out.print("Commands: ");
        for (String command : commands) {
            System.out.print(command + " ");
        }
        System.out.println();
    }

    private String getInput() {
        String input = userInput.nextLine().toLowerCase();
        System.out.println("--------------------------------------------------");
        return input;
    }

    private void load() {
        System.out.print("Load last saved game? (y/n) ");
        String input = getInput();
        if (input.equals("y")) {
            loadGame();
        } else {
            System.out.println("New game started!");
        }
    }

    private void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream(saveFilePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            inventory = (UserInventory) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Game loaded!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load failed, starting new game!");
        }
    }

    private void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream(saveFilePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(inventory);
            out.close();
            fileOut.close();
            System.out.println("Game saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
