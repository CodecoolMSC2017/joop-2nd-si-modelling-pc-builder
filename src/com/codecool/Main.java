package com.codecool;

import java.util.*;

public class Main {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        Store store = new Store();
        UserInventory inventory = new UserInventory(1000);

        while (true) {
            System.out.println("\n\033[1mMain Menu\033[0m\nCommands: :home :store :build :find :inventory :save :help :exit\n");
            String input = userInput.nextLine().toLowerCase();
            System.out.println();
            if (input.equals(":exit")) {
                exitMenu();
                break;
            } else 
            if (input.equals(":home")) {
                homeMenu();
            } else 
            if (input.equals(":store")) {
                storeMenu(store, inventory);
            } else
            if (input.equals(":build")) {
                buildMenu(inventory);
            } else
            if (input.equals(":find")) {
                findMenu();
            } else
            if (input.equals(":inventory")) {
                inventoryMenu(inventory);
            } else
            if (input.equals(":save")) {
                saveMenu();
            } else
            if (input.equals(":help")) {
                printHelp();
            } else {
                System.out.println("Unknown command: " + input);
            }
        }
    }

    static void exitMenu() {
        System.out.println("exit menu is in progress");
    }

    static void homeMenu() {
        System.out.println("home menu is in progress");
    }

    static void storeMenu(Store store, UserInventory inventory) {
        while (true) {
            String cathegory;
            try {
                cathegory = store.displayInventory("\033[1mStore Menu\033[0m", ":back (or type the corresponding number)");
            } catch (InvalidCathegoryException e) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                continue;
            } catch (EmptyCathegoryException e) {
                System.out.println("\n\033[91m\033[1mThe store has no components of this type.\033[0m");
                continue;
            }
            if (cathegory.equals(":back")) {
                return;
            }
            while (true) {
                System.out.println("\nYour money: \033[1m$" + inventory.getMoney() + "\033[0m\nCommands: :details :buy :back\n");
                String action = userInput.nextLine().toLowerCase();

                if (action.equals(":back")) {
                    break;
                }
                try {
                    if (action.equals(":buy")) {
                        store.handlePurchase(inventory, cathegory);
                    } else
                    if (action.equals(":details")) {
                        store.showDetails(cathegory);
                    } else {
                        System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                }
                store.displayCathegory(cathegory);
            }
        }
    }

    static void buildMenu(UserInventory inventory) {
        while(true) {
            String input = inventory.displayComputers("\033[1mBuild Menu\033[0m", ":back :new :modify");
            if (input.equals(":back")) {
                break;
            }
            if (input.equals(":new")) {
                continue;
            }
            if (input.equals(":modify")) {
                continue;
            }
            
        }
    }

    static void findMenu() {
        System.out.println("find menu is in progress");
    }

    static void inventoryMenu(UserInventory inventory) {
        while (true) {
            String cathegory;
            try {
                cathegory = inventory.displayInventory("\033[1mInventory Menu\033[0m", ":back (or type the corresponding number)");
            } catch (InvalidCathegoryException e) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                continue;
            } catch (EmptyCathegoryException e) {
                System.out.println("\n\033[91m\033[1mYou don't have any components of this type.\033[0m");
                continue;
            }
            if (cathegory.equals(":back")) {
                break;
            }
            while (true) {
                System.out.println("\nYour money: \033[1m$" + inventory.getMoney() + "\033[0m\nCommands: :details :sell :back\n");
                String action = userInput.nextLine().toLowerCase();

                if (action.equals(":back")) {
                    break;
                }
                try {
                    if (action.equals(":sell")) {
                        inventory.handleSell(cathegory);
                    } else
                    if (action.equals(":details")) {
                        inventory.showDetails(cathegory);
                    } else {
                        System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                } catch (EmptyCathegoryException e) {
                    System.out.println("\n\033[91m\033[1mYou don't have any components of this type.\033[0m");
                    break;
                }
                inventory.displayCathegory(cathegory);
            }
        }
    }

    static void saveMenu() {
        System.out.println("save menu is in progress");
    }

    static void printHelp() {
        System.out.println("   \033[1mMain Menu commands\033[0m");
        System.out.println("   :home      > this is where you can use your PCs");
        System.out.println("   :store     > enters the store menu where you can browse and buy components");
        System.out.println("   :build     > modify a PC you have already built or build a brand new one");
        System.out.println("   :find      > type the name of your PC to display it's specs");
        System.out.println("   :inventory > displays the components you have bought but have not built in yet");
        System.out.println("   :save      > saves your progress (saved game is loaded automatiaclly upon startup)");
        System.out.println("   :help      > displays this helpful description");
        System.out.println("   :exit      > after asking to save the game exits the program");
    }

}