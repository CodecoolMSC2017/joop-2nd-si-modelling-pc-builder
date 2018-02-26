package com.codecool.api.components;

import com.codecool.api.enums.Tier;

import java.util.Objects;

public abstract class PCComponent implements java.io.Serializable {

    static final long serialVersionUID = 4301932672197829586L;

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

    public abstract String details();

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
        return this.getManufacturer() + " " + this.getName() + " ($" + this.getValue() + ")";
    }

}