package com.codecool.cmd;

import com.codecool.api.Computer;
import com.codecool.api.Inventory;
import com.codecool.api.Store;
import com.codecool.api.UserInventory;
import com.codecool.api.components.PCComponent;
import com.codecool.api.exceptions.ComponentsDoNotMatchException;
import com.codecool.api.exceptions.NoMoreRoomException;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class CmdMenu {

    private String saveFilePath = System.getProperty("user.home") + "/pc-builder-save.ser";

    private UserInventory inventory = new UserInventory(1000);
    private Store store = new Store();

    private Scanner userInput = new Scanner(System.in);
    private String currentType;

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
        while (true) {
            List<? extends PCComponent> items = chooseCategory(store);
            if (items == null) {
                return;
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
        String[] options = new String[]{
                "new",
                "modify",
                "rename",
                "disassemble",
                "back"
        };
        while (true) {
            showMenu("Build Menu", options);
            String choice = getInput();
            switch (choice) {
                case "back":
                    return;
                case "new":
                    createNewPc();
                    break;
                case "modify":
                    modifyMenu();
                    break;
                case "rename":
                    renamePc();
                    break;
                case "disassemble":
                    disassemblePc();
                    break;
                default:
                    System.out.println("Unknown command: " + choice);
            }
        }
    }

    private void modifyMenu() {
        Computer pc = choosePc();
        if (pc == null) {
            return;
        }
        while (true) {
            List<? extends PCComponent> components = chooseCategory(pc);
            if (components == null) {
                return;
            }
            pcItemsMenu(components, pc);
        }
    }

    private void pcItemsMenu(List<? extends PCComponent> components, Computer pc) {
        String[] options = new String[]{"add", "remove", "details", "back"};
        while (true) {
            displayCategory(components);
            showMenu("", options);
            String choice = getInput();
            switch (choice) {
                case "back":
                    return;
                case "add":
                    addComponentToPc(pc, findInventoryListByType());
                    break;
                case "remove":
                    removeComponent(pc);
                    break;
                case "details":
                    showDetails(components);
                    break;
                default:
                    System.out.println("Unknown command: " + choice);
            }
        }
    }

    private void removeComponent(Computer pc) {
        PCComponent component = chooseItem(findComputerListByType(pc));
        pc.removeItem(component);
        inventory.addItem(component);
        System.out.println("Component removed!");
    }

    private List<? extends PCComponent> findComputerListByType(Computer pc) {
        switch (currentType) {
            case "case":
                return pc.getCases();
            case "psu":
                return pc.getPsus();
            case "motherboard":
                return pc.getMotherboards();
            case "cpu":
                return pc.getCpus();
            case "heatsink":
                return pc.getHeatsinks();
            case "fan":
                return pc.getFans();
            case "memory":
                return pc.getMemories();
            case "gpu":
                return pc.getGpus();
            case "ssd":
                return pc.getSsds();
            case "hdd":
                return pc.getHdds();
            default:
                return null;
        }
    }

    private List<? extends PCComponent> findInventoryListByType() {
        switch (currentType) {
            case "case":
                return inventory.getCases();
            case "psu":
                return inventory.getPsus();
            case "motherboard":
                return inventory.getMotherboards();
            case "cpu":
                return inventory.getCpus();
            case "heatsink":
                return inventory.getHeatsinks();
            case "fan":
                return inventory.getFans();
            case "memory":
                return inventory.getMemories();
            case "gpu":
                return inventory.getGpus();
            case "ssd":
                return inventory.getSsds();
            case "hdd":
                return inventory.getHdds();
            default:
                return null;
        }
    }

    private void addComponentToPc(Computer pc, List<? extends PCComponent> components) {
        if (components == null) {
            System.out.println("Error with type " + currentType);
            return;
        }
        PCComponent component = chooseItem(components);
        if (component == null) {
            return;
        }
        try {
            pc.addComponent(component);
            inventory.removeItem(component);
            System.out.println("Component added!");
        } catch (NoMoreRoomException | ComponentsDoNotMatchException e) {
            System.out.println(e.getMessage());
        }
    }

    private void disassemblePc() {
        Computer pc = choosePc();
        if (pc == null) {
            return;
        }
        System.out.println("Are you sure you want to disassemble " + pc.getName() + "? (y/n)");
        String choice = getInput();
        if (choice.equals("y")) {
            inventory.getComputers().remove(pc);
        }
    }

    private void renamePc() {
        Computer pc = choosePc();
        if (pc == null) {
            return;
        }
        System.out.println("Type the new name of the pc: (or back)");
        String newName = getInput();
        if (newName.equals("back") || newName.equals("")) {
            return;
        }
        pc.setName(newName);
        System.out.println("Pc renamed!");
    }

    private Computer choosePc() {
        List<Computer> computers = inventory.getComputers();
        if (computers.size() == 0) {
            System.out.println("You don't have Pcs!");
            return null;
        }
        int counter;
        while (true) {
            System.out.println("Choose a pc by it's number (or back)");
            counter = 0;
            for (Computer computer : computers) {
                System.out.println(counter + ") " + computer.getName());
                counter++;
            }
            String choice = getInput();
            if (choice.equals("back")) {
                return null;
            }
            try {
                int index = Integer.parseInt(choice);
                return computers.get(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
            }
        }
    }

    private void createNewPc() {
        if (inventory.getComputers().size() > 2) {
            System.out.println("You have the maximum number of Pcs!");
            return;
        }
        System.out.println("Type the name of the new pc: (or back)");
        String name = getInput();
        if (name.equals("back") || name.equals("")) {
            return;
        }
        if (inventory.checkIfNameExists(name)) {
            System.out.println("This name already exists!");
            return;
        }
        inventory.addComputer(new Computer(name));
        System.out.println("Pc created!\n" +
                "May your framerates be high and temperatures low!");
    }

    private void computersMenu() {
        while (true) {
            Computer pc = choosePc();
            if (pc == null) {
                return;
            }
            System.out.println(pc);
        }
    }

    private void inventoryMenu() {
        while (true) {
            List<? extends PCComponent> items = chooseCategory(inventory);
            if (items == null) {
                return;
            }
            inventoryItemsMenu(items);
        }
    }

    private void inventoryItemsMenu(List<? extends PCComponent> components) {
        if (components.size() == 0) {
            System.out.println("No items!");
            return;
        }
        String[] options = new String[]{"sell", "details", "back"};
        while (true) {
            displayCategory(components);
            showMenu("Money: " + inventory.getMoney(), options);
            String choice = getInput();
            switch (choice) {
                case "back":
                    return;
                case "sell":
                    sellItem(components);
                    break;
                case "details":
                    showDetails(components);
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
        if (components.size() == 0) {
            System.out.println("No items");
            return;
        }
        int counter = 0;
        System.out.println();
        for (PCComponent component : components) {
            System.out.println(counter + ") " + component.toString());
            counter++;
        }
    }

    private List<? extends PCComponent> chooseCategory(Inventory inventory) {
        List<? extends PCComponent> items;
        while (true) {
            listCategories(inventory);
            switch (getInput()) {
                case "back":
                    return null;
                case "0":
                    items = inventory.getCases();
                    currentType = "case";
                    break;
                case "1":
                    items = inventory.getPsus();
                    currentType = "psu";
                    break;
                case "2":
                    items = inventory.getMotherboards();
                    currentType = "motherboard";
                    break;
                case "3":
                    items = inventory.getCpus();
                    currentType = "cpu";
                    break;
                case "4":
                    items = inventory.getHeatsinks();
                    currentType = "heatsink";
                    break;
                case "5":
                    items = inventory.getFans();
                    currentType = "fan";
                    break;
                case "6":
                    items = inventory.getMemories();
                    currentType = "memory";
                    break;
                case "7":
                    items = inventory.getGpus();
                    currentType = "gpu";
                    break;
                case "8":
                    items = inventory.getSsds();
                    currentType = "ssd";
                    break;
                case "9":
                    items = inventory.getHdds();
                    currentType = "hdd";
                    break;
                default:
                    System.out.println("Wrong input!");
                    continue;
            }
            return items;
        }
    }

    private void listCategories(Inventory inventory) {
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
            inventory.save();
            System.out.println("Game saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
