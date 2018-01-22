package com.codecool;

import java.util.*;

public class Main {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        Store store = new Store();
        UserInventory inventory = new UserInventory(1000);

        while (true) {
            System.out.println("\n\033[1mMain Menu\033[0m\nCommands:" + 
                " :home :store :build :find :inventory :save :help :exit\n");
            String input = userInput.nextLine().toLowerCase();
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
                findMenu(inventory);
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
            String cathegory = store.chooseCathegory("\033[1mStore Menu\033[0m", null);
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
            System.out.println("\n\033[1mBuild Menu\033[0m\nCommands: :back :new :modify\n");
            String input = userInput.nextLine().toLowerCase();
            if (input.equals(":back")) {
                break;
            }
            if (input.equals(":new")) {
                inventory.handleCreate();
            } else
            if (input.equals(":modify")) {
                inventory.handleModify();
            } else {
                System.out.println("Unknown command: " + input);
            }
            
        }
    }

    static void findMenu(UserInventory inventory) {
        if (inventory.getComputers().length < 1) {
            System.out.println("\n\033[1m\033[91mYou don't have any PCs.\033[0m");
            return;
        }
        while (true) {
            Computer pc = inventory.selectPC();
            if (pc == null) {
                break;
            }
            System.out.println(pc.details());
        }
    }

    static void inventoryMenu(UserInventory inventory) {
        while (true) {
            String cathegory = inventory.chooseCathegory("\033[1mInventory Menu\033[0m", null);
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
        System.out.println("   :find      > select one of your PCs to display it's specs");
        System.out.println("   :inventory > displays the components you have bought but have not built in yet");
        System.out.println("   :save      > saves your progress (saved game is loaded automatiaclly upon startup)");
        System.out.println("   :help      > displays this helpful description");
        System.out.println("   :exit      > after asking to save the game exits the program");
    }

}