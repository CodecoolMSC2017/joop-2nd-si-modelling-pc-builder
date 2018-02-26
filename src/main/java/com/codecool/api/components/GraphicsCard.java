package com.codecool.api.components;

import com.codecool.api.enums.Size;
import com.codecool.api.enums.Tier;

import java.util.Objects;

public class GraphicsCard extends ProcessingUnit {

    private int vram;
    private Size size;

    public GraphicsCard(String name,
                        String manufacturer,
                        int value,
                        Tier tier,
                        int powerConsumption,
                        String memoryType,
                        int coreClock,
                        boolean overclockable,
                        int vram,
                        Size size) {
        super(name, manufacturer, value, tier, powerConsumption, memoryType, coreClock, overclockable);
        this.vram = vram;
        this.size = size;
    }

    public int getVram() {
        return vram;
    }

    public Size getSize() {
        return size;
    }

    public String details() {
        return "Name: " + this.getName() + "\n" +
                "Manufacturer: " + this.getManufacturer() + "\n" +
                "Value: $" + this.getValue() + "\n" +
                "Tier: " + this.getTier() + "\n" +
                "Power consumption: " + this.getPowerConsumption() + "W\n" +
                "Memory type: " + this.getMemoryType() + "\n" +
                "Amount of memory: " + this.getVram() + "GB\n" +
                "Core clock: " + this.getCoreClock() + "Mhz\n" +
                "Overclockable: " + this.getOverclockable() + "\n" +
                "Size: " + this.getSize();
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
        GraphicsCard card = (GraphicsCard) o;
        return Objects.equals(this.getName(), card.getName());
    }

}