package com.codecool.api.components;

import com.codecool.api.enums.Tier;

public abstract class Storage extends Electronic {

    private int capacity;
    private int transferSpeed;

    public Storage(String name, String manufacturer, int value, Tier tier,
                   int powerConsumption, int capacity, int transferSpeed) {
        super(name, manufacturer, value, tier, powerConsumption);
        this.capacity = capacity;
        this.transferSpeed = transferSpeed;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTransferSpeed() {
        return transferSpeed;
    }

}