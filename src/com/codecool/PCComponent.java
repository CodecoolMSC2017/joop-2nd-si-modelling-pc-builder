package com.codecool;

import java.util.Objects;

public class PCComponent {

    private String name;
    private String manufacturer;
    private int value;
    private Tier tier;

    public PCComponent(String name, String manufacturer, int value, Tier tier) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.value = value;
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getValue() {
        return value;
    }

    public Tier getTier() {
        return tier;
    }

    @Override
    public String toString() {
        return this.getName();
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
        PCComponent component = (PCComponent)o;
        return Objects.equals(this.getName(), component.getName());
    }

}