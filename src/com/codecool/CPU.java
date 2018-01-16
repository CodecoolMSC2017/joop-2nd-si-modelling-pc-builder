package com.codecool;

public class CPU extends ProcessingUnit {

    private int threads;
    private int cores;

    public CPU(int threads, int cores, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.threads = threads;
        this.cores = cores;
    }

    public int getThreads() {
        return threads;
    }

    public int getCores() {
        return cores;
    }

}