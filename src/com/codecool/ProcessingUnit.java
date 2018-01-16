package com.codecool;

import java.util.Objects;

public class ProcessingUnit extends Electronic {

    private Temperature temperature;
    private String memoryType;

    public ProcessingUnit(String name, String manufacturer, int value, Tier tier,
    int powerConsumption, String memoryType) {
        super(name, manufacturer, value, tier, powerConsumption);
        this.temperature = Temperature.valueOf("AMBIENT");
        this.memoryType = memoryType;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public String getMemoryType() {
        return memoryType;
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
        ProcessingUnit processor = (ProcessingUnit)o;
        return Objects.equals(this.getName(), processor.getName());
    }

}