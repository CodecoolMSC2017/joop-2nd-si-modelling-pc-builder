package com.codecool;

import java.util.Objects;

public class Motherboard extends Electronic {

    private Size size;
    private String socket;
    private int amountOfSockets;
    private String memoryType;
    private int amountOfMemorySlots;
    private int amountOfPCIESlots;
    private int amountOfSata;

    public Motherboard(String name, String manufacturer, int value, Tier tier, int powerConsumption,
    Size size, String socket, int amountOfSockets, String memoryType, int amountOfMemorySlots,
    int amountOfPCIESlots, int amountOfSata) {
        super(name, manufacturer, value, tier, powerConsumption);
        this.size = size;
        this.socket = socket;
        this.amountOfSockets = amountOfSockets;
        this.memoryType = memoryType;
        this.amountOfMemorySlots = amountOfMemorySlots;
        this.amountOfPCIESlots = amountOfPCIESlots;
        this.amountOfSata = amountOfSata;
    }

    public Size getSize() {
        return size;
    }

    public String getSocket() {
        return socket;
    }

    public int getAmountOfSockets() {
        return amountOfSockets;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public int getAmountOfMemorySlots() {
        return amountOfMemorySlots;
    }

    public int getAmountOfPCIESlots() {
        return amountOfPCIESlots;
    }

    public int getAmountOfSata() {
        return amountOfSata;
    }

    public String details() {
        return "\n\033[1m   Name: " + this.getName() + "\n" +
               "   Manufacturer: " + this.getManufacturer() + "\n" +
               "   Value: " + this.getValue() + "\n" +
               "   Tier: " + this.getTier() + "\n" +
               "   Power consumption: " + this.getPowerConsumption() + "W\n" +
               "   Size: " + this.getSize() + "\n" +
               "   Socket: " + this.getSocket() + "\n" +
               "   Amount of sockets: " + this.getAmountOfSockets() + "\n" +
               "   Supported memory type: " + this.getMemoryType() + "\n" +
               "   Amount of memory slots " + this.getAmountOfMemorySlots() + "\n" +
               "   Amount of PCI-e x16 slots " + this.getAmountOfPCIESlots() + "\n" +
               "   Amount of SATA3 connectors " + this.getAmountOfSata();
    }

    @Override

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
        Motherboard board = (Motherboard)o;
        return Objects.equals(this.getName(), board.getName());
    }

}