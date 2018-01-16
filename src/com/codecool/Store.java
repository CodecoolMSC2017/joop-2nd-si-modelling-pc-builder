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
        
    }

    private static PowerSupply[] readPsuFile() {

    }

    private static Motherboard[] readMotherboardFile() {

    }

    private static CPU[] readCpuFile() {

    }

    private static Heatsink[] readHeatsinkFile() {

    }

    private static Fan[] readFanFile() {

    }

    private static Memory[] readMemoryFile() {

    }

    private static GraphicsCard[] readGpuFile() {

    }

    private static SolidStateDrive[] readSsdFile() {

    }

    private static HardDiskDrive[] readHddFile() {

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