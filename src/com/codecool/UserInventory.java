package com.codecool;

public class UserInventory extends Inventory {

    private int money;
    private Computer[] computers;

    public UserInventory(int money) {
        super(new Case[] {}, new PowerSupply[] {}, new Motherboard[] {}, new CPU[] {},
            new Heatsink[] {}, new Fan[] {}, new Memory[] {}, new GraphicsCard[] {},
            new SolidStateDrive[] {}, new HardDiskDrive[] {});
        this.money = money;
        this.computers = new Computer[] {};
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