package com.codecool;

import java.util.Scanner;

public class Inventory {

    private Case[] cases;
    private PowerSupply[] psus;
    private Motherboard[] motherboards;
    private CPU[] cpus;
    private Heatsink[] heatsinks;
    private Fan[] fans;
    private Memory[] memories;
    private GraphicsCard[] gpus;
    private SolidStateDrive[] ssds;
    private HardDiskDrive[] hdds;
    protected Scanner userInput;

    public Inventory(Case[] cases, PowerSupply[] psus, Motherboard[] motherboards, CPU[] cpus,
        Heatsink[] heatsinks, Fan[] fans, Memory[] memories, GraphicsCard[] gpus,
        SolidStateDrive[] ssds, HardDiskDrive[] hdds) {
        this.cases = cases;
        this.psus = psus;
        this.motherboards = motherboards;
        this.cpus = cpus;
        this.heatsinks = heatsinks;
        this.fans = fans;
        this.memories = memories;
        this.gpus = gpus;
        this.ssds = ssds;
        this.hdds = hdds;
        this.userInput = new Scanner(System.in);
    }

    public String chooseCathegory(String menuTitle, Computer pc) {
        String cathegory;
        while (true) {
            if (pc != null) {
                System.out.println(pc.details());
            }
            try {
                cathegory = this.displayInventory(menuTitle, ":back (or type the corresponding number)");
                break;
            } catch (InvalidCathegoryException e) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
            } catch (EmptyCathegoryException e) {
                System.out.println("\n\033[91m\033[1mThere are no components of this type.\033[0m");
            }
        }
        return cathegory;
    }

    private String displayInventory(String menuTitle, String commands) throws InvalidCathegoryException, EmptyCathegoryException {
        System.out.println("\n" + menuTitle + "\nCommands: " + commands + "\n");
        System.out.println("0 (" + this.getCases().length + " items) Cases");
        System.out.println("1 (" + this.getPsus().length + " items) Power supplies");
        System.out.println("2 (" + this.getMotherboards().length + " items) Motherboards");
        System.out.println("3 (" + this.getCpus().length + " items) Processors");
        System.out.println("4 (" + this.getHeatsinks().length + " items) Heatsinks");
        System.out.println("5 (" + this.getFans().length + " items) Fans");
        System.out.println("6 (" + this.getMemories().length + " items) Memories");
        System.out.println("7 (" + this.getGpus().length + " items) Graphics cards");
        System.out.println("8 (" + this.getSsds().length + " items) Solid state drives");
        System.out.println("9 (" + this.getHdds().length + " items) Hard disk drives\n");

        String cathegory = userInput.nextLine().toLowerCase();

        checkValidCathegory(cathegory);

        if (!checkEmptyCathegory(cathegory)) {
            throw new EmptyCathegoryException();
        }

        displayCathegory(cathegory);

        return cathegory;
    }

    private void checkValidCathegory(String cathegory) throws InvalidCathegoryException {
        for(String option : new String[] {":back", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}) {
            if (option.equals(cathegory)) {
                return;
            }
        }
        throw new InvalidCathegoryException();
    }

    private boolean checkEmptyCathegory(String cathegory) {
        switch(cathegory) {
            case "0":
                return this.getCases().length > 0;
            case "1":
                return this.getPsus().length > 0;
            case "2":
                return this.getMotherboards().length > 0;
            case "3":
                return this.getCpus().length > 0;
            case "4":
                return this.getHeatsinks().length > 0;
            case "5":
                return this.getFans().length > 0;
            case "6":
                return this.getMemories().length > 0;
            case "7":
                return this.getGpus().length > 0;
            case "8":
                return this.getSsds().length > 0;
            case "9":
                return this.getHdds().length > 0;
        }
        return true;
    }

    public void displayCathegory(String cathegory) {
        if (cathegory.equals(":back")) {
            return;
        }
        if (cathegory.equals("0")) {
            System.out.println("\n\033[1mCases:\033[0m");
            displayItems(this.getCases());
        } else
        if (cathegory.equals("1")) {
            System.out.println("\n\033[1mPower supplies:\033[0m");
            displayItems(this.getPsus());
        } else
        if (cathegory.equals("2")) {
            System.out.println("\n\033[1mMotherboards:\033[0m");
            displayItems(this.getMotherboards());
        } else
        if (cathegory.equals("3")) {
            System.out.println("\n\033[1mProcessors:\033[0m");
            displayItems(this.getCpus());
        } else
        if (cathegory.equals("4")) {
            System.out.println("\n\033[1mHeatsinks:\033[0m");
            displayItems(this.getHeatsinks());
        } else
        if (cathegory.equals("5")) {
            System.out.println("\n\033[1mFans:\033[0m");
            displayItems(this.getFans());
        } else
        if (cathegory.equals("6")) {
            System.out.println("\n\033[1mMemories:\033[0m");
            displayItems(this.getMemories());
        } else
        if (cathegory.equals("7")) {
            System.out.println("\n\033[1mGrapics cards:\033[0m");
            displayItems(this.getGpus());
        } else
        if (cathegory.equals("8")) {
            System.out.println("\n\033[1mSolid state drives:\033[0m");
            displayItems(this.getSsds());
        } else
        if (cathegory.equals("9")) {
            System.out.println("\n\033[1mHard disk drives:\033[0m");
            displayItems(this.getHdds());
        } else {
            System.out.println("\033[1m\033[91mIncorrect input: \033[0m");
        }
    }

    private void displayItems(PCComponent[] items) {
        int counter = 0;
        for (PCComponent item : items) {
            System.out.println(counter + " " + item);
            counter++;
        }
    }

    public void showDetails(String cathegory) {
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
                System.out.println(this.getCases()[index].details());
                return;
            case "1":
                System.out.println(this.getPsus()[index].details());
                break;
            case "2":
                System.out.println(this.getMotherboards()[index].details());
                break;
            case "3":
                System.out.println(this.getCpus()[index].details());
                break;
            case "4":
                System.out.println(this.getHeatsinks()[index].details());
                break;
            case "5":
                System.out.println(this.getFans()[index].details());
                break;
            case "6":
                System.out.println(this.getMemories()[index].details());
                break;
            case "7":
                System.out.println(this.getGpus()[index].details());
                break;
            case "8":
                System.out.println(this.getSsds()[index].details());
                break;
            case "9":
                System.out.println(this.getHdds()[index].details());
                break;
        }
        System.out.println();
    }

    private int getIndexOfItem(PCComponent[] array, PCComponent item) {
        int counter = 0;
        for (PCComponent part : array) {
            if (part.equals(item)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    public void addItem(Case item) {
        Case[] newArray = new Case[cases.length + 1];
        int counter = 0;
        for (Case aCase : cases) {
            newArray[counter] = aCase;
            counter++;
        }
        newArray[cases.length] = item;
        cases = newArray;
    }

    public void addItem(PowerSupply item) {
        PowerSupply[] newArray = new PowerSupply[psus.length + 1];
        int counter = 0;
        for (PowerSupply psu : psus) {
            newArray[counter] = psu;
            counter++;
        }
        newArray[psus.length] = item;
        psus = newArray;
    }

    public void addItem(Motherboard item) {
        Motherboard[] newArray = new Motherboard[motherboards.length + 1];
        int counter = 0;
        for (Motherboard motherboard : motherboards) {
            newArray[counter] = motherboard;
            counter++;
        }
        newArray[motherboards.length] = item;
        motherboards = newArray;
    }

    public void addItem(CPU item) {
        CPU[] newArray = new CPU[cpus.length + 1];
        int counter = 0;
        for (CPU cpu : cpus) {
            newArray[counter] = cpu;
            counter++;
        }
        newArray[cpus.length] = item;
        cpus = newArray;
    }

    public void addItem(Heatsink item) {
        Heatsink[] newArray = new Heatsink[heatsinks.length + 1];
        int counter = 0;
        for (Heatsink heatsink : heatsinks) {
            newArray[counter] = heatsink;
            counter++;
        }
        newArray[heatsinks.length] = item;
        heatsinks = newArray;
    }

    public void addItem(Fan item) {
        Fan[] newArray = new Fan[fans.length + 1];
        int counter = 0;
        for (Fan fan : fans) {
            newArray[counter] = fan;
            counter++;
        }
        newArray[fans.length] = item;
        fans = newArray;
    }

    public void addItem(Memory item) {
        Memory[] newArray = new Memory[memories.length + 1];
        int counter = 0;
        for (Memory memory : memories) {
            newArray[counter] = memory;
            counter++;
        }
        newArray[memories.length] = item;
        memories = newArray;
    }

    public void addItem(GraphicsCard item) {
        GraphicsCard[] newArray = new GraphicsCard[gpus.length + 1];
        int counter = 0;
        for (GraphicsCard gpu : gpus) {
            newArray[counter] = gpu;
            counter++;
        }
        newArray[gpus.length] = item;
        gpus = newArray;
    }

    public void addItem(SolidStateDrive item) {
        SolidStateDrive[] newArray = new SolidStateDrive[ssds.length + 1];
        int counter = 0;
        for (SolidStateDrive ssd : ssds) {
            newArray[counter] = ssd;
            counter++;
        }
        newArray[ssds.length] = item;
        ssds = newArray;
    }

    public void addItem(HardDiskDrive item) {
        HardDiskDrive[] newArray = new HardDiskDrive[hdds.length + 1];
        int counter = 0;
        for (HardDiskDrive hdd : hdds) {
            newArray[counter] = hdd;
            counter++;
        }
        newArray[hdds.length] = item;
        hdds = newArray;
    }

    public void deleteItem(Case item) {
        int index = getIndexOfItem(cases, item);
        Case[] newArray = new Case[cases.length - 1];
        int counter = 0;
        for (Case aCase : cases) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = aCase;
            counter++;
        }
        cases = newArray;
    }

    public void deleteItem(PowerSupply item) {
        int index = getIndexOfItem(psus, item);
        PowerSupply[] newArray = new PowerSupply[psus.length - 1];
        int counter = 0;
        for (PowerSupply psu : psus) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = psu;
            counter++;
        }
        psus = newArray;
    }

    public void deleteItem(Motherboard item) {
        int index = getIndexOfItem(motherboards, item);
        Motherboard[] newArray = new Motherboard[motherboards.length - 1];
        int counter = 0;
        for (Motherboard motherboard : motherboards) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = motherboard;
            counter++;
        }
        motherboards = newArray;
    }

    public void deleteItem(CPU item) {
        int index = getIndexOfItem(cpus, item);
        CPU[] newArray = new CPU[cpus.length - 1];
        int counter = 0;
        for (CPU cpu : cpus) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = cpu;
            counter++;
        }
        cpus = newArray;
    }

    public void deleteItem(Heatsink item) {
        int index = getIndexOfItem(heatsinks, item);
        Heatsink[] newArray = new Heatsink[heatsinks.length - 1];
        int counter = 0;
        for (Heatsink heatsink : heatsinks) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = heatsink;
            counter++;
        }
        heatsinks = newArray;
    }

    public void deleteItem(Fan item) {
        int index = getIndexOfItem(fans, item);
        Fan[] newArray = new Fan[fans.length - 1];
        int counter = 0;
        for (Fan fan : fans) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = fan;
            counter++;
        }
        fans = newArray;
    }

    public void deleteItem(Memory item) {
        int index = getIndexOfItem(memories, item);
        Memory[] newArray = new Memory[memories.length - 1];
        int counter = 0;
        for (Memory memory : memories) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = memory;
            counter++;
        }
        memories = newArray;
    }

    public void deleteItem(GraphicsCard item) {
        int index = getIndexOfItem(gpus, item);
        GraphicsCard[] newArray = new GraphicsCard[gpus.length - 1];
        int counter = 0;
        for (GraphicsCard gpu : gpus) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = gpu;
            counter++;
        }
        gpus = newArray;
    }

    public void deleteItem(SolidStateDrive item) {
        int index = getIndexOfItem(ssds, item);
        SolidStateDrive[] newArray = new SolidStateDrive[ssds.length - 1];
        int counter = 0;
        for (SolidStateDrive ssd : ssds) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = ssd;
            counter++;
        }
        ssds = newArray;
    }

    public void deleteItem(HardDiskDrive item) {
        int index = getIndexOfItem(hdds, item);
        HardDiskDrive[] newArray = new HardDiskDrive[hdds.length - 1];
        int counter = 0;
        for (HardDiskDrive hdd : hdds) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = hdd;
            counter++;
        }
        hdds = newArray;
    }

    public Case[] getCases() {
        return cases;
    }

    public PowerSupply[] getPsus() {
        return psus;
    }

    public Motherboard[] getMotherboards() {
        return motherboards;
    }

    public CPU[] getCpus() {
        return cpus;
    }

    public Heatsink[] getHeatsinks() {
        return heatsinks;
    }

    public Fan[] getFans() {
        return fans;
    }

    public Memory[] getMemories() {
        return memories;
    }

    public GraphicsCard[] getGpus() {
        return gpus;
    }

    public SolidStateDrive[] getSsds() {
        return ssds;
    }

    public HardDiskDrive[] getHdds() {
        return hdds;
    }

}