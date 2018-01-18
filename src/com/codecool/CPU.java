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

    public String details() {
        return "\n\033[1m   Name: " + this.getName() + "\n" +
               "   Manufacturer: " + this.getManufacturer() + "\n" +
               "   Value: $" + this.getValue() + "\n" +
               "   Tier: " + this.getTier() + "\n" +
               "   Power consumption: " + this.getPowerConsumption() + "W\n" +
               "   Supported memory type: " + this.getMemoryType() + "\n" +
               "   Core clock: " + this.getCoreClock() + "Mhz\n" +
               "   Overclockable: " + this.getOverclockable() + "\n" +
               "   Socket: " + this.getSocket() + "\n" +
               "   Amount of threads: " + this.getThreads() + "\n" +
               "   Amount of cores: " + this.getCores();
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