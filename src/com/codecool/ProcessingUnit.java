package com.codecool;

public class ProcessingUnit extends PCComponent {

    private int amountOfCores;

    public ProcessingUnit(int amountOfCores, Tier tier, String name, String manufacturer, int powerConsumption, int value) {
        super(tier, name, manufacturer, powerConsumption, value);
        this.amountOfCores = amountOfCores;
    }

    public int getAmountOfCores() {
        return amountOfCores;
    }

}