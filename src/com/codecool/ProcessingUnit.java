package com.codecool;

public class ProcessingUnit extends Electronic {

    private int amountOfCores;

    public ProcessingUnit(int amountOfCores, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.amountOfCores = amountOfCores;
    }

    public int getAmountOfCores() {
        return amountOfCores;
    }

}