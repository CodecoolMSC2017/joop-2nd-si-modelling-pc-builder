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
            String cathegory = chooseCathegory(store, "\033[1mStore Menu\033[0m", null);
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
                handleCreate(inventory);
            } else
            if (input.equals(":modify")) {
                handleModify(inventory);
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
            String cathegory = chooseCathegory(inventory, "\033[1mInventory Menu\033[0m", null);
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

    static String chooseCathegory(Inventory inventory, String menuTitle, Computer pc) {
        String cathegory;
        while (true) {
            if (pc != null) {
                System.out.println(pc.details());
            }
            try {
                cathegory = inventory.displayInventory(menuTitle, ":back (or type the corresponding number)");
                break;
            } catch (InvalidCathegoryException e) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
            } catch (EmptyCathegoryException e) {
                System.out.println("\n\033[91m\033[1mThere are no components of this type.\033[0m");
            }
        }
        return cathegory;
    }

    static void handleCreate(UserInventory inventory) {
        if (inventory.getComputers().length == 3) {
            System.out.println("\n\033[1m\033[91mYou can only have 3 computers at the same time!\033[0m\n" +
                "\033[1m(You don't want to get the attention of the authorities with your high power bill)\033[0m");
            return;
        }
        System.out.println("\033[1mTo create a new PC you have to name it first:\033[0m\n");
        String name = userInput.nextLine();
        inventory.addComputer(new Computer(name));
        System.out.println("\n\033[1m\033[92mYour new PC " + name + " has been created!\033[0m");
        System.out.println("See :modify to select it's components\n");
    }

    static void handleModify(UserInventory inventory) {
        if (inventory.getComputers().length < 1) {
            System.out.println("\n\033[1m\033[91mYou don't have any PCs.\033[0m");
            return;
        }
        Computer pc = inventory.selectPC();
        if (pc == null) {
            return;
        }
        while (true) {
            System.out.println(pc.details());
            System.out.println("\nCommands: :add :remove :back\n");
            String action = userInput.nextLine().toLowerCase();

            if (action.equals(":back")) {
                break;
            }
            try {
                if (action.equals(":add")) {
                    handleAdd(pc, inventory);
                } else
                if (action.equals(":remove")) {
                    handleRemove();
                } else {
                    System.out.println("Unknown command: " + action);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
            }
        }
    }

    static void handleAdd(Computer pc, UserInventory inventory) throws ArrayIndexOutOfBoundsException {
        String cathegory = chooseCathegory(inventory, "\033[1mSelect an item\033[0m", pc);
        if (cathegory.equals(":back")) {
            return;
        }
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
                Case aCase = inventory.getCases()[index];
                pc.addItem(aCase);
                inventory.deleteItem(aCase);
                return;
            case "1":
                PowerSupply psu = inventory.getPsus()[index];
                pc.addItem(psu);
                inventory.deleteItem(psu);
                return;
            case "2":
                Motherboard motherboard = inventory.getMotherboards()[index];
                pc.addItem(motherboard);
                inventory.deleteItem(motherboard);
                return;
            case "3":
                CPU cpu = inventory.getCpus()[index];
                pc.addItem(cpu);
                inventory.deleteItem(cpu);
                return;
            case "4":
                Heatsink heatsink = inventory.getHeatsinks()[index];
                pc.addItem(heatsink);
                inventory.deleteItem(heatsink);
                return;
            case "5":
                Fan fan = inventory.getFans()[index];
                pc.addItem(fan);
                inventory.deleteItem(fan);
                return;
            case "6":
                Memory memory = inventory.getMemories()[index];
                pc.addItem(memory);
                inventory.deleteItem(memory);
                return;
            case "7":
                GraphicsCard gpu = inventory.getGpus()[index];
                pc.addItem(gpu);
                inventory.deleteItem(gpu);
                return;
            case "8":
                SolidStateDrive ssd = inventory.getSsds()[index];
                pc.addItem(ssd);
                inventory.deleteItem(ssd);
                return;
            case "9":
                HardDiskDrive hdd = inventory.getHdds()[index];
                pc.addItem(hdd);
                inventory.deleteItem(hdd);
                return;
        }
    }

    static void handleRemove() {

    }

}