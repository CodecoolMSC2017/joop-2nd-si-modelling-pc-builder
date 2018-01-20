package com.codecool;

import java.io.*;
import java.util.Scanner;

public class Store extends Inventory {

    public Store() {
        super(readCaseFile(), readPsuFile(), readMotherboardFile(), readCpuFile(), readHeatsinkFile(), 
            readFanFile(), readMemoryFile(), readGpuFile(), readSsdFile(), readHddFile());
    }

    public void handlePurchase(UserInventory inventory, String cathegory) throws ArrayIndexOutOfBoundsException {
        System.out.print("\nSelect an item by it's number: ");
        String input = userInput.nextLine().toLowerCase();
        int index = 0;
        try {
            index = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            System.out.println("\n\033[1m\033[91mIncorrect input!\033[0m");
            return;
        }
        try {
            switch(cathegory) {
                case "0":
                    Case aCase = this.getCases()[index];
                    if (inventory.getMoney() < aCase.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(aCase);
                    inventory.manageMoney(-aCase.getValue());
                    break;
                case "1":
                    PowerSupply psu = this.getPsus()[index];
                    if (inventory.getMoney() < psu.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(psu);
                    inventory.manageMoney(-psu.getValue());
                    break;
                case "2":
                    Motherboard motherboard = this.getMotherboards()[index];
                    if (inventory.getMoney() < motherboard.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(motherboard);
                    inventory.manageMoney(-motherboard.getValue());
                    break;
                case "3":
                    CPU cpu = this.getCpus()[index];
                    if (inventory.getMoney() < cpu.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(cpu);
                    inventory.manageMoney(-cpu.getValue());
                    break;
                case "4":
                    Heatsink heatsink = this.getHeatsinks()[index];
                    if (inventory.getMoney() < heatsink.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(heatsink);
                    inventory.manageMoney(-heatsink.getValue());
                    break;
                case "5":
                    Fan fan = this.getFans()[index];
                    if (inventory.getMoney() < fan.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(fan);
                    inventory.manageMoney(-fan.getValue());
                    break;
                case "6":
                    Memory memory = this.getMemories()[index];
                    if (inventory.getMoney() < memory.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(memory);
                    inventory.manageMoney(-memory.getValue());
                    break;
                case "7":
                    GraphicsCard gpu = this.getGpus()[index];
                    if (inventory.getMoney() < gpu.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(gpu);
                    inventory.manageMoney(-gpu.getValue());
                    break;
                case "8":
                    SolidStateDrive ssd = this.getSsds()[index];
                    if (inventory.getMoney() < ssd.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(ssd);
                    inventory.manageMoney(-ssd.getValue());
                    break;
                case "9":
                    HardDiskDrive hdd = this.getHdds()[index];
                    if (inventory.getMoney() < hdd.getValue()) {
                        throw new NotEnoughMoneyException();
                    }
                    inventory.addItem(hdd);
                    inventory.manageMoney(-hdd.getValue());
                    break;
            }
        } catch(NotEnoughMoneyException e) {
            System.out.println("\n\033[1m\033[91mYou don't have enough money to purchase this item!\033[0m");
            return;
        }
        System.out.println("\n\033[1m\033[92mItem purchased!\033[0m");
    }

    private static Case[] readCaseFile() {
        String[] fileContent = getFileContent("../store_items/cases.csv");
        Case[] result = new Case[fileContent.length];
        int counter = 0;

        for (String line : fileContent) {
            String[] parts = line.split(";");
            result[counter] = new Case(parts[0], parts[1], Integer.parseInt(parts[2]), Tier.valueOf(parts[3]),
                Size.valueOf(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]),
                Integer.parseInt(parts[7]), Integer.parseInt(parts[8]));
            counter++;
        }
        return result;
    }

    private static PowerSupply[] readPsuFile() {
        String[] fileContent = getFileContent("../store_items/psus.csv");
        PowerSupply[] result = new PowerSupply[fileContent.length];
        int counter = 0;

        for (String line : fileContent) {
            String[] parts = line.split(";");
            result[counter] = new PowerSupply(parts[0], parts[1], Integer.parseInt(parts[2]), Tier.valueOf(parts[3]),
            Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
            counter++;
        }
        return result;
    }

    private static Motherboard[] readMotherboardFile() {
        String[] fileContent = getFileContent("../store_items/motherboards.csv");
        Motherboard[] result = new Motherboard[fileContent.length];
        int counter = 0;

        for (String line : fileContent) {
            String[] parts = line.split(";");
            result[counter] = new Motherboard(parts[0], parts[1], Integer.parseInt(parts[2]), Tier.valueOf(parts[3]),
                Integer.parseInt(parts[4]), Size.valueOf(parts[5]), parts[6], Integer.parseInt(parts[7]), parts[8],
                Integer.parseInt(parts[9]), Integer.parseInt(parts[10]), Integer.parseInt(parts[11]));
            counter++;
        }
        return result;
    }

    private static CPU[] readCpuFile() {
        String[] fileContent = getFileContent("../store_items/cpus.csv");
        CPU[] result = new CPU[fileContent.length];
        int counter = 0;

        for (String line : fileContent) {
            String[] parts = line.split(";");
            result[counter] = new CPU(parts[0], parts[1], Integer.parseInt(parts[2]), Tier.valueOf(parts[3]),
                Integer.parseInt(parts[4]), parts[5], Integer.parseInt(parts[6]), Boolean.parseBoolean(parts[7]),
                parts[8], Integer.parseInt(parts[9]), Integer.parseInt(parts[10]));
            counter++;
        }
        return result;
    }

    private static Heatsink[] readHeatsinkFile() {
        String[] fileContent = getFileContent("../store_items/heatsinks.csv");
        Heatsink[] result = new Heatsink[fileContent.length];
        int counter = 0;

        for (String line : fileContent) {
            String[] parts = line.split(";");
            result[counter] = new Heatsink(parts[0], parts[1], Integer.parseInt(parts[2]),
                Tier.valueOf(parts[3]), Size.valueOf(parts[4]));
            counter++;
        }
        return result;
    }

    private static Fan[] readFanFile() {
        String[] fileContent = getFileContent("../store_items/fans.csv");
        Fan[] result = new Fan[fileContent.length];
        int counter = 0;

        for (String line : fileContent) {
            String[] parts = line.split(";");
            result[counter] = new Fan(parts[0], parts[1], Integer.parseInt(parts[2]), Tier.valueOf(parts[3]),
                Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
            counter++;
        }
        return result;
    }

    private static Memory[] readMemoryFile() {
        String[] fileContent = getFileContent("../store_items/rams.csv");
        Memory[] result = new Memory[fileContent.length];
        int counter = 0;

        for (String line : fileContent) {
            String[] parts = line.split(";");
            result[counter] = new Memory(parts[0], parts[1], Integer.parseInt(parts[2]), Tier.valueOf(parts[3]),
                Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]),
                parts[7], Integer.parseInt(parts[8]));
            counter++;
        }
        return result;
    }

    private static GraphicsCard[] readGpuFile() {
        String[] fileContent = getFileContent("../store_items/gpus.csv");
        GraphicsCard[] result = new GraphicsCard[fileContent.length];
        int counter = 0;

        for (String line : fileContent) {
            String[] parts = line.split(";");
            result[counter] = new GraphicsCard(parts[0], parts[1], Integer.parseInt(parts[2]), Tier.valueOf(parts[3]),
                Integer.parseInt(parts[4]), parts[5], Integer.parseInt(parts[6]), Boolean.parseBoolean(parts[7]),
                Integer.parseInt(parts[8]), Size.valueOf(parts[9]));
            counter++;
        }
        return result;
    }

    private static SolidStateDrive[] readSsdFile() {
        String[] fileContent = getFileContent("../store_items/ssds.csv");
        SolidStateDrive[] result = new SolidStateDrive[fileContent.length];
        int counter = 0;

        for (String line : fileContent) {
            String[] parts = line.split(";");
            result[counter] = new SolidStateDrive(parts[0], parts[1], Integer.parseInt(parts[2]), Tier.valueOf(parts[3]),
                Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
            counter++;
        }
        return result;
    }

    private static HardDiskDrive[] readHddFile() {
        String[] fileContent = getFileContent("../store_items/hdds.csv");
        HardDiskDrive[] result = new HardDiskDrive[fileContent.length];
        int counter = 0;

        for (String line : fileContent) {
            String[] parts = line.split(";");
            result[counter] = new HardDiskDrive(parts[0], parts[1], Integer.parseInt(parts[2]), Tier.valueOf(parts[3]),
                Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]),
                Integer.parseInt(parts[7]));
            counter++;
        }
        return result;
    }

    private static int getFileLength(File file) {
        Scanner sc;
        int fileLength = 0;

        try {
            sc = new Scanner(file);

            while (sc.hasNext()) {
                fileLength++;
                sc.nextLine();
            }
            sc.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileLength;
    }

    private static String[] getFileContent(String fileName) {
        int fileLength = getFileLength(new File(fileName));
        String[] content = new String[fileLength];
        Scanner read;

        try {
            read = new Scanner(new File(fileName));
            int counter = 0;

            while (read.hasNext()) {
                content[counter] = read.nextLine();
                counter++;
            }
            read.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

}