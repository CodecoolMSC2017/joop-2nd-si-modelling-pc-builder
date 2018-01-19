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