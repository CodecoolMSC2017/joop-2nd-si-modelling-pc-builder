package com.codecool;

public class Fan extends Electronic {

    private int rpm;
    private int airflow;

    public Fan(int rpm, int airflow, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.rpm = rpm;
        this.airflow = airflow;
    }

}