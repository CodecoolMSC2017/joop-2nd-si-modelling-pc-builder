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
                buildMenu();
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
            String cathegory = displayInventory(store, "\033[1mStore Menu\033[0m", ":back (or type the corresponding number)");
            if (cathegory.equals(":back")) {
                return;
            }
            if (cathegory.equals("invalid")) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                continue;
            }
            while (true) {
                System.out.println("\nYour money: \033[1m$" + inventory.getMoney() + "\033[0m\nCommands: :details :buy :back\n");
                String action = userInput.nextLine().toLowerCase();

                if (action.equals(":back")) {
                    break;
                }
                try {
                    if (action.equals(":buy")) {
                        handlePurchase(store, inventory, cathegory);
                    } else
                    if (action.equals(":details")) {
                        showDetails(store, cathegory);
                    } else {
                        System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                    }
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                }
                displayCathegory(store, cathegory);
            }
        }
    }

    static void buildMenu() {
        while(true) {
            System.out.println("\033[1mBuild Menu\033[0m\nCommands: :back :new :modify\n");
            String input = userInput.nextLine().toLowerCase();
            if (input.equals(":back")) {
                break;
            }
            if (input.equals(":new")) {
                continue;
            }
            if (input.equals(":modify")) {
                continue;
            } else {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m\n");
            }
        }
    }

    static void findMenu() {
        System.out.println("find menu is in progress");
    }

    static void inventoryMenu(UserInventory inventory) {
        while (true) {
            String cathegory = displayInventory(inventory, "\033[1mInventory Menu\033[0m", ":back (or type the corresponding number)");
            if (cathegory.equals(":back")) {
                break;
            }
            if (cathegory.equals("invalid")) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                continue;
            }
            if (cathegory.equals("empty")) {
                System.out.println("\n\033[91m\033[1mYou don't have any components of this type.\033[0m");
                continue;
            }
            while (true) {
                System.out.println("\nYour money: \033[1m$" + inventory.getMoney() + "\033[0m\nCommands: :details :sell :back\n");
                String action = userInput.nextLine().toLowerCase();

                if (action.equals(":back")) {
                    break;
                }
                try {
                    if (action.equals(":sell")) {
                        handleSell(inventory, cathegory);
                    } else
                    if (action.equals(":details")) {
                        showDetails(inventory, cathegory);
                    } else {
                        System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                    }
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                }
                displayCathegory(inventory, cathegory);
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

    static void displayComputers(UserInventory inventory) {
        Computer[] computers = inventory.getComputers();
        if (computers.length < 1) {
            System.out.println("You don't have any PCs.");
            return;
        }
        for (Computer computer : computers) {
            System.out.println(computer);
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

    static void handleSell(UserInventory inventory, String cathegory) throws ArrayIndexOutOfBoundsException {
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
                inventory.deleteItem(aCase);
                inventory.manageMoney(aCase.getValue());
                break;
            case "1":
                PowerSupply psu = inventory.getPsus()[index];
                inventory.deleteItem(psu);
                inventory.manageMoney(psu.getValue());
                break;
            case "2":
                Motherboard motherboard = inventory.getMotherboards()[index];
                inventory.deleteItem(motherboard);
                inventory.manageMoney(motherboard.getValue());
                break;
            case "3":
                CPU cpu = inventory.getCpus()[index];
                inventory.deleteItem(cpu);
                inventory.manageMoney(cpu.getValue());
                break;
            case "4":
                Heatsink heatsink = inventory.getHeatsinks()[index];
                inventory.deleteItem(heatsink);
                inventory.manageMoney(heatsink.getValue());
                break;
            case "5":
                Fan fan = inventory.getFans()[index];
                inventory.deleteItem(fan);
                inventory.manageMoney(fan.getValue());
                break;
            case "6":
                Memory memory = inventory.getMemories()[index];
                inventory.deleteItem(memory);
                inventory.manageMoney(memory.getValue());
                break;
            case "7":
                GraphicsCard gpu = inventory.getGpus()[index];
                inventory.deleteItem(gpu);
                inventory.manageMoney(gpu.getValue());
                break;
            case "8":
                SolidStateDrive ssd = inventory.getSsds()[index];
                inventory.deleteItem(ssd);
                inventory.manageMoney(ssd.getValue());
                break;
            case "9":
                HardDiskDrive hdd = inventory.getHdds()[index];
                inventory.deleteItem(hdd);
                inventory.manageMoney(hdd.getValue());
                break;
        }
        System.out.println("\n\033[1m\033[92mItem sold!\033[0m");
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

        String cathegory = checkValidCathegory(userInput.nextLine().toLowerCase());

        if (!checkEmptyCathegory(cathegory, inventory)) {
            return "empty";
        }
        if (!cathegory.equals("invalid")){
            displayCathegory(inventory, cathegory);
        }
        return cathegory;
    }

    static boolean checkEmptyCathegory(String cathegory, Inventory inventory) {
        switch(cathegory) {
            case ":back":
                return true;
            case "0":
                return inventory.getCases().length > 0;
            case "1":
                return inventory.getPsus().length > 0;
            case "2":
                return inventory.getMotherboards().length > 0;
            case "3":
                return inventory.getCpus().length > 0;
            case "4":
                return inventory.getHeatsinks().length > 0;
            case "5":
                return inventory.getFans().length > 0;
            case "6":
                return inventory.getMemories().length > 0;
            case "7":
                return inventory.getGpus().length > 0;
            case "8":
                return inventory.getSsds().length > 0;
            case "9":
                return inventory.getHdds().length > 0;
        }
        return true;
    }

    static String checkValidCathegory(String cathegory) {
        for(String option : new String[] {":back", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}) {
            if (option.equals(cathegory)) {
                return cathegory;
            }
        }
        return "invalid";
    }

    static void displayCathegory(Inventory inventory, String cathegory) {
        if (cathegory.equals(":back")) {
            return;
        }
        if (cathegory.equals("0")) {
            System.out.println("\n\033[1mCases:\033[0m");
            displayItems(inventory.getCases());
        } else
        if (cathegory.equals("1")) {
            System.out.println("\n\033[1mPower supplies:\033[0m");
            displayItems(inventory.getPsus());
        } else
        if (cathegory.equals("2")) {
            System.out.println("\n\033[1mMotherboards:\033[0m");
            displayItems(inventory.getMotherboards());
        } else
        if (cathegory.equals("3")) {
            System.out.println("\n\033[1mProcessors:\033[0m");
            displayItems(inventory.getCpus());
        } else
        if (cathegory.equals("4")) {
            System.out.println("\n\033[1mHeatsinks:\033[0m");
            displayItems(inventory.getHeatsinks());
        } else
        if (cathegory.equals("5")) {
            System.out.println("\n\033[1mFans:\033[0m");
            displayItems(inventory.getFans());
        } else
        if (cathegory.equals("6")) {
            System.out.println("\n\033[1mMemories:\033[0m");
            displayItems(inventory.getMemories());
        } else
        if (cathegory.equals("7")) {
            System.out.println("\n\033[1mGrapics cards:\033[0m");
            displayItems(inventory.getGpus());
        } else
        if (cathegory.equals("8")) {
            System.out.println("\n\033[1mSolid state drives:\033[0m");
            displayItems(inventory.getSsds());
        } else
        if (cathegory.equals("9")) {
            System.out.println("\n\033[1mHard disk drives:\033[0m");
            displayItems(inventory.getHdds());
        } else {
            System.out.println("\033[1m\033[91mIncorrect input: \033[0m");
        }
    }

    static void displayItems(PCComponent[] items) {
        int counter = 0;
        String colorCode;
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