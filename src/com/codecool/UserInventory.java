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

    public void displayComputers() {
        Computer[] computers = this.getComputers();
        if (computers.length < 1) {
            System.out.println("You don't have any PCs.");
            return;
        }
        for (Computer computer : computers) {
            System.out.println(computer);
        }
    }

    public void handleSell(String cathegory) throws ArrayIndexOutOfBoundsException {
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
                Case aCase = this.getCases()[index];
                this.deleteItem(aCase);
                this.manageMoney(aCase.getValue());
                break;
            case "1":
                PowerSupply psu = this.getPsus()[index];
                this.deleteItem(psu);
                this.manageMoney(psu.getValue());
                break;
            case "2":
                Motherboard motherboard = this.getMotherboards()[index];
                this.deleteItem(motherboard);
                this.manageMoney(motherboard.getValue());
                break;
            case "3":
                CPU cpu = this.getCpus()[index];
                this.deleteItem(cpu);
                this.manageMoney(cpu.getValue());
                break;
            case "4":
                Heatsink heatsink = this.getHeatsinks()[index];
                this.deleteItem(heatsink);
                this.manageMoney(heatsink.getValue());
                break;
            case "5":
                Fan fan = this.getFans()[index];
                this.deleteItem(fan);
                this.manageMoney(fan.getValue());
                break;
            case "6":
                Memory memory = this.getMemories()[index];
                this.deleteItem(memory);
                this.manageMoney(memory.getValue());
                break;
            case "7":
                GraphicsCard gpu = this.getGpus()[index];
                this.deleteItem(gpu);
                this.manageMoney(gpu.getValue());
                break;
            case "8":
                SolidStateDrive ssd = this.getSsds()[index];
                this.deleteItem(ssd);
                this.manageMoney(ssd.getValue());
                break;
            case "9":
                HardDiskDrive hdd = this.getHdds()[index];
                this.deleteItem(hdd);
                this.manageMoney(hdd.getValue());
                break;
        }
        System.out.println("\n\033[1m\033[92mItem sold!\033[0m");
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

    public void addComputer(int index, Computer computer) {
        computers[index] = computer;
    }

}