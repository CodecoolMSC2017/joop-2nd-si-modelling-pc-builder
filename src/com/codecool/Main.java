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
            String cathegory = displayInventory(store, "Store Menu", ":back (or type corresponding number");
            if (cathegory.equals(":back")) {
                return;
            }
            while (true) {
                System.out.println("\nCommands: :details :purchase :back\n");
                String action = userInput.nextLine();

                if (action.equals(":back")) {
                    break;
                }
                if (action.equals(":purchase")) {
                    try {
                        handlePurchase(store, inventory, cathegory);
                    } catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println("\nIncorrect input!");
                    }
                } else
                if (action.equals(":details")) {
                    System.out.println("details: ...");
                } else {
                    System.out.println("\nWrong input: " + action + "\n");
                }
            }
        }
    }

    static void handlePurchase(Store store, UserInventory inventory, String cathegory) throws ArrayIndexOutOfBoundsException {
        System.out.println("Select an item by it's number:");
            String input = userInput.nextLine();
            int index = 0;
            try {
                index = Integer.parseInt(input);
            } catch(NumberFormatException e) {
                System.out.println("\nWrong input!\n");
                return;
            }
            switch(cathegory) {
                case "0":
                    inventory.addItem(store.getCases()[index]);
                    break;
                case "1":
                    inventory.addItem(store.getPsus()[index]);
                    break;
                case "2":
                    inventory.addItem(store.getMotherboards()[index]);
                    break;
                case "3":
                    inventory.addItem(store.getCpus()[index]);
                    break;
                case "4":
                    inventory.addItem(store.getHeatsinks()[index]);
                    break;
                case "5":
                    inventory.addItem(store.getFans()[index]);
                    break;
                case "6":
                    inventory.addItem(store.getMemories()[index]);
                    break;
                case "7":
                    inventory.addItem(store.getGpus()[index]);
                    break;
                case "8":
                    inventory.addItem(store.getSsds()[index]);
                    break;
                case "9":
                    inventory.addItem(store.getHdds()[index]);
                    break;
            }
    }

    static void inventoryMenu(UserInventory inventory) {
        while (true) {
            String input = displayInventory(inventory, "Inventory Menu", ":back (or type corresponding number");
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

        String input = userInput.nextLine().toLowerCase();

        if (input.equals(":back")) {
            return input;
        }
        if (input.equals("0")) {
            displayItems(inventory.getCases());
        } else
        if (input.equals("1")) {
            displayItems(inventory.getPsus());
        } else
        if (input.equals("2")) {
            displayItems(inventory.getMotherboards());
        } else
        if (input.equals("3")) {
            displayItems(inventory.getCpus());
        } else
        if (input.equals("4")) {
            displayItems(inventory.getHeatsinks());
        } else
        if (input.equals("5")) {
            displayItems(inventory.getFans());
        } else
        if (input.equals("6")) {
            displayItems(inventory.getMemories());
        } else
        if (input.equals("7")) {
            displayItems(inventory.getGpus());
        } else
        if (input.equals("8")) {
            displayItems(inventory.getSsds());
        } else
        if (input.equals("9")) {
            displayItems(inventory.getHdds());
        }
        return input;
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