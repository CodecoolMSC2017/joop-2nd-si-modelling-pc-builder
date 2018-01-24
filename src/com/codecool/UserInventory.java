package com.codecool;

import java.io.*;

public class UserInventory extends Inventory {

    private int money;
    private Computer[] computers;

    public UserInventory(int money) {
        super(new Case[0], new PowerSupply[0], new Motherboard[0], new CPU[0],
            new Heatsink[0], new Fan[0], new Memory[0], new GraphicsCard[0],
            new SolidStateDrive[0], new HardDiskDrive[0]);
        this.money = money;
        this.computers = new Computer[0];
    }

    public void handleModify() {
        if (this.getComputers().length < 1) {
            System.out.println("\n\033[1m\033[91mYou don't have any PCs.\033[0m");
            return;
        }
        Computer pc = this.selectPC();
        if (pc == null) {
            return;
        }
        while (true) {
            System.out.println(pc.details());
            System.out.println("\nCommands: :add :remove :back\n");
            String action = getUserInput().nextLine().toLowerCase().toLowerCase();

            if (action.equals(":back")) {
                break;
            }
            try {
                if (action.equals(":add")) {
                    this.handleAdd(pc);
                } else
                if (action.equals(":remove")) {
                    this.handleRemove(pc);
                } else {
                    System.out.println("Unknown command: " + action);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
            }
        }
    }

    public void handleCreate() {
        if (this.getComputers().length == 3) {
            System.out.println("\n\033[1m\033[91mYou can only have 3 computers at the same time!\033[0m\n" +
                "\033[1m(You don't want to get the attention of the authorities with your high power bill)\033[0m");
            return;
        }
        System.out.println("\n\033[1mTo create a new PC you have to name it first:\033[0m\n");
        String name = getUserInput().nextLine().toLowerCase();
        this.addComputer(new Computer(name));
        System.out.println("\n\033[1m\033[92mYour new PC " + name + " has been created!\033[0m");
        System.out.println("\033[1mMay your framerates be high and temperatures low!\033[0m");
        System.out.println("See :modify to select it's components");
    }

    public void handleAdd(Computer pc) throws ArrayIndexOutOfBoundsException {
        String cathegory = this.chooseCathegory("\033[1mSelect an item\033[0m", pc);
        if (cathegory.equals(":back")) {
            return;
        }
        System.out.print("\nSelect an item by it's number: ");
        String input = getUserInput().nextLine().toLowerCase().toLowerCase();
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
                    if (pc.getCases().length > 0) {
                        throw new NoMoreRoomException();
                    }
                    Case aCase = this.getCases()[index];
                    if (pc.getMotherboards().length != 0) {
                        if (pc.getMotherboards()[0].getSize().getValue() > aCase.getSize().getValue()) {
                            throw new ComponentsDoNotMatchException("This case cannot fit the current motherboard.");
                        }
                    }
                    if (pc.getHeatsinks().length > 0) {
                        for (Heatsink heatsink : pc.getHeatsinks()) {
                            if (heatsink.getSize().getValue() > aCase.getSize().getValue()) {
                                throw new ComponentsDoNotMatchException("This case cannot fit the current heatsink.");
                            }
                        }
                    }
                    if (pc.getGpus().length > 0) {
                        for (GraphicsCard gpu : pc.getGpus()) {
                            if (gpu.getSize().getValue() > aCase.getSize().getValue()) {
                                throw new ComponentsDoNotMatchException("This case cannot fit the current graphics card.");
                            }
                        }
                    }
                    pc.addItem(aCase);
                    this.deleteItem(aCase);
                    return;
                case "1":
                    if (pc.getPsus().length > 0) {
                        throw new NoMoreRoomException();
                    }
                    PowerSupply psu = this.getPsus()[index];
                    if (pc.getPowerConsumption() > psu.getPerformance()) {
                        throw new ComponentsDoNotMatchException("This power supply is not enough to power this beast of a system.");
                    }
                    pc.addItem(psu);
                    this.deleteItem(psu);
                    return;
                case "2":
                    if (pc.getMotherboards().length > 0) {
                        throw new NoMoreRoomException();
                    }
                    Motherboard motherboard = this.getMotherboards()[index];
                    if (pc.getCases().length != 0) {
                        if (pc.getCases()[0].getSize().getValue() < motherboard.getSize().getValue()) {
                            throw new ComponentsDoNotMatchException("The current case cannot fit this motherboard.");
                        }
                    }
                    if (pc.getCpus().length > 0) {
                        for (CPU cpu : pc.getCpus()) {
                            if (!cpu.getSocket().equals(motherboard.getSocket())) {
                                throw new ComponentsDoNotMatchException("The current processor's socket does not match the socket of this motherboard.");
                            }
                        }
                    }
                    if (pc.getMemories().length > 0) {
                        for (Memory memory : pc.getMemories()) {
                            if (!memory.getType().equals(motherboard.getMemoryType())) {
                                throw new ComponentsDoNotMatchException("The type of current memory does not match the memory type of this motherboard.");
                            }
                        }
                    }
                    if (pc.getPsus().length > 0) {
                        if (pc.getPsus()[0].getPerformance() < pc.getPowerConsumption() + motherboard.getPowerConsumption()) {
                            throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
                        }
                    }
                    pc.addItem(motherboard);
                    this.deleteItem(motherboard);
                    return;
                case "3":
                    try {
                        if (pc.getCpus().length == pc.getMotherboards()[0].getAmountOfSockets()) {
                            throw new NoMoreRoomException();
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (pc.getCpus().length == 1) {
                            throw new NoMoreRoomException();
                        }
                    }
                    CPU cpu = this.getCpus()[index];
                    if (pc.getMotherboards().length > 0) {
                        if (!pc.getMotherboards()[0].getMemoryType().equals(cpu.getMemoryType())) {
                            throw new ComponentsDoNotMatchException("The memory type of the current mmotherboard does not match the memory type of this processor.");
                        }
                    }
                    if (pc.getMemories().length > 0) {
                        for (Memory ram : pc.getMemories()) {
                            if (!ram.getType().equals(cpu.getMemoryType())) {
                                throw new ComponentsDoNotMatchException("The type of current memory does not match the memory type of this processor.");
                            }
                        }
                    }
                    if (pc.getPsus().length > 0) {
                        if (pc.getPsus()[0].getPerformance() < pc.getPowerConsumption() + cpu.getPowerConsumption()) {
                            throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
                        }
                    }
                    pc.addItem(cpu);
                    this.deleteItem(cpu);
                    return;
                case "4":
                    try {
                        if (pc.getHeatsinks().length == pc.getMotherboards()[0].getAmountOfSockets()) {
                            throw new NoMoreRoomException();
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (pc.getHeatsinks().length == 1) {
                            throw new NoMoreRoomException();
                        }
                    }
                    Heatsink heatsink = this.getHeatsinks()[index];
                    if (pc.getCases().length > 0) {
                        if (pc.getCases()[0].getSize().getValue() < heatsink.getSize().getValue()) {
                            throw new ComponentsDoNotMatchException("The current case cannot fit this heatsink.");
                        }
                    }
                    pc.addItem(heatsink);
                    this.deleteItem(heatsink);
                    return;
                case "5":
                    try {
                        if (pc.getFans().length == pc.getCases()[0].getFrontFanCapacity() + pc.getCases()[0].getRearFanCapacity()) {
                            throw new NoMoreRoomException();
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (pc.getFans().length == 2) {
                            throw new NoMoreRoomException();
                        }
                    }
                    Fan fan = this.getFans()[index];
                    if (pc.getPsus().length > 0) {
                        if (pc.getPsus()[0].getPerformance() < pc.getPowerConsumption() + fan.getPowerConsumption()) {
                            throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
                        }
                    }
                    pc.addItem(fan);
                    this.deleteItem(fan);
                    return;
                case "6":
                    Memory memory = this.getMemories()[index];
                    try {
                        if (memory.getAmountOfSticks() > pc.getAmountOfFreeMemorySlots()) {
                            throw new NoMoreRoomException();
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (pc.getMemories().length == 1) {
                            throw new NoMoreRoomException();
                        }
                    }
                    if (pc.getMotherboards().length > 0) {
                        if (!pc.getMotherboards()[0].getMemoryType().equals(memory.getType())) {
                            throw new ComponentsDoNotMatchException("The memory type of the current motherboard does not match the type of this memory.");
                        }
                    }
                    if (pc.getCpus().length > 0) {
                        for (CPU aCpu : pc.getCpus()) {
                            if (!aCpu.getMemoryType().equals(memory.getType())) {
                                throw new ComponentsDoNotMatchException("The memory type of the current processor does not match the type of this memory.");
                            }
                        }
                    }
                    if (pc.getPsus().length > 0) {
                        if (pc.getPsus()[0].getPerformance() < pc.getPowerConsumption() + memory.getPowerConsumption()) {
                            throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
                        }
                    }
                    pc.addItem(memory);
                    this.deleteItem(memory);
                    return;
                case "7":
                    try {
                        if (pc.getMotherboards()[0].getAmountOfPCIESlots() == pc.getGpus().length) {
                            throw new NoMoreRoomException();
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (pc.getGpus().length == 1) {
                            throw new NoMoreRoomException();
                        }
                    }
                    GraphicsCard gpu = this.getGpus()[index];
                    if (pc.getCases().length > 0) {
                        if (pc.getCases()[0].getSize().getValue() < gpu.getSize().getValue()) {
                            throw new ComponentsDoNotMatchException("The current case cannot fit this graphics card.");
                        }
                    }
                    if (pc.getPsus().length > 0) {
                        if (pc.getPsus()[0].getPerformance() < pc.getPowerConsumption() + gpu.getPowerConsumption()) {
                            throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
                        }
                    }
                    pc.addItem(gpu);
                    this.deleteItem(gpu);
                    return;
                case "8":
                    try {
                        if (pc.getSsds().length + pc.getHdds().length == pc.getMotherboards()[0].getAmountOfSata()) {
                            throw new NoMoreRoomException();
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (pc.getSsds().length + pc.getHdds().length == 1) {
                            throw new NoMoreRoomException();
                        }
                    }
                    if (pc.getCases().length > 0) {
                        if (pc.getCases()[0].getSSDCapacity() == pc.getSsds().length) {
                            throw new NoMoreRoomException();
                        }
                    }
                    SolidStateDrive ssd = this.getSsds()[index];
                    if (pc.getPsus().length > 0) {
                        if (pc.getPsus()[0].getPerformance() < pc.getPowerConsumption() + ssd.getPowerConsumption()) {
                            throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
                        }
                    }
                    pc.addItem(ssd);
                    this.deleteItem(ssd);
                    return;
                case "9":
                    try {
                        if (pc.getSsds().length + pc.getHdds().length == pc.getMotherboards()[0].getAmountOfSata()) {
                            throw new NoMoreRoomException();
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (pc.getSsds().length + pc.getHdds().length == 2) {
                            throw new NoMoreRoomException();
                        }
                    }
                    if (pc.getCases().length > 0) {
                        if (pc.getCases()[0].getHDDCapacity() == pc.getHdds().length) {
                            throw new NoMoreRoomException();
                        }
                    }
                    HardDiskDrive hdd = this.getHdds()[index];
                    if (pc.getPsus().length > 0) {
                        if (pc.getPsus()[0].getPerformance() < pc.getPowerConsumption() + hdd.getPowerConsumption()) {
                            throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
                        }
                    }
                    pc.addItem(hdd);
                    this.deleteItem(hdd);
                    return;
            }
        } catch (NoMoreRoomException e) {
            System.out.println("\n\033[1m\033[91mThere is no more room for that component!\n" +
                "You must remove another component of that type first.\033[0m");
        } catch (ComponentsDoNotMatchException e) {
            System.out.println("\n\033[1m\033[91m" + e.getMessage() + "\033[0m");
        }
        pc.checkIfFunctional();
    }

    public void handleRemove(Computer pc) throws ArrayIndexOutOfBoundsException {
        String cathegory = pc.chooseCathegory("\033[1mSelect an item\033[0m", pc);
        if (cathegory.equals(":back")) {
            return;
        }
        System.out.print("\nSelect an item by it's number: ");
        String input = getUserInput().nextLine().toLowerCase().toLowerCase();
        int index = 0;
        try {
            index = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
            return;
        }
        switch(cathegory) {
            case "0":
                Case aCase = pc.getCases()[index];
                pc.deleteItem(aCase);
                this.addItem(aCase);
                return;
            case "1":
                PowerSupply psu = pc.getPsus()[index];
                pc.deleteItem(psu);
                this.addItem(psu);
                return;
            case "2":
                Motherboard motherboard = pc.getMotherboards()[index];
                pc.deleteItem(motherboard);
                this.addItem(motherboard);
                return;
            case "3":
                CPU cpu = pc.getCpus()[index];
                pc.deleteItem(cpu);
                this.addItem(cpu);
                return;
            case "4":
                Heatsink heatsink = pc.getHeatsinks()[index];
                pc.deleteItem(heatsink);
                this.addItem(heatsink);
                return;
            case "5":
                Fan fan = pc.getFans()[index];
                pc.deleteItem(fan);
                this.addItem(fan);
                return;
            case "6":
                Memory memory = pc.getMemories()[index];
                pc.deleteItem(memory);
                this.addItem(memory);
                return;
            case "7":
                GraphicsCard gpu = pc.getGpus()[index];
                pc.deleteItem(gpu);
                this.addItem(gpu);
                return;
            case "8":
                SolidStateDrive ssd = pc.getSsds()[index];
                pc.deleteItem(ssd);
                this.addItem(ssd);
                return;
            case "9":
                HardDiskDrive hdd = pc.getHdds()[index];
                pc.deleteItem(hdd);
                this.addItem(hdd);
                return;
        }
    }

    public void handleDisassemble() {
        if (this.getComputers().length < 1) {
            System.out.println("\n\033[1m\033[91mYou don't have any PCs.\033[0m");
            return;
        }
        Integer index = selectPCByIndex();
        if (index == null) {
            return;
        }
        System.out.println("\n\033[1mAll components will be returned to your inventory and the PC will be deleted.\n" +
            "\033[91mAre you sure you want to disassemble this PC? (y/n)\033[0m\n");
        String choice = getUserInput().nextLine().toLowerCase().toLowerCase();
        index = (int)index;
        String pcName = computers[index].getName();
        if (choice.equals("y")) {
            addComponentsFromPC(index);
            deleteComputer(index);
            System.out.println("\n\033[1m" + pcName + " has been disassembled.");
            return;
        }
        System.out.println("\n\033[1m" + pcName + " remains untouched.");
    }

    public Computer selectPC() {
        String input;
        while (true) {
            System.out.println("\n\033[1mSelect a PC\033[0m");
            System.out.println("Commands: :back (or type the corresponding number)\n");
            this.displayComputers();
            input = getUserInput().nextLine().toLowerCase();
            if (input.equals(":back")) {
                return null;
            }
            try {
                int index = Integer.parseInt(input);
                return this.getComputers()[index];
            } catch(NumberFormatException e) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                continue;
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                continue;
            }
        }
    }

    public Integer selectPCByIndex() {
        String input;
        while (true) {
            System.out.println("\n\033[1mSelect a PC\033[0m");
            System.out.println("Commands: :back (or type the corresponding number)\n");
            this.displayComputers();
            input = getUserInput().nextLine().toLowerCase();
            if (input.equals(":back")) {
                return null;
            }
            try {
                int index = Integer.parseInt(input);
                return index;
            } catch(NumberFormatException e) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                continue;
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
                continue;
            }
        }
    }

    public void displayComputers() {
        Computer[] computers = this.getComputers();
        if (computers.length < 1) {
            System.out.println("You don't have any PCs.");
            return;
        }
        int counter = 0;
        for (Computer computer : computers) {
            System.out.println(counter + " " + computer);
            counter++;
        }
        System.out.println();
    }

    public void handleSell(String cathegory) throws ArrayIndexOutOfBoundsException, EmptyCathegoryException {
        System.out.print("\nSelect an item by it's number: ");
        String input = getUserInput().nextLine().toLowerCase().toLowerCase();
        int index = 0;
        try {
            index = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
            return;
        }
        System.out.println("\n\033[1m\033[92mItem sold!\033[0m");
        switch(cathegory) {
            case "0":
                Case aCase = this.getCases()[index];
                this.deleteItem(aCase);
                this.manageMoney(aCase.getValue());
                if (this.getCases().length < 1) {
                    throw new EmptyCathegoryException();
                }
                break;
            case "1":
                PowerSupply psu = this.getPsus()[index];
                this.deleteItem(psu);
                this.manageMoney(psu.getValue());
                if (this.getPsus().length < 1) {
                    throw new EmptyCathegoryException();
                }
                break;
            case "2":
                Motherboard motherboard = this.getMotherboards()[index];
                this.deleteItem(motherboard);
                this.manageMoney(motherboard.getValue());
                if (this.getMotherboards().length < 1) {
                    throw new EmptyCathegoryException();
                }
                break;
            case "3":
                CPU cpu = this.getCpus()[index];
                this.deleteItem(cpu);
                this.manageMoney(cpu.getValue());
                if (this.getCpus().length < 1) {
                    throw new EmptyCathegoryException();
                }
                break;
            case "4":
                Heatsink heatsink = this.getHeatsinks()[index];
                this.deleteItem(heatsink);
                this.manageMoney(heatsink.getValue());
                if (this.getHeatsinks().length < 1) {
                    throw new EmptyCathegoryException();
                }
                break;
            case "5":
                Fan fan = this.getFans()[index];
                this.deleteItem(fan);
                this.manageMoney(fan.getValue());
                if (this.getFans().length < 1) {
                    throw new EmptyCathegoryException();
                }
                break;
            case "6":
                Memory memory = this.getMemories()[index];
                this.deleteItem(memory);
                this.manageMoney(memory.getValue());
                if (this.getMemories().length < 1) {
                    throw new EmptyCathegoryException();
                }
                break;
            case "7":
                GraphicsCard gpu = this.getGpus()[index];
                this.deleteItem(gpu);
                this.manageMoney(gpu.getValue());
                if (this.getGpus().length < 1) {
                    throw new EmptyCathegoryException();
                }
                break;
            case "8":
                SolidStateDrive ssd = this.getSsds()[index];
                this.deleteItem(ssd);
                this.manageMoney(ssd.getValue());
                if (this.getSsds().length < 1) {
                    throw new EmptyCathegoryException();
                }
                break;
            case "9":
                HardDiskDrive hdd = this.getHdds()[index];
                this.deleteItem(hdd);
                this.manageMoney(hdd.getValue());
                if (this.getHdds().length < 1) {
                    throw new EmptyCathegoryException();
                }
                break;
        }
    }

    public int getMoney() {
        return money;
    }

    public void manageMoney(int difference) {
        money += difference;
    }

    public Computer[] getComputers() {
        return computers;
    }

    private void addComponentsFromPC(int index) {
        Computer pc = computers[index];
        if (pc.getCases().length > 0) {
            this.addItem(pc.getCases()[0]);
        }
        if (pc.getPsus().length > 0) {
            this.addItem(pc.getPsus()[0]);
        }
        if (pc.getMotherboards().length > 0) {
            this.addItem(pc.getMotherboards()[0]);
        }
        if (pc.getCpus().length > 0) {
            for (CPU cpu : pc.getCpus()) {
                this.addItem(cpu);
            }
        }
        if (pc.getHeatsinks().length > 0) {
            for (Heatsink heatsink : pc.getHeatsinks()) {
                this.addItem(heatsink);
            }
        }
        if (pc.getFans().length > 0) {
            for (Fan fan : pc.getFans()) {
                this.addItem(fan);
            }
        }
        if (pc.getMemories().length > 0) {
            for (Memory memory : pc.getMemories()) {
                this.addItem(memory);
            }
        }
        if (pc.getGpus().length > 0) {
            for (GraphicsCard gpu : pc.getGpus()) {
                this.addItem(gpu);
            }
        }
        if (pc.getSsds().length > 0) {
            for (SolidStateDrive ssd : pc.getSsds()) {
                this.addItem(ssd);
            }
        }
        if (pc.getHdds().length > 0) {
            for (HardDiskDrive hdd : pc.getHdds()) {
                this.addItem(hdd);
            }
        }
    }

    private void deleteComputer(int index) {
        Computer[] newArray = new Computer[computers.length - 1];
        int counter = 0;
        for (Computer comp : computers) {
            if (index == counter) {
                index = -1;
                continue;
            }
            newArray[counter] = comp;
            counter++;
        }
        computers = newArray;
    }

    public void addComputer(Computer computer) {
        Computer[] newArray = new Computer[computers.length + 1];
        int counter = 0;
        for (Computer comp : computers) {
            newArray[counter] = comp;
            counter++;
        }
        newArray[computers.length] = computer;
        computers = newArray;
    }

    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream("../saves/save.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("\n\033[1m\033[92mGame saved.\033[0m\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}