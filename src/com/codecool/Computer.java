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
        String cpus = "";
        for (CPU cpu : this.getCPUs()) {
            cpus += cpu.getName() + "\n";
        }
        String heatsinks = "";
        for (Heatsink heatsink : this.getHeatsinks()) {
            heatsinks += heatsink.getName() + "\n";
        }
        String fans = "";
        for (Fan fan : this.getFans()) {
            fans += fan.getName() + "\n";
        }
        String memories = "";
        for (Memory memory : this.getMemories()) {
            memories += memory.getName() + "\n";
        }
        String gpus = "";
        for (GraphicsCard gpu : this.getGpus()) {
            gpus += gpu.getName() + "\n";
        }
        String storage = "";
        for (Storage stor : storages) {
            storage += stor.getName() + "\n";
        }
        return "\nCase:\n   " + this.getCase() + "\n" +
            "Power supply:\n   " + this.getPsu() + "\n" +
            "Motherboard:\n   " + this.getMotherboard() + "\n" +
            "Processor(s):\n   " + cpus +
            "Heatsink(s):\n   " + heatsinks +
            "Fan(s):\n   " + fans +
            "Memory:\n   " + memories +
            "Graphics card(s)\n   " + gpus +
            "Storage\n   " + storage;
    }

    public void checkFunctional() {
        
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