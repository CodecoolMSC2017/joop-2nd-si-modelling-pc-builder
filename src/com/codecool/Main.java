package com.codecool;

import java.util.*;

public class Main {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        Store store = new Store();
        UserInventory inventory = new UserInventory(1000);

        while (true) {
            System.out.println("\nMain Menu\nCommands: :store :build :find :inventory :save :help :exit");
            String input = userInput.nextLine().toLowerCase();
            System.out.println();
            if (input.equals(":exit")) {
                break;
            } else 
            if (input.equals(":store")) {
                storeMenu(store, inventory);
            } else
            if (input.equals(":build")) {
                buildMenu(inventory);;
            } else
            if (input.equals(":find")) {
                break;
            } else
            if (input.equals(":inventory")) {
                inventoryMenu(inventory);
            } else
            if (input.equals(":save")) {
                break;
            } else
            if (input.equals(":help")) {
                System.out.println("\t:store     > enters the store menu where you can browse and buy components");
                System.out.println("\t:build     > modify a PC you have already built or build a brand new one");
                System.out.println("\t:find      > find your PC by typing it's name and displays it's specs");
                System.out.println("\t:inventory > displays the components you have bought but have not built in yet");
                System.out.println("\t:save      > saves your progress (saved game is loaded automatiaclly upon startup)");
                System.out.println("\t:exit      > after asking to save the game exits the program");
            } else {
                System.out.println("Unknown command: " + input);
            }
        }
    }

    static void storeMenu(Store store, UserInventory inventory) {
        while (true) {
            String cathegory = displayInventory(store, "Store Menu", ":back (or type the corresponding number)");
            if (cathegory.equals(":back")) {
                return;
            }
            while (true) {
                System.out.println("\nYour money: \033[1m$" + inventory.getMoney() + "\033[0m\nCommands: :details :purchase :back\n");
                String action = userInput.nextLine().toLowerCase();

                if (action.equals(":back")) {
                    break;
                }
                try {
                    if (action.equals(":purchase")) {
                        handlePurchase(store, inventory, cathegory);
                    } else
                    if (action.equals(":details")) {
                        showDetails(store, cathegory);
                    } else {
                        System.out.println("\n\033[1m\033[91mIncorrect input: \033[0m" + action);
                    }
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                }
                displayCathegory(store, cathegory);
            }
        }
    }

    static void showDetails(Inventory inventory, String cathegory) {
        System.out.print("\nSelect an item by it's number: ");
        String input = userInput.nextLine().toLowerCase();
        int index = 0;
        try {
            index = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
            return;
        }
        switch(cathegory) {
            case "0":
                System.out.println(inventory.getCases()[index].details());
                return;
            case "1":
                System.out.println(inventory.getPsus()[index].details());
                break;
            case "2":
                System.out.println(inventory.getMotherboards()[index].details());
                break;
            case "3":
                System.out.println(inventory.getCpus()[index].details());
                break;
            case "4":
                System.out.println(inventory.getHeatsinks()[index].details());
                break;
            case "5":
                System.out.println(inventory.getFans()[index].details());
                break;
            case "6":
                System.out.println(inventory.getMemories()[index].details());
                break;
            case "7":
                System.out.println(inventory.getGpus()[index].details());
                break;
            case "8":
                System.out.println(inventory.getSsds()[index].details());
                break;
            case "9":
                System.out.println(inventory.getHdds()[index].details());
                break;
        }
        System.out.println();
    }

    static void handlePurchase(Store store, UserInventory inventory, String cathegory) throws ArrayIndexOutOfBoundsException {
        System.out.print("\nSelect an item by it's number: ");
        String input = userInput.nextLine().toLowerCase();
        int index = 0;
        try {
            index = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
            return;
        }
        try {
            switch(cathegory) {
                case "0":
                    Case aCase = store.getCases()[index];
                    if (inventory.getMoney() < aCase.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(aCase);
                    inventory.manageMoney(-aCase.getValue());
                    break;
                case "1":
                    PowerSupply psu = store.getPsus()[index];
                    if (inventory.getMoney() < psu.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(psu);
                    inventory.manageMoney(-psu.getValue());
                    break;
                case "2":
                    Motherboard motherboard = store.getMotherboards()[index];
                    if (inventory.getMoney() < motherboard.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(motherboard);
                    inventory.manageMoney(-motherboard.getValue());
                    break;
                case "3":
                    CPU cpu = store.getCpus()[index];
                    if (inventory.getMoney() < cpu.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(cpu);
                    inventory.manageMoney(-cpu.getValue());
                    break;
                case "4":
                    Heatsink heatsink = store.getHeatsinks()[index];
                    if (inventory.getMoney() < heatsink.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(heatsink);
                    inventory.manageMoney(-heatsink.getValue());
                    break;
                case "5":
                    Fan fan = store.getFans()[index];
                    if (inventory.getMoney() < fan.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(fan);
                    inventory.manageMoney(-fan.getValue());
                    break;
                case "6":
                    Memory memory = store.getMemories()[index];
                    if (inventory.getMoney() < memory.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(memory);
                    inventory.manageMoney(-memory.getValue());
                    break;
                case "7":
                    GraphicsCard gpu = store.getGpus()[index];
                    if (inventory.getMoney() < gpu.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(gpu);
                    inventory.manageMoney(-gpu.getValue());
                    break;
                case "8":
                    SolidStateDrive ssd = store.getSsds()[index];
                    if (inventory.getMoney() < ssd.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(ssd);
                    inventory.manageMoney(-ssd.getValue());
                    break;
                case "9":
                    HardDiskDrive hdd = store.getHdds()[index];
                    if (inventory.getMoney() < hdd.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(hdd);
                    inventory.manageMoney(-hdd.getValue());
                    break;
            }
        } catch(NotEnoughMoneyException e) {
            System.out.println("\n\033[1m\033[91mYou don't have enough money to purchase this item!\033[0m");
            return;
        }
        System.out.println("\n\033[1m\033[92mItem purchased!\033[0m");
    }

    static void inventoryMenu(UserInventory inventory) {
        while (true) {
            String input = displayInventory(inventory, "Inventory Menu", ":back (or type the corresponding number)");
            if (input.equals(":back")) {
                break;
            }
        }
    }

    static void buildMenu(Inventory inventory) {
        System.out.println("this is the build menu");
    }

    static String displayInventory(Inventory inventory, String menuTitle, String commands) {
        System.out.println("\n" + menuTitle + "\nCommands: " + commands + "\n");
        System.out.println("0 (" + inventory.getCases().length + " items) Cases");
        System.out.println("1 (" + inventory.getPsus().length + " items) Power supplies");
        System.out.println("2 (" + inventory.getMotherboards().length + " items) Motherboards");
        System.out.println("3 (" + inventory.getCpus().length + " items) Processors");
        System.out.println("4 (" + inventory.getHeatsinks().length + " items) Heatsinks");
        System.out.println("5 (" + inventory.getFans().length + " items) Fans");
        System.out.println("6 (" + inventory.getMemories().length + " items) Memories");
        System.out.println("7 (" + inventory.getGpus().length + " items) Graphics cards");
        System.out.println("8 (" + inventory.getSsds().length + " items) Solid state drives");
        System.out.println("9 (" + inventory.getHdds().length + " items) Hard disk drives\n");

        String cathegory = userInput.nextLine().toLowerCase();

        displayCathegory(inventory, cathegory);

        return cathegory;
    }

    static void displayCathegory(Inventory inventory, String cathegory) {
        if (cathegory.equals(":back")) {
            return;
        }
        if (cathegory.equals("0")) {
            displayItems(inventory.getCases());
        } else
        if (cathegory.equals("1")) {
            displayItems(inventory.getPsus());
        } else
        if (cathegory.equals("2")) {
            displayItems(inventory.getMotherboards());
        } else
        if (cathegory.equals("3")) {
            displayItems(inventory.getCpus());
        } else
        if (cathegory.equals("4")) {
            displayItems(inventory.getHeatsinks());
        } else
        if (cathegory.equals("5")) {
            displayItems(inventory.getFans());
        } else
        if (cathegory.equals("6")) {
            displayItems(inventory.getMemories());
        } else
        if (cathegory.equals("7")) {
            displayItems(inventory.getGpus());
        } else
        if (cathegory.equals("8")) {
            displayItems(inventory.getSsds());
        } else
        if (cathegory.equals("9")) {
            displayItems(inventory.getHdds());
        } else {
            System.out.println("incorrect");
        }
    }

    static void displayItems(PCComponent[] items) {
        if (items.length < 1) {
            System.out.println("\nYou don't have any components of this type.");
            return;
        }
        int counter = 0;
        String colorCode;
        System.out.println();
        for (PCComponent item : items) {
            switch(item.getTier()) {
                case HIGH:
                    colorCode = "\033[91m";
                    break;
                case MEDIUM:
                    colorCode = "\033[93m";
                    break;
                case LOW:
                    colorCode = "\033[92m";
                    break;
                default:
                    colorCode = "\033[0m";
            }
            System.out.println(counter + " " + colorCode + item + "\033[0m");
            counter++;
        }
    }

}