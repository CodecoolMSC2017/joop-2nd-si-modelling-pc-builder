package com.codecool;

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
            String action = userInput.nextLine().toLowerCase();

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
        System.out.println("\033[1mTo create a new PC you have to name it first:\033[0m\n");
        String name = userInput.nextLine();
        this.addComputer(new Computer(name));
        System.out.println("\n\033[1m\033[92mYour new PC " + name + " has been created!\033[0m");
        System.out.println("See :modify to select it's components\n");
    }

    public void handleAdd(Computer pc) throws ArrayIndexOutOfBoundsException {
        String cathegory = this.chooseCathegory("\033[1mSelect an item\033[0m", pc);
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
                    pc.addItem(fan);
                    this.deleteItem(fan);
                    return;
                case "6":
                    Memory memory = this.getMemories()[index];
                    try {
                        if (memory.getAmountOfSticks() > amountOfFreeMemorySlots(pc)) {
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
    }

    public void handleRemove(Computer pc) throws ArrayIndexOutOfBoundsException {
        String cathegory = pc.chooseCathegory("\033[1mSelect an item\033[0m", pc);
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

    private int amountOfFreeMemorySlots(Computer pc) throws ArrayIndexOutOfBoundsException {
        int amountOfSticks = 0;
        for (Memory ram : pc.getMemories()) {
            amountOfSticks += ram.getAmountOfSticks();
        }
        return pc.getMotherboards()[0].getAmountOfMemorySlots() - amountOfSticks;
    }

    public Computer selectPC() {
        String input;
        while (true) {
            System.out.println("\n\033[1mSelect a PC\033[0m");
            System.out.println("Commands: :back (or type the corresponding number)\n");
            this.displayComputers();
            input = userInput.nextLine();
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

    public void displayComputers() {
        Computer[] computers = this.getComputers();
        if (computers.length < 1) {
            System.out.println("You don't have any PCs.");
            return;
        }
        int counter = 0;
        for (Computer computer : computers) {
            System.out.println(counter + " " + computer);
        }
        System.out.println();
    }

    public void handleSell(String cathegory) throws ArrayIndexOutOfBoundsException, EmptyCathegoryException {
        System.out.print("\nSelect an item by it's number: ");
        String input = userInput.nextLine().toLowerCase();
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

    public void addComputer(Computer computer) {
        Computer[] newArray = new Computer[computers.length + 1];
        int counter = 0;
        for (Computer motherboard : computers) {
            newArray[counter] = motherboard;
            counter++;
        }
        newArray[computers.length] = computer;
        computers = newArray;
    }

}