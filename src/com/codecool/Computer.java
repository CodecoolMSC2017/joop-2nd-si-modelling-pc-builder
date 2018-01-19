package com.codecool;

public class Computer {

    private String name;
    private Case casing;
    private PowerSupply psu;
    private Motherboard motherboard;
    private Heatsink heatsink;
    private Fan[] fans;
    private CPU[] cpu;
    private Memory[] ram;
    private GraphicsCard[] gpu;
    private Storage[] storage;

    public Computer(String name, Case casing, PowerSupply psu, Motherboard motherboard, Heatsink heatsink,
    Fan[] fans, CPU[] cpu, Memory[] ram, GraphicsCard[] gpu, Storage[] storage) {
        this.name = name;
        this.casing = casing;
        this.psu = psu;
        this.motherboard = motherboard;
        this.heatsink = heatsink;
        this.fans = fans;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.storage = storage;
    }

}