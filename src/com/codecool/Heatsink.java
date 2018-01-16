package com.codecool;

import java.util.Objects;

public class Heatsink extends PCComponent {

    private Size size;

    public Heatsink(Size size, String name, String manufacturer, int value, Tier tier) {
        super(name, manufacturer, value, tier);
        this.size = size;
    }

    public Size getSize() {
        return size;
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
        Heatsink heatsink = (Heatsink)o;
        return Objects.equals(this.getName(), heatsink.getName());
    }

}