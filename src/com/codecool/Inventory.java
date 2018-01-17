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