package com.codecool.api.components;

import com.codecool.api.enums.Temperature;
import com.codecool.api.enums.Tier;

public abstract class ProcessingUnit extends Electronic {

    private Temperature temperature;
    private String memoryType;
    private int coreClock;
    private boolean overclockable;

    public ProcessingUnit(String name, String manufacturer, int value, Tier tier,
                          int powerConsumption, String memoryType, int coreClock, boolean overclockable) {
        super(name, manufacturer, value, tier, powerConsumption);
        this.temperature = Temperature.valueOf("AMBIENT");
        this.memoryType = memoryType;
        this.coreClock = coreClock;
        this.overclockable = overclockable;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public int getCoreClock() {
        return coreClock;
    }

    public boolean getOverclockable() {
        return overclockable;
    }

}