package com.codecool;

import java.util.Objects;

public class Computer extends Inventory {

    private String name;
    private boolean functional;
    private boolean isTurnedOn;

    public Computer(String name) {
        super(new Case[0], new PowerSupply[0], new Motherboard[0], new CPU[0], new Heatsink[0],
            new Fan[0], new Memory[0], new GraphicsCard[0], new SolidStateDrive[0], new HardDiskDrive[0]);
        this.name = name;
        this.functional = false;
        this.isTurnedOn = false;
    }

    public int getAmountOfFreeMemorySlots() throws ArrayIndexOutOfBoundsException {
        int amountOfSticks = 0;
        for (Memory ram : this.getMemories()) {
            amountOfSticks += ram.getAmountOfSticks();
        }
        return this.getMotherboards()[0].getAmountOfMemorySlots() - amountOfSticks;
    }

    public void checkIfFunctional() {
        if (this.getCases().length > 0 || this.getPsus().length > 0 || this.getMotherboards().length > 0 ||
        this.getCpus().length > 0 ||    this.getHeatsinks().length > 0 || this.getFans().length > 0 ||
        this.getMemories().length > 0 || this.getGpus().length > 0 || this.getSsds().length +
        this.getHdds().length > 0) {
            this.functional = true;
            return;
        }
        this.functional = false;
    }

    public int getPowerConsumption() {
        int powerConsumption = 0;
        if (this.getMotherboards().length > 0) {
            powerConsumption += this.getMotherboards()[0].getPowerConsumption();
        }
        if (this.getCpus().length > 0) {
            for (CPU cpu : this.getCpus()) {
                powerConsumption += cpu.getPowerConsumption();
            }
        }
        if (this.getFans().length > 0) {
            for (Fan fan : this.getFans()) {
                powerConsumption += fan.getPowerConsumption();
            }
        }
        if (this.getMemories().length > 0) {
            for (Memory ram : this.getMemories()) {
                powerConsumption += ram.getPowerConsumption();
            }
        }
        if (this.getGpus().length > 0) {
            for (GraphicsCard gpu : this.getGpus()) {
                powerConsumption += gpu.getPowerConsumption();
            }
        }
        if (this.getSsds().length > 0) {
            for (SolidStateDrive ssd : this.getSsds()) {
                powerConsumption += ssd.getPowerConsumption();
            }
        }
        if (this.getHdds().length > 0) {
            for (HardDiskDrive hdd : this.getHdds()) {
                powerConsumption += hdd.getPowerConsumption();
            }
        }
        return powerConsumption;
    }

    public String details() {
        String theCase = "";
        if (this.getCases().length == 0) {
            theCase = "\033[91m   None\n\033[0m";
        } else {
            theCase = "   " + this.getCases()[0].toString() + "\n";
        }
        String psu = "";
        if (this.getPsus().length == 0) {
            psu = "\033[91m   None\n\033[0m";
        } else {
            psu = "   " + this.getPsus()[0].toString() + "\n";
        }
        String motherboard = "";
        if (this.getMotherboards().length == 0) {
            motherboard = "\033[91m   None\n\033[0m";
        } else {
            motherboard = "   " + this.getMotherboards()[0].toString() + "\n";
        }
        String cpus = "";
        if (this.getCpus().length == 0) {
            cpus = "\033[91m   None\n\033[0m";
        } else {
            for (CPU cpu : this.getCpus()) {
                cpus += "   " + cpu.toString() + "\n";
            }
        }
        String heatsinks = "";
        if (this.getHeatsinks().length == 0) {
            heatsinks = "\033[91m   None\n\033[0m";
        } else {
            for (Heatsink heatsink : this.getHeatsinks()) {
                heatsinks += "   " + heatsink.toString() + "\n";
            }
        }
        String fans = "";
        if (this.getFans().length == 0) {
            fans = "\033[91m   None\n\033[0m";
        } else {
            for (Fan fan : this.getFans()) {
                fans += "   " + fan.toString() + "\n";
            }
        }
        String memories = "";
        if (this.getMemories().length == 0) {
            memories = "\033[91m   None\n\033[0m";
        } else {
            for (Memory memory : this.getMemories()) {
                memories += "   " + memory.toString() + "\n";
            }
        }
        String gpus = "";
        if (this.getGpus().length == 0) {
            gpus = "\033[91m   None\n\033[0m";
        } else {
            for (GraphicsCard gpu : this.getGpus()) {
                gpus += "   " + gpu.toString() + "\n";
            }
        }
        String ssds = "";
        if (this.getSsds().length == 0) {
            ssds = "\033[91m   None\n\033[0m";
        } else {
            for (SolidStateDrive ssd : this.getSsds()) {
                ssds += "   " + ssd.toString() + "\n";
            }
        }
        String hdds = "";
        if (this.getHdds().length == 0) {
            hdds = "\033[91m   None\n\033[0m";
        } else {
            for (Storage hdd : this.getHdds()) {
                hdds += "   " + hdd.toString() + "\n";
            }
        }
        return "\n\033[1mComponents of " + this.getName() + ":" +
            "\nCase:\033[0m\n" + theCase +
            "\033[1mPower supply:\033[0m\n" + psu +
            "\033[1mMotherboard:\033[0m\n" + motherboard +
            "\033[1mProcessor(s):\033[0m\n" + cpus +
            "\033[1mHeatsink(s):\033[0m\n" + heatsinks +
            "\033[1mFan(s):\033[0m\n" + fans +
            "\033[1mMemory:\033[0m\n" + memories +
            "\033[1mGraphics card(s):\033[0m\n" + gpus +
            "\033[1mSolid state drives:\033[0m\n" + ssds +
            "\033[1mHard disk drives:\033[0m\n" + hdds +
            "\n\033[1mFunctional: " + functional +
            "\n\033[1mPower consumption: " + getPowerConsumption() + "W\033[0m\n";
    }

    public void turnOn() {
        this.isTurnedOn = true;
    }

    public void turnOff() {
        this.isTurnedOn = false;
    }

    public String getName() {
        return name;
    }

    public boolean getFunctional() {
        return functional;
    }

    public boolean getIsTurnedOn() {
        return isTurnedOn;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Computer computer = (Computer)o;
        return Objects.equals(this.getName(), computer.getName());
    }

}