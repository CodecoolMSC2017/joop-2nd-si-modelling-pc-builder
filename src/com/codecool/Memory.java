package com.codecool;

import java.util.Objects;

public class Memory extends Electronic {

    private int capacity;
    private String type;
    private int amountOfSticks;

    public Memory(String name, String manufacturer, int value, Tier tier,
    int powerConsumption, int capacity, String type, int amountOfSticks) {
        super(name, manufacturer, value, tier, powerConsumption);
        this.capacity = capacity;
        this.type = type;
        this.amountOfSticks = amountOfSticks;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public int getAmountOfSticks() {
        return amountOfSticks;
    }

    public String details() {
        return "\n\033[1m   Name: " + this.getName() + "\n" +
               "   Manufacturer: " + this.getManufacturer() + "\n" +
               "   Value: $" + this.getValue() + "\n" +
               "   Tier: " + this.getTier() + "\n" +
               "   Power consumption: " + this.getPowerConsumption() + "W\n" +
               "   Capacity: " + this.getCapacity() + "GB\n" +
               "   Memory type: " + this.getType() + "\n" +
               "   Amount of modules: " + this.getAmountOfSticks();
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
        Memory mem = (Memory)o;
        return Objects.equals(this.getName(), mem.getName());
    }

}