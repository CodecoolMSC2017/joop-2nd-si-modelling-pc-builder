package com.codecool;

import java.util.Objects;

public class GraphicsCard extends ProcessingUnit {

    private int vram;
    private Size size;

    public GraphicsCard(String name, String manufacturer, int value, Tier tier,
        int powerConsumption, String memoryType, int coreClock, boolean overclockable, int vram, Size size) {
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
        GraphicsCard card = (GraphicsCard)o;
        return Objects.equals(this.getName(), card.getName());
    }

}