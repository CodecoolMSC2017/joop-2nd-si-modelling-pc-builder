package com.codecool;

public class Computer {

    private Case casing;
    private PowerSupply psu;
    private Motherboard motherboard;
    private CPU[] cpu;
    private Memory[] ram;
    private GraphicsCard[] gpu;
    private Storage[] storage;

    public Computer(Case casing, PowerSupply psu, Motherboard motherboard, CPU[] cpu,
    Memory[] ram, GraphicsCard[] gpu, Storage[] storage) {
        this.casing = casing;
        this.psu = psu;
        this.motherboard = motherboard;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.storage = storage;
    }

}