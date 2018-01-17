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
                storeMenu(store);
            } else
            if (input.equals(":build")) {
                buildMenu(inventory);;
            } else
            if (input.equals(":find")) {
                break;
            } else
            if (input.equals(":inventory")) {
                displayInventory(inventory);;
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

    static void storeMenu(Store store) {
        System.out.println("Store Menu\nCommands: :back");
    }

    static void buildMenu(Inventory inventory) {
        System.out.println("this is the build menu");
    }

    static void displayInventory(UserInventory inventory) {
        System.out.println("\nInventory\nCommands: :back (or type corresponding number)\n");
        System.out.println("0 (" + inventory.getCases().length + ") Cases");
        System.out.println("1 (" + inventory.getPsus().length + ") Power supplies");
        System.out.println("2 (" + inventory.getMotherboards().length + ") Motherboards");
        System.out.println("3 (" + inventory.getCpus().length + ") Processors");
        System.out.println("4 (" + inventory.getHeatsinks().length + ") Heatsinks");
        System.out.println("5 (" + inventory.getFans().length + ") Fans");
        System.out.println("6 (" + inventory.getMemories().length + ") Memories");
        System.out.println("7 (" + inventory.getGpus().length + ") Graphics cards");
        System.out.println("8 (" + inventory.getSsds().length + ") Solid state drives");
        System.out.println("9 (" + inventory.getHdds().length + ") Hard disk drives\n");
        String input = userInput.nextLine().toLowerCase();

        if (input.equals(":back")) {
            return;
        }
    }

}