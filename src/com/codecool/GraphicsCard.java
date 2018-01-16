package com.codecool;

import java.util.Objects;

public class GraphicsCard extends ProcessingUnit {

    private int vram;
    private Size size;

    public GraphicsCard(int vram, Size size, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.vram = vram;
        this.size = size;
    }

    public int getVram() {
        return vram;
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
        GraphicsCard card = (GraphicsCard)o;
        return Objects.equals(this.getName(), card.getName());
    }

}