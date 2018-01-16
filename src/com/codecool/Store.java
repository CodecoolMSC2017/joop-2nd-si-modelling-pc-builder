package com.codecool;

import java.io.*;
import java.util.Scanner;

public class Store {

    private Case[] cases;
    private PowerSupply[] psus;
    private Motherboard[] motherboards;
    private CPU[] cpus;
    private Heatsink[] heatsinks;
    private Fan[] fans;
    private Memory[] memories;
    private GraphicsCard[] gpus;
    private SolidStateDrive[] ssds;
    private HardDiskDrive[] hdds;

    public Store() {
        this.cases = readCaseFile();
        this.psus = readPsuFile();
        this.motherboards = readMotherboardFile();
        this.cpus = readCpuFile();
        this.heatsinks = readHeatsinkFile();
        this.fans = readFanFile();
        this.memories = readMemoryFile();
        this.gpus = readGpuFile();
        this.ssds = readSsdFile();
        this.hdds = readHddFile();
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
                Integer.parseInt(parts[4], Size.valueOf(parts[5], parts[6], Integer.parseInt(parts[7], parts[8],
                Integer.parseInt(parts[9], Integer.parseInt(parts[10], Integer.parseInt(parts[11]);
            counter++;
        }
        return result;
    }

    private static CPU[] readCpuFile() {
        return new CPU[1];
    }

    private static Heatsink[] readHeatsinkFile() {
        return new Heatsink[1];
    }

    private static Fan[] readFanFile() {
        return new Fan[1];
    }

    private static Memory[] readMemoryFile() {
        return new Memory[1];
    }

    private static GraphicsCard[] readGpuFile() {
        return new GraphicsCard[1];
    }

    private static SolidStateDrive[] readSsdFile() {
        return new SolidStateDrive[1];
    }

    private static HardDiskDrive[] readHddFile() {
        return new HardDiskDrive[1];
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