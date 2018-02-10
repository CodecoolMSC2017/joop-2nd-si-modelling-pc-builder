package com.codecool;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Store extends Inventory {

    private static Element components = getComponentsFromFile();

    public Store() {
        super(readCases(components), readPsus(components), readMotherboards(components),
            readCpus(components), readHeatsinks(components), readFans(components),
            readMemorys(components), readGpus(components), readSsds(components), readHdds(components));
    }

    public void handlePurchase(UserInventory inventory, String cathegory) throws ArrayIndexOutOfBoundsException {
        System.out.print("\nSelect an item by it's number: ");
        String input = getUserInput().nextLine().toLowerCase();
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

    private static Element getComponentsFromFile() {
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
            return (Element) docBuilder.parse("../store_items/components.xml").getElementsByTagName("Components").item(0);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Case[] readCases(Element components) {
        Element casesElement = (Element) components.getElementsByTagName("Cases").item(0);
        NodeList cases = casesElement.getElementsByTagName("Case");
        Case[] result = new Case[cases.getLength()];
        Element current;
        for (int i = 0; i < cases.getLength(); i++) {
            current = (Element) cases.item(i);
            result[i] = new Case(current.getAttribute("name"),
                                 current.getAttribute("manufacturer"),
                                 Integer.parseInt(current.getAttribute("value")),
                                 Tier.valueOf(current.getAttribute("tier")),
                                 Size.valueOf(current.getAttribute("size")),
                                 Integer.parseInt(current.getAttribute("ssdCapacity")),
                                 Integer.parseInt(current.getAttribute("hddCapacity")),
                                 Integer.parseInt(current.getAttribute("frontFanCapacity")),
                                 Integer.parseInt(current.getAttribute("rearFanCapacity")));
        }
        return result;
    }

    private static PowerSupply[] readPsus(Element components) {
        Element psusElement = (Element) components.getElementsByTagName("PowerSupplies").item(0);
        NodeList psus = psusElement.getElementsByTagName("PowerSupply");
        PowerSupply[] result = new PowerSupply[psus.getLength()];
        Element current;
        for (int i = 0; i < psus.getLength(); i++) {
            current = (Element) psus.item(i);
            result[i] = new PowerSupply(current.getAttribute("name"),
                                        current.getAttribute("manufacturer"),
                                        Integer.parseInt(current.getAttribute("value")),
                                        Tier.valueOf(current.getAttribute("tier")),
                                        Integer.parseInt(current.getAttribute("powerConsumption")),
                                        Integer.parseInt(current.getAttribute("performance")));
        }
        return result;
    }

    private static Motherboard[] readMotherboards(Element components) {
        Element motherboardsElement = (Element) components.getElementsByTagName("Motherboards").item(0);
        NodeList motherboards = motherboardsElement.getElementsByTagName("Motherboard");
        Motherboard[] result = new Motherboard[motherboards.getLength()];
        Element current;
        for (int i = 0; i < motherboards.getLength(); i++) {
            current = (Element) motherboards.item(i);
            result[i] = new Motherboard(current.getAttribute("name"),
                                        current.getAttribute("manufacturer"),
                                        Integer.parseInt(current.getAttribute("value")),
                                        Tier.valueOf(current.getAttribute("tier")),
                                        Integer.parseInt(current.getAttribute("powerConsumption")),
                                        Size.valueOf(current.getAttribute("size")),
                                        current.getAttribute("socket"),
                                        Integer.parseInt(current.getAttribute("amountOfSockets")),
                                        current.getAttribute("memoryType"),
                                        Integer.parseInt(current.getAttribute("amountOfMemorySlots")),
                                        Integer.parseInt(current.getAttribute("amountOfPCIESlots")),
                                        Integer.parseInt(current.getAttribute("amountOfSata")));
        }
        return result;
    }

    private static CPU[] readCpus(Element components) {
        Element cpusElement = (Element) components.getElementsByTagName("Processors").item(0);
        NodeList cpus = cpusElement.getElementsByTagName("Processor");
        CPU[] result = new CPU[cpus.getLength()];
        Element current;
        for (int i = 0; i < cpus.getLength(); i++) {
            current = (Element) cpus.item(i);
            result[i] = new CPU(current.getAttribute("name"),
                                current.getAttribute("manufacturer"),
                                Integer.parseInt(current.getAttribute("value")),
                                Tier.valueOf(current.getAttribute("tier")),
                                Integer.parseInt(current.getAttribute("powerConsumption")),
                                current.getAttribute("memoryType"),
                                Integer.parseInt(current.getAttribute("coreClock")),
                                Boolean.parseBoolean(current.getAttribute("overclockable")),
                                current.getAttribute("socket"),
                                Integer.parseInt(current.getAttribute("threads")),
                                Integer.parseInt(current.getAttribute("cores")));
        }
        return result;
    }

    private static Heatsink[] readHeatsinks(Element components) {
        Element heatsinksElement = (Element) components.getElementsByTagName("Heatsinks").item(0);
        NodeList heatsinks = heatsinksElement.getElementsByTagName("Heatsink");
        Heatsink[] result = new Heatsink[heatsinks.getLength()];
        Element current;
        for (int i = 0; i < heatsinks.getLength(); i++) {
            current = (Element) heatsinks.item(i);
            result[i] = new Heatsink(current.getAttribute("name"),
                                     current.getAttribute("manufacturer"),
                                     Integer.parseInt(current.getAttribute("value")),
                                     Tier.valueOf(current.getAttribute("tier")),
                                     Size.valueOf(current.getAttribute("size")));
        }
        return result;
    }

    private static Fan[] readFans(Element components) {
        Element fansElement = (Element) components.getElementsByTagName("Fans").item(0);
        NodeList fans = fansElement.getElementsByTagName("Fan");
        Fan[] result = new Fan[fans.getLength()];
        Element current;
        for (int i = 0; i < fans.getLength(); i++) {
            current = (Element) fans.item(i);
            result[i] = new Fan(current.getAttribute("name"),
                                current.getAttribute("manufacturer"),
                                Integer.parseInt(current.getAttribute("value")),
                                Tier.valueOf(current.getAttribute("tier")),
                                Integer.parseInt(current.getAttribute("powerConsumption")),
                                Integer.parseInt(current.getAttribute("rpm")),
                                Integer.parseInt(current.getAttribute("airflow")));
        }
        return result;
    }

    private static Memory[] readMemorys(Element components) {
        Element memoriesElement = (Element) components.getElementsByTagName("Memories").item(0);
        NodeList memories = memoriesElement.getElementsByTagName("Memory");
        Memory[] result = new Memory[memories.getLength()];
        Element current;
        for (int i = 0; i < memories.getLength(); i++) {
            current = (Element) memories.item(i);
            result[i] = new Memory(current.getAttribute("name"),
                                   current.getAttribute("manufacturer"),
                                   Integer.parseInt(current.getAttribute("value")),
                                   Tier.valueOf(current.getAttribute("tier")),
                                   Integer.parseInt(current.getAttribute("powerConsumption")),
                                   Integer.parseInt(current.getAttribute("speed")),
                                   Integer.parseInt(current.getAttribute("capacity")),
                                   current.getAttribute("type"),
                                   Integer.parseInt(current.getAttribute("amountOfSticks")));
        }
        return result;
    }

    private static GraphicsCard[] readGpus(Element components) {
        Element gpusElement = (Element) components.getElementsByTagName("GraphicsCards").item(0);
        NodeList gpus = gpusElement.getElementsByTagName("GraphicsCard");
        GraphicsCard[] result = new GraphicsCard[gpus.getLength()];
        Element current;
        for (int i = 0; i < gpus.getLength(); i++) {
            current = (Element) gpus.item(i);
            result[i] = new GraphicsCard(current.getAttribute("name"),
                                         current.getAttribute("manufacturer"),
                                         Integer.parseInt(current.getAttribute("value")),
                                         Tier.valueOf(current.getAttribute("tier")),
                                         Integer.parseInt(current.getAttribute("powerConsumption")),
                                         current.getAttribute("memoryType"),
                                         Integer.parseInt(current.getAttribute("coreClock")),
                                         Boolean.parseBoolean(current.getAttribute("overclockable")),
                                         Integer.parseInt(current.getAttribute("vram")),
                                         Size.valueOf(current.getAttribute("size")));
        }
        return result;
    }

    private static SolidStateDrive[] readSsds(Element components) {
        Element ssdsElement = (Element) components.getElementsByTagName("SolidStateDrives").item(0);
        NodeList ssds = ssdsElement.getElementsByTagName("SolidStateDrive");
        SolidStateDrive[] result = new SolidStateDrive[ssds.getLength()];
        Element current;
        for (int i = 0; i < ssds.getLength(); i++) {
            current = (Element) ssds.item(i);
            result[i] = new SolidStateDrive(current.getAttribute("name"),
                                            current.getAttribute("manufacturer"),
                                            Integer.parseInt(current.getAttribute("value")),
                                            Tier.valueOf(current.getAttribute("tier")),
                                            Integer.parseInt(current.getAttribute("powerConsumption")),
                                            Integer.parseInt(current.getAttribute("capacity")),
                                            Integer.parseInt(current.getAttribute("transferSpeed")));
        }
        return result;
    }

    private static HardDiskDrive[] readHdds(Element components) {
        Element hddsElement = (Element) components.getElementsByTagName("HardDiskDrives").item(0);
        NodeList hdds = hddsElement.getElementsByTagName("HardDiskDrive");
        HardDiskDrive[] result = new HardDiskDrive[hdds.getLength()];
        Element current;
        for (int i = 0; i < hdds.getLength(); i++) {
            current = (Element) hdds.item(i);
            result[i] = new HardDiskDrive(current.getAttribute("name"),
                                            current.getAttribute("manufacturer"),
                                            Integer.parseInt(current.getAttribute("value")),
                                            Tier.valueOf(current.getAttribute("tier")),
                                            Integer.parseInt(current.getAttribute("powerConsumption")),
                                            Integer.parseInt(current.getAttribute("capacity")),
                                            Integer.parseInt(current.getAttribute("transferSpeed")),
                                            Integer.parseInt(current.getAttribute("rpm")));
        }
        return result;
    }

}