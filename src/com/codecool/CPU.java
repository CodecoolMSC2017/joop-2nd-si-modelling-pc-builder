package com.codecool;

import java.util.Objects;

public class CPU extends ProcessingUnit {

    private String socket;
    private int threads;
    private int cores;

    public CPU(String name, String manufacturer, int value, Tier tier,
        int powerConsumption, String memoryType, int coreClock, boolean overclockable,
        String socket, int threads, int cores) {
        super(name, manufacturer, value, tier, powerConsumption, memoryType, coreClock, overclockable);
        this.socket = socket;
        this.threads = threads;
        this.cores = cores;
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