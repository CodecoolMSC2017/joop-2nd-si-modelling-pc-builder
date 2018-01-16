package com.codecool;

import java.util.Objects;

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
        CPU cpu = (CPU)o;
        return Objects.equals(this.getName(), cpu.getName());
    }

}