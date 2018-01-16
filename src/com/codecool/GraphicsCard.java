package com.codecool;

public class GraphicsCard extends ProcessingUnit {

    private int vram;

    public GraphicsCard(int vram, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.vram = vram;
    }
}