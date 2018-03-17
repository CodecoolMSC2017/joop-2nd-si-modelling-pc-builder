package com.codecool.api;

import com.codecool.api.components.*;
import com.codecool.api.enums.Temperature;
import com.codecool.api.exceptions.ComponentsDoNotMatchException;
import com.codecool.api.exceptions.NoMoreRoomException;

import java.util.List;
import java.util.Objects;

public class Computer extends Inventory {

    private String name;
    private boolean functional = false;
    private boolean isTurnedOn = false;

    public Computer(String name) {
        this.name = name;
    }

    public void updateTemperature(boolean isUnderLoad) {
        if (isUnderLoad) {
            if (isTurnedOn) {
                for (int i = 0; i < this.getCpus().size(); i++) {
                    this.getCpus().get(i).setTemperature(Temperature.valueOf("UNDERLOAD"));
                }
                for (int i = 0; i < this.getGpus().size(); i++) {
                    this.getGpus().get(i).setTemperature(Temperature.valueOf("UNDERLOAD"));
                }
            }
        } else {
            if (isTurnedOn) {
                for (int i = 0; i < this.getCpus().size(); i++) {
                    this.getCpus().get(i).setTemperature(Temperature.valueOf("IDLE"));
                }
                for (int i = 0; i < this.getGpus().size(); i++) {
                    this.getGpus().get(i).setTemperature(Temperature.valueOf("IDLE"));
                }
            } else {
                for (int i = 0; i < this.getCpus().size(); i++) {
                    this.getCpus().get(i).setTemperature(Temperature.valueOf("AMBIENT"));
                }
                for (int i = 0; i < this.getGpus().size(); i++) {
                    this.getGpus().get(i).setTemperature(Temperature.valueOf("AMBIENT"));
                }
            }
        }
    }

    public int getTotalFanciness() {
        int fanciness = 0;
        for (PCComponent component : getAllComponents()) {
            fanciness += component.getTier().getFanciness();
        }
        return fanciness;
    }

    private int getAmountOfFreeMemorySlots() throws IndexOutOfBoundsException {
        int amountOfSticks = 0;
        for (Memory ram : getMemories()) {
            amountOfSticks += ram.getAmountOfSticks();
        }
        return getMotherboards().get(0).getAmountOfMemorySlots() - amountOfSticks;
    }

    private void checkIfFunctional() {
        this.functional = (getCases().size() > 0 &&
                getPsus().size() > 0 &&
                getMotherboards().size() > 0 &&
                getCpus().size() > 0 &&
                getHeatsinks().size() > 0 &&
                getFans().size() > 0 &&
                getMemories().size() > 0 &&
                getGpus().size() > 0 &&
                getSsds().size() + getHdds().size() > 0);
    }

    public int getPowerConsumption() {
        int powerConsumption = 0;
        Electronic elec;
        for (PCComponent component : getAllComponents()) {
            if (component instanceof Electronic) {
                elec = (Electronic) component;
                powerConsumption += elec.getPowerConsumption();
            }
        }
        return powerConsumption;
    }

    public void turnOn() {
        this.isTurnedOn = true;
    }

    public void turnOff() {
        this.isTurnedOn = false;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public boolean getFunctional() {
        return functional;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    public void addComponent(PCComponent component) throws NoMoreRoomException, ComponentsDoNotMatchException {
        if (component instanceof Case) {
            addComponent((Case) component);
        } else if (component instanceof PowerSupply) {
            addComponent((PowerSupply) component);
        } else if (component instanceof Motherboard) {
            addComponent((Motherboard) component);
        } else if (component instanceof CPU) {
            addComponent((CPU) component);
        } else if (component instanceof Heatsink) {
            addComponent((Heatsink) component);
        } else if (component instanceof Fan) {
            addComponent((Fan) component);
        } else if (component instanceof Memory) {
            addComponent((Memory) component);
        } else if (component instanceof GraphicsCard) {
            addComponent((GraphicsCard) component);
        } else if (component instanceof SolidStateDrive) {
            addComponent((SolidStateDrive) component);
        } else if (component instanceof HardDiskDrive) {
            addComponent((HardDiskDrive) component);
        }
        checkIfFunctional();
    }

    private void addComponent(Case item) throws NoMoreRoomException, ComponentsDoNotMatchException {
        if (getCases().size() > 0) {
            throw new NoMoreRoomException("A PC can have only 1 case at a time.");
        }
        if (getMotherboards().size() > 0) {
            if (getMotherboards().get(0).getSize().getValue() > item.getSize().getValue()) {
                throw new ComponentsDoNotMatchException("This case cannot fit the current motherboard.");
            }
        }
        if (getHeatsinks().size() > 0) {
            for (Heatsink heatsink : getHeatsinks()) {
                if (heatsink.getSize().getValue() > item.getSize().getValue()) {
                    throw new ComponentsDoNotMatchException("This case cannot fit the current heatsink.");
                }
            }
        }
        if (getGpus().size() > 0) {
            for (GraphicsCard gpu : getGpus()) {
                if (gpu.getSize().getValue() > item.getSize().getValue()) {
                    throw new ComponentsDoNotMatchException("This case cannot fit the current graphics card.");
                }
            }
        }
        addItem(item);
    }

    private void addComponent(PowerSupply item) throws NoMoreRoomException, ComponentsDoNotMatchException {
        if (getPsus().size() > 0) {
            throw new NoMoreRoomException("A PC can have only 1 power supply at a time.");
        }
        if (getPowerConsumption() > item.getPerformance()) {
            throw new ComponentsDoNotMatchException("This power supply is not enough to power this beast of a system.");
        }
        addItem(item);
    }

    private void addComponent(Motherboard item) throws NoMoreRoomException, ComponentsDoNotMatchException {
        if (getMotherboards().size() > 0) {
            throw new NoMoreRoomException("A PC can have only 1 motherboard at a time.");
        }
        if (getCases().size() > 0) {
            if (getCases().get(0).getSize().getValue() < item.getSize().getValue()) {
                throw new ComponentsDoNotMatchException("The current case cannot fit this motherboard.");
            }
        }
        if (getCpus().size() > 0) {
            for (CPU cpu : getCpus()) {
                if (!cpu.getSocket().equals(item.getSocket())) {
                    throw new ComponentsDoNotMatchException("The current processor's socket does not match the socket of this motherboard.");
                }
            }
        }
        if (getMemories().size() > 0) {
            for (Memory memory : getMemories()) {
                if (!memory.getType().equals(item.getMemoryType())) {
                    throw new ComponentsDoNotMatchException("The type of current memory does not match the memory type of this motherboard.");
                }
            }
        }
        if (getPsus().size() > 0) {
            if (getPsus().get(0).getPerformance() < getPowerConsumption() + item.getPowerConsumption()) {
                throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
            }
        }
        addItem(item);
    }

    private void addComponent(CPU item) throws NoMoreRoomException, ComponentsDoNotMatchException {
        try {
            if (getCpus().size() == getMotherboards().get(0).getAmountOfSockets()) {
                throw new NoMoreRoomException("The motherboard has a cpu in all of it's sockets.");
            }
        } catch (IndexOutOfBoundsException e) {
            if (getCpus().size() > 0) {
                throw new NoMoreRoomException("You can select only 1 cpu without a motherboard.");
            }
        }
        if (getMotherboards().size() > 0) {
            if (!getMotherboards().get(0).getSocket().equals(item.getSocket())) {
                throw new ComponentsDoNotMatchException("The socket of the motherboard does not match the socket of this processor.");
            }
            if (!getMotherboards().get(0).getMemoryType().equals(item.getMemoryType())) {
                throw new ComponentsDoNotMatchException("The memory type of the current mmotherboard does not match the memory type of this processor.");
            }
        }
        if (getMemories().size() > 0) {
            for (Memory ram : getMemories()) {
                if (!ram.getType().equals(item.getMemoryType())) {
                    throw new ComponentsDoNotMatchException("The type of current memory does not match the memory type of this processor.");
                }
            }
        }
        if (getPsus().size() > 0) {
            if (getPsus().get(0).getPerformance() < getPowerConsumption() + getPowerConsumption()) {
                throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
            }
        }
        addItem(item);
    }

    private void addComponent(Heatsink item) throws NoMoreRoomException, ComponentsDoNotMatchException {
        try {
            if (getHeatsinks().size() == getMotherboards().get(0).getAmountOfSockets()) {
                throw new NoMoreRoomException("The motherboard has a heatsink on all of it's sockets.");
            }
        } catch (IndexOutOfBoundsException e) {
            if (getHeatsinks().size() > 0) {
                throw new NoMoreRoomException("You can select only 1 heatsink without a motherboard.");
            }
        }
        if (getCases().size() > 0) {
            if (getCases().get(0).getSize().getValue() < item.getSize().getValue()) {
                throw new ComponentsDoNotMatchException("The current case cannot fit this heatsink.");
            }
        }
        addItem(item);
    }

    private void addComponent(Fan item) throws NoMoreRoomException, ComponentsDoNotMatchException {
        try {
            if (getFans().size() == getCases().get(0).getFrontFanCapacity() + getCases().get(0).getRearFanCapacity()) {
                throw new NoMoreRoomException("You cannot fit more fans on this case.");
            }
        } catch (IndexOutOfBoundsException e) {
            if (getFans().size() > 1) {
                throw new NoMoreRoomException("You can select only 2 fans without a case.");
            }
        }
        if (getPsus().size() > 0) {
            if (getPsus().get(0).getPerformance() < getPowerConsumption() + item.getPowerConsumption()) {
                throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
            }
        }
        addItem(item);
    }

    private void addComponent(Memory item) throws NoMoreRoomException, ComponentsDoNotMatchException {
        try {
            if (item.getAmountOfSticks() > getAmountOfFreeMemorySlots()) {
                throw new NoMoreRoomException("The motherboard does not have enough empty memory slots for this memory.");
            }
        } catch (IndexOutOfBoundsException e) {
            if (getMemories().size() > 0) {
                throw new NoMoreRoomException("You can select only 1 memory without a motherboard.");
            }
        }
        if (getMotherboards().size() > 0) {
            if (!getMotherboards().get(0).getMemoryType().equals(item.getType())) {
                throw new ComponentsDoNotMatchException("The memory type of the current motherboard does not match the type of this memory.");
            }
        }
        if (getCpus().size() > 0) {
            for (CPU aCpu : getCpus()) {
                if (!aCpu.getMemoryType().equals(item.getType())) {
                    throw new ComponentsDoNotMatchException("The memory type of the current processor does not match the type of this memory.");
                }
            }
        }
        if (getPsus().size() > 0) {
            if (getPsus().get(0).getPerformance() < getPowerConsumption() + item.getPowerConsumption()) {
                throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
            }
        }
        addItem(item);
    }

    private void addComponent(GraphicsCard item) throws NoMoreRoomException, ComponentsDoNotMatchException {
        try {
            if (getMotherboards().get(0).getAmountOfPCIESlots() == getGpus().size()) {
                throw new NoMoreRoomException("The motherboard has no more empty PCI-e slots.");
            }
        } catch (IndexOutOfBoundsException e) {
            if (getGpus().size() > 0) {
                throw new NoMoreRoomException("You can select only 1 graphics card without a motherboard.");
            }
        }
        if (getCases().size() > 0) {
            if (getCases().get(0).getSize().getValue() < item.getSize().getValue()) {
                throw new ComponentsDoNotMatchException("The current case cannot fit this graphics card.");
            }
        }
        if (getPsus().size() > 0) {
            if (getPsus().get(0).getPerformance() < getPowerConsumption() + item.getPowerConsumption()) {
                throw new ComponentsDoNotMatchException("The current power supply will not be enough to power the computer with this component.");
            }
        }
        addItem(item);
    }

    private void addComponent(SolidStateDrive item) throws NoMoreRoomException, ComponentsDoNotMatchException {
        try {
            if (getSsds().size() + getHdds().size() == getMotherboards().get(0).getAmountOfSata()) {
                throw new NoMoreRoomException("The motherboard has no more empty SATA3 slots.");
            }
        } catch (IndexOutOfBoundsException e) {
            if (getSsds().size() + getHdds().size() > 0) {
                throw new NoMoreRoomException("You can select only 1 storage device without a motherboard.");
            }
        }
        if (getCases().size() > 0) {
            if (getCases().get(0).getSSDCapacity() == getSsds().size()) {
                throw new NoMoreRoomException("The case has no more empty slots for ssds.");
            }
        }
        if (getPsus().size() > 0) {
            if (getPsus().get(0).getPerformance() < getPowerConsumption() + item.getPowerConsumption()) {
                throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
            }
        }
        addItem(item);
    }

    private void addComponent(HardDiskDrive item) throws NoMoreRoomException, ComponentsDoNotMatchException {
        try {
            if (getSsds().size() + getHdds().size() == getMotherboards().get(0).getAmountOfSata()) {
                throw new NoMoreRoomException("The motherboard has no more empty SATA3 slots.");
            }
        } catch (IndexOutOfBoundsException e) {
            if (getSsds().size() + getHdds().size() > 0) {
                throw new NoMoreRoomException("You can select only 1 storage device without a motherboard.");
            }
        }
        if (getCases().size() > 0) {
            if (getCases().get(0).getHDDCapacity() == getHdds().size()) {
                throw new NoMoreRoomException("The case has no more empty slots for hdds.");
            }
        }
        if (getPsus().size() > 0) {
            if (getPsus().get(0).getPerformance() < getPowerConsumption() + item.getPowerConsumption()) {
                throw new ComponentsDoNotMatchException("The current power supply will not enough to power the computer with this component.");
            }
        }
        addItem(item);
    }

    private <T extends ProcessingUnit> String listProcessingUnits(List<T> items) {
        StringBuilder sb = new StringBuilder();
        for (ProcessingUnit component : items) {
            sb.append("  ");
            sb.append(component.getManufacturer());
            sb.append(" ");
            sb.append(component.getName());
            sb.append(" (");
            sb.append(component.getTemperature().inDigits());
            sb.append(" C)\n");
        }
        return sb.toString();
    }

    public String getHomeInfo() {
        return name + "\n" +
                "\nTurned on: " + isTurnedOn + "\n" +
                "\nProcessor:\n" + listProcessingUnits(getCpus()) +
                "\nGraphics card:\n" + listProcessingUnits(getGpus());
    }

    private <T extends PCComponent> String getListAsString(List<T> items) {
        StringBuilder sb = new StringBuilder();
        if (items.size() == 0) {
            sb.append("  ");
            sb.append("None\n");
        } else {
            for (PCComponent component : items) {
                sb.append("  ");
                sb.append(component.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return name + "\n" +
                "Functional: " + functional + "\n" +
                "Case:\n" + getListAsString(getCases()) +
                "Power supply:\n" + getListAsString(getPsus()) +
                "Motherboard:\n" + getListAsString(getMotherboards()) +
                "Processor:\n" + getListAsString(getCpus()) +
                "Heatsink:\n" + getListAsString(getHeatsinks()) +
                "Fan:\n" + getListAsString(getFans()) +
                "Memory:\n" + getListAsString(getMemories()) +
                "Graphics card:\n" + getListAsString(getGpus()) +
                "Solid state drive:\n" + getListAsString(getSsds()) +
                "Hard disk drive:\n" + getListAsString(getHdds());
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
        Computer computer = (Computer) o;
        return Objects.equals(this.getName(), computer.getName());
    }

}