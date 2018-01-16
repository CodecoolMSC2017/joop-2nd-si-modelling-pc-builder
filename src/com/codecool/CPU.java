package com.codecool;

import java.util.Objects;

public class CPU extends ProcessingUnit {

    private String socket;
    private int threads;
    private int cores;
    private String memoryType;

    public CPU(String socket, int threads, int cores, String memoryType, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.socket = socket;
        this.threads = threads;
        this.cores = cores;
        this.memoryType = memoryType;
    }

    public String getSocket() {
        return socket;
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