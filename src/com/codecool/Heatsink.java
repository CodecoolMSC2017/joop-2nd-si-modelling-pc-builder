package com.codecool;

import java.util.Objects;

public class Heatsink extends PCComponent {

    private Size size;

    public Heatsink(String name, String manufacturer, int value, Tier tier, Size size) {
        super(name, manufacturer, value, tier);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public String details() {
        return "\n\033[1m   Name: " + this.getName() + "\n" +
               "   Manufacturer: " + this.getManufacturer() + "\n" +
               "   Value: $" + this.getValue() + "\n" +
               "   Tier: " + this.getTier() + "\n" +
               "   Size: " + this.getSize();
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
        Heatsink heatsink = (Heatsink)o;
        return Objects.equals(this.getName(), heatsink.getName());
    }

}