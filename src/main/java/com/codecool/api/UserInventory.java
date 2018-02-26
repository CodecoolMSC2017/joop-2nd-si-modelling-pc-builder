package com.codecool.api;

import java.util.ArrayList;
import java.util.List;

public class UserInventory extends Inventory {

    private int money;
    private List<Computer> computers = new ArrayList<>();

    public UserInventory(int money) {
        this.money = money;
    }

    public void updateTemperatures(boolean isUnderLoad) {
        for (Computer pc : computers) {
            pc.updateTemperature(isUnderLoad);
        }
    }

    public int mine() {
        int moneyMined = 0;
        for (Computer pc : this.getComputers()) {
            if (pc.isTurnedOn()) {
                moneyMined += pc.getTotalFanciness() * pc.getTotalFanciness();
            }
        }
        return moneyMined / 10;
    }

    public int getAmountOfFunctionalPCs() {
        int amountOfFunctionalPCs = 0;

        for (Computer computer : this.getComputers()) {
            if (computer.getFunctional()) {
                amountOfFunctionalPCs++;
            }
        }
        return amountOfFunctionalPCs;
    }

    public int getAmountOfPCsTurnedOn() {
        int amountOfPCsTurnedOn = 0;

        for (Computer computer : this.getComputers()) {
            if (computer.isTurnedOn()) {
                amountOfPCsTurnedOn++;
            }
        }
        return amountOfPCsTurnedOn;
    }

    public boolean checkIfNameExists(String name) {
        for (Computer pc : computers) {
            if (pc.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Computer getPcByName(String name) {
        for (Computer pc : computers) {
            if (pc.getName().equals(name)) {
                return pc;
            }
        }
        return null;
    }

    public int getMoney() {
        return money;
    }

    public void manageMoney(int difference) {
        money += difference;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void removeComputer(Computer pc) {
        computers.remove(pc);
    }

    public void addComputer(Computer pc) {
        computers.add(pc);
    }

}