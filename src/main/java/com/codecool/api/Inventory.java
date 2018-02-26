package com.codecool.api;

import com.codecool.api.components.*;
import com.codecool.api.exceptions.EmptyCathegoryException;
import com.codecool.api.exceptions.InvalidCathegoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Inventory implements java.io.Serializable {

    static final long serialVersionUID = 4301932672197899670L;

    private List<Case> cases;
    private List<PowerSupply> psus;
    private List<Motherboard> motherboards;
    private List<CPU> cpus;
    private List<Heatsink> heatsinks;
    private List<Fan> fans;
    private List<Memory> memories;
    private List<GraphicsCard> gpus;
    private List<SolidStateDrive> ssds;
    private List<HardDiskDrive> hdds;

    public Inventory(List<Case> cases,
                     List<PowerSupply> psus,
                     List<Motherboard> motherboards,
                     List<CPU> cpus,
                     List<Heatsink> heatsinks,
                     List<Fan> fans,
                     List<Memory> memories,
                     List<GraphicsCard> gpus,
                     List<SolidStateDrive> ssds,
                     List<HardDiskDrive> hdds) {
        this.cases = cases;
        this.psus = psus;
        this.motherboards = motherboards;
        this.cpus = cpus;
        this.heatsinks = heatsinks;
        this.fans = fans;
        this.memories = memories;
        this.gpus = gpus;
        this.ssds = ssds;
        this.hdds = hdds;
    }

    public Inventory() {
        this.cases = new ArrayList<>();
        this.psus = new ArrayList<>();
        this.motherboards = new ArrayList<>();
        this.cpus = new ArrayList<>();
        this.heatsinks = new ArrayList<>();
        this.fans = new ArrayList<>();
        this.memories = new ArrayList<>();
        this.gpus = new ArrayList<>();
        this.ssds = new ArrayList<>();
        this.hdds = new ArrayList<>();
    }

    public List<PCComponent> getAllComponents() {
        List<PCComponent> result = new ArrayList<>();
        result.addAll(cases);
        result.addAll(psus);
        result.addAll(motherboards);
        result.addAll(cpus);
        result.addAll(heatsinks);
        result.addAll(fans);
        result.addAll(memories);
        result.addAll(gpus);
        result.addAll(ssds);
        result.addAll(hdds);
        return result;
    }

    public PCComponent getComponentByName(String name) {
        List<PCComponent> allComponents = getAllComponents();
        for (PCComponent component : allComponents) {
            if (component.toString().equals(name)) {
                return component;
            }
        }
        return null;
    }

    public void addItem(PCComponent component) {
        if (component instanceof Case) {
            cases.add((Case) component);
        } else if (component instanceof PowerSupply) {
            psus.add((PowerSupply) component);
        } else if (component instanceof Motherboard) {
            motherboards.add((Motherboard) component);
        } else if (component instanceof CPU) {
            cpus.add((CPU) component);
        } else if (component instanceof Heatsink) {
            heatsinks.add((Heatsink) component);
        } else if (component instanceof Fan) {
            fans.add((Fan) component);
        } else if (component instanceof Memory) {
            memories.add((Memory) component);
        } else if (component instanceof GraphicsCard) {
            gpus.add((GraphicsCard) component);
        } else if (component instanceof SolidStateDrive) {
            ssds.add((SolidStateDrive) component);
        } else if (component instanceof HardDiskDrive) {
            hdds.add((HardDiskDrive) component);
        }
    }

    public void removeItem(PCComponent component) {
        if (component instanceof Case) {
            cases.remove(component);
        } else if (component instanceof PowerSupply) {
            psus.remove(component);
        } else if (component instanceof Motherboard) {
            motherboards.remove(component);
        } else if (component instanceof CPU) {
            cpus.remove(component);
        } else if (component instanceof Heatsink) {
            heatsinks.remove(component);
        } else if (component instanceof Fan) {
            fans.remove(component);
        } else if (component instanceof Memory) {
            memories.remove(component);
        } else if (component instanceof GraphicsCard) {
            gpus.remove(component);
        } else if (component instanceof SolidStateDrive) {
            ssds.remove(component);
        } else if (component instanceof HardDiskDrive) {
            hdds.remove(component);
        }
    }

    public List<Case> getCases() {
        return cases;
    }

    public List<PowerSupply> getPsus() {
        return psus;
    }

    public List<Motherboard> getMotherboards() {
        return motherboards;
    }

    public List<CPU> getCpus() {
        return cpus;
    }

    public List<Heatsink> getHeatsinks() {
        return heatsinks;
    }

    public List<Fan> getFans() {
        return fans;
    }

    public List<Memory> getMemories() {
        return memories;
    }

    public List<GraphicsCard> getGpus() {
        return gpus;
    }

    public List<SolidStateDrive> getSsds() {
        return ssds;
    }

    public List<HardDiskDrive> getHdds() {
        return hdds;
    }
}