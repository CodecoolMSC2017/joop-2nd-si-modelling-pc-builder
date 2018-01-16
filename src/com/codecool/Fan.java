package com.codecool;

import java.util.Objects;

public class Fan extends Electronic {

    private int rpm;
    private int airflow;

    public Fan(int rpm, int airflow, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.rpm = rpm;
        this.airflow = airflow;
    }

    public int getRpm() {
        return rpm;
    }

    public int getAirflow() {
        return airflow;
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
        Fan fan = (Fan)o;
        return Objects.equals(this.getName(), fan.getName());
    }

}