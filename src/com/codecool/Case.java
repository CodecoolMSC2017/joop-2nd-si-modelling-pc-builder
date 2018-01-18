package com.codecool;

import java.util.Objects;

public class Case extends PCComponent {

    private Size size;
    private int ssdCapacity;
    private int hddCapacity;
    private int frontFanCapacity;
    private int rearFanCapacity;

    public Case(String name, String manufacturer, int value, Tier tier, Size size,
    int ssdCapacity, int hddCapacity, int frontFanCapacity, int rearFanCapacity) {
        super(name, manufacturer, value, tier);
        this.size = size;
        this.ssdCapacity = ssdCapacity;
        this.hddCapacity = hddCapacity;
        this.frontFanCapacity = frontFanCapacity;
        this.rearFanCapacity = rearFanCapacity;
    }

    public Size getSize() {
        return size;
    }

    public int getSSDCapacity() {
        return ssdCapacity;
    }

    public int getHDDCapacity() {
        return hddCapacity;
    }

    public int getFrontFanCapacity() {
        return frontFanCapacity;
    }

    public int getRearFanCapacity() {
        return rearFanCapacity;
    }

    public String details() {
        return "\n\033[1m   Name: " + this.getName() + "\n" +
               "   Manufacturer: " + this.getManufacturer() + "\n" +
               "   Value: " + this.getValue() + "\n" +
               "   Tier: " + this.getTier() + "\n" +
               "   Size: " + this.getSize() + "\n" +
               "   SSD capacity: " + this.getSSDCapacity() + "\n" +
               "   HDD capacity: " + this.getHDDCapacity() + "\n" +
               "   Front fan capacity: " + this.getFrontFanCapacity() + "\n" +
               "   Rear fan capacity: " + this.getRearFanCapacity() + "\033[0m";
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
        Case casing = (Case)o;
        return Objects.equals(this.getName(), casing.getName());
    }

}