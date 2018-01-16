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