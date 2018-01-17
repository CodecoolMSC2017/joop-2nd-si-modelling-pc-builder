package com.codecool;

import java.io.*;
import java.util.Scanner;

public class Store extends Inventory {

    public Store() {
        super(readCaseFile(), readPsuFile(), readMotherboardFile(), readCpuFile(), readHeatsinkFile(), 
            readFanFile(), readMemoryFile(), readGpuFile(), readSsdFile(), readHddFile());
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
                Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), parts[6], Integer.parseInt(parts[7]));
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