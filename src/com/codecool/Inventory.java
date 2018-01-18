package com.codecool;

public class Inventory {

    private Case[] cases;
    private PowerSupply[] psus;
    private Motherboard[] motherboards;
    private CPU[] cpus;
    private Heatsink[] heatsinks;
    private Fan[] fans;
    private Memory[] memories;
    private GraphicsCard[] gpus;
    private SolidStateDrive[] ssds;
    private HardDiskDrive[] hdds;

    public Inventory(Case[] cases, PowerSupply[] psus, Motherboard[] motherboards, CPU[] cpus,
        Heatsink[] heatsinks, Fan[] fans, Memory[] memories, GraphicsCard[] gpus,
        SolidStateDrive[] ssds, HardDiskDrive[] hdds) {
        this.cases = cases;
        this.psus = psus;
        this.motherboards = motherboards;
        this.cpus = cpus;
        this.heatsinks = heatsinks;
        this.fans = fans;
        this.memories = memories;
        this.gpus = gpus;
        this.ssds = ssds;
        this.hdds = hdds;
    }

    public void addItem(Case item) {
        Case[] newArray = new Case[cases.length + 1];
        int counter = 0;
        for (Case aCase : cases) {
            newArray[counter] = aCase;
            counter++;
        }
        newArray[cases.length] = item;
        cases = newArray;
    }

    public void addItem(PowerSupply item) {
        PowerSupply[] newArray = new PowerSupply[psus.length + 1];
        int counter = 0;
        for (PowerSupply psu : psus) {
            newArray[counter] = psu;
            counter++;
        }
        newArray[psus.length] = item;
        psus = newArray;
    }

    public void addItem(Motherboard item) {
        Motherboard[] newArray = new Motherboard[motherboards.length + 1];
        int counter = 0;
        for (Motherboard motherboard : motherboards) {
            newArray[counter] = motherboard;
            counter++;
        }
        newArray[motherboards.length] = item;
        motherboards = newArray;
    }

    public void addItem(CPU item) {
        CPU[] newArray = new CPU[cpus.length + 1];
        int counter = 0;
        for (CPU cpu : cpus) {
            newArray[counter] = cpu;
            counter++;
        }
        newArray[cpus.length] = item;
        cpus = newArray;
    }

    public void addItem(Heatsink item) {
        Heatsink[] newArray = new Heatsink[heatsinks.length + 1];
        int counter = 0;
        for (Heatsink heatsink : heatsinks) {
            newArray[counter] = heatsink;
            counter++;
        }
        newArray[heatsinks.length] = item;
        heatsinks = newArray;
    }

    public void addItem(Fan item) {
        Fan[] newArray = new Fan[fans.length + 1];
        int counter = 0;
        for (Fan fan : fans) {
            newArray[counter] = fan;
            counter++;
        }
        newArray[fans.length] = item;
        fans = newArray;
    }

    public void addItem(Memory item) {
        Memory[] newArray = new Memory[memories.length + 1];
        int counter = 0;
        for (Memory memory : memories) {
            newArray[counter] = memory;
            counter++;
        }
        newArray[memories.length] = item;
        memories = newArray;
    }

    public void addItem(GraphicsCard item) {
        GraphicsCard[] newArray = new GraphicsCard[gpus.length + 1];
        int counter = 0;
        for (GraphicsCard gpu : gpus) {
            newArray[counter] = gpu;
            counter++;
        }
        newArray[gpus.length] = item;
        gpus = newArray;
    }

    public void addItem(SolidStateDrive item) {
        SolidStateDrive[] newArray = new SolidStateDrive[ssds.length + 1];
        int counter = 0;
        for (SolidStateDrive ssd : ssds) {
            newArray[counter] = ssd;
            counter++;
        }
        newArray[ssds.length] = item;
        ssds = newArray;
    }

    public void addItem(HardDiskDrive item) {
        HardDiskDrive[] newArray = new HardDiskDrive[hdds.length + 1];
        int counter = 0;
        for (HardDiskDrive hdd : hdds) {
            newArray[counter] = hdd;
            counter++;
        }
        newArray[hdds.length] = item;
        hdds = newArray;
    }

    public void deleteItem(Case item) {

    }

    public void deleteItem(PowerSupply item) {

    }

    public void deleteItem(Motherboard item) {

    }

    public void deleteItem(CPU item) {

    }

    public void deleteItem(Heatsink item) {

    }

    public void deleteItem(Fan item) {

    }

    public void deleteItem(Memory item) {

    }

    public void deleteItem(GraphicsCard item) {

    }

    public void deleteItem(SolidStateDrive item) {

    }

    public void deleteItem(HardDiskDrive item) {

    }

    public Case[] getCases() {
        return cases;
    }

    public PowerSupply[] getPsus() {
        return psus;
    }

    public Motherboard[] getMotherboards() {
        return motherboards;
    }

    public CPU[] getCpus() {
        return cpus;
    }

    public Heatsink[] getHeatsinks() {
        return heatsinks;
    }

    public Fan[] getFans() {
        return fans;
    }

    public Memory[] getMemories() {
        return memories;
    }

    public GraphicsCard[] getGpus() {
        return gpus;
    }

    public SolidStateDrive[] getSsds() {
        return ssds;
    }

    public HardDiskDrive[] getHdds() {
        return hdds;
    }

}