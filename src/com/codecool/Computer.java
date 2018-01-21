package com.codecool;

import java.util.Objects;

public class Computer {

    private String name;
    private Case casing;
    private PowerSupply psu;
    private Motherboard motherboard;
    private CPU[] cpus;
    private Heatsink[] heatsinks;
    private Fan[] fans;
    private Memory[] rams;
    private GraphicsCard[] gpus;
    private Storage[] storages;
    private boolean functional;

    public Computer(String name, Case casing, PowerSupply psu, Motherboard motherboard, CPU[] cpus,
        Heatsink[] heatsinks, Fan[] fans, Memory[] rams, GraphicsCard[] gpus, Storage[] storages) {
        this.name = name;
        this.casing = casing;
        this.psu = psu;
        this.motherboard = motherboard;
        this.cpus = cpus;
        this.heatsinks = heatsinks;
        this.fans = fans;
        this.rams = rams;
        this.gpus = gpus;
        this.storages = storages;
        this.functional = false;
    }

    public String details() {
        String theCase = "";
        if (this.getCase() == null) {
            theCase = "\033[91mNone\n\033[0m";
        } else {
            theCase = this.getCase().getName();
        }
        String psu = "";
        if (this.getPsu() == null) {
            psu = "\033[91mNone\n\033[0m";
        } else {
            psu = this.getPsu().getName();
        }
        String motherboard = "";
        if (this.getMotherboard() == null) {
            motherboard = "\033[91mNone\n\033[0m";
        } else {
            motherboard = this.getMotherboard().getName();
        }
        String cpus = "";
        if (this.getCPUs().length == 0) {
            cpus = "\033[91mNone\n\033[0m";
        } else {
            for (CPU cpu : this.getCPUs()) {
                cpus += cpu.getName() + "\n";
            }
        }
        String heatsinks = "";
        if (this.getHeatsinks().length == 0) {
            heatsinks = "\033[91mNone\n\033[0m";
        } else {
            for (Heatsink heatsink : this.getHeatsinks()) {
                heatsinks += heatsink.getName() + "\n";
            }
        }
        String fans = "";
        if (this.getFans().length == 0) {
            fans = "\033[91mNone\n\033[0m";
        } else {
            for (Fan fan : this.getFans()) {
                fans += fan.getName() + "\n";
            }
        }
        String memories = "";
        if (this.getMemories().length == 0) {
            memories = "\033[91mNone\n\033[0m";
        } else {
            for (Memory memory : this.getMemories()) {
                memories += memory.getName() + "\n";
            }
        }
        String gpus = "";
        if (this.getGpus().length == 0) {
            gpus = "\033[91mNone\n\033[0m";
        } else {
            for (GraphicsCard gpu : this.getGpus()) {
                gpus += gpu.getName() + "\n";
            }
        }
        String storage = "";
        if (this.getStorages().length == 0) {
            storage = "\033[91mNone\n\033[0m";
        } else {
            for (Storage stor : storages) {
                storage += stor.getName() + "\n";
            }
        }
        return "\n\033[1mCase:\033[0m\n   " + theCase +
            "\033[1mPower supply:\033[0m\n   " + psu +
            "\033[1mMotherboard:\033[0m\n   " + motherboard +
            "\033[1mProcessor(s):\033[0m\n   " + cpus +
            "\033[1mHeatsink(s):\033[0m\n   " + heatsinks +
            "\033[1mFan(s):\033[0m\n   " + fans +
            "\033[1mMemory:\033[0m\n   " + memories +
            "\033[1mGraphics card(s):\033[0m\n   " + gpus +
            "\033[1mStorage:\033[0m\n   " + storage;
    }

    public void checkFunctional() {
        System.out.println("in progress");
    }

    public String getName() {
        return name;
    }

    public Case getCase() {
        return casing;
    }

    public PowerSupply getPsu() {
        return psu;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public CPU[] getCPUs() {
        return cpus;
    }
    
    public Heatsink[] getHeatsinks() {
        return heatsinks;
    }

    public Fan[] getFans() {
        return fans;
    }

    public Memory[] getMemories() {
        return rams;
    }

    public GraphicsCard[] getGpus() {
        return gpus;
    }

    public Storage[] getStorages() {
        return storages;
    }

    public boolean getFunctional() {
        return functional;
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