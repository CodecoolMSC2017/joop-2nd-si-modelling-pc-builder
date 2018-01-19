package com.codecool;

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

    public GraphicsCard[] getGraphicsCards() {
        return gpus;
    }

    public Storage[] getStorages() {
        return storages;
    }

    @Override

    public String toString() {
        return name;
    }

}