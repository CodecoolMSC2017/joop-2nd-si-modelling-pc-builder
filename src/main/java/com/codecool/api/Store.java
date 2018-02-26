package com.codecool.api;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.codecool.api.components.*;
import com.codecool.api.enums.Size;
import com.codecool.api.enums.Tier;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Store extends Inventory {

    private static Element components = new XmlLoader().getComponentsElement();

    public Store() {
        super(readCases(),
                readPsus(),
                readMotherboards(),
                readCpus(),
                readHeatsinks(),
                readFans(),
                readMemorys(),
                readGpus(),
                readSsds(),
                readHdds());
    }

    private static List<Case> readCases() {
        Element casesElement = (Element) components.getElementsByTagName("Cases").item(0);
        NodeList cases = casesElement.getElementsByTagName("Case");
        List<Case> result = new ArrayList<>();
        Element current;
        for (int i = 0; i < cases.getLength(); i++) {
            current = (Element) cases.item(i);
            result.add(new Case(current.getAttribute("name"),
                    current.getAttribute("manufacturer"),
                    Integer.parseInt(current.getAttribute("value")),
                    Tier.valueOf(current.getAttribute("tier")),
                    Size.valueOf(current.getAttribute("size")),
                    Integer.parseInt(current.getAttribute("ssdCapacity")),
                    Integer.parseInt(current.getAttribute("hddCapacity")),
                    Integer.parseInt(current.getAttribute("frontFanCapacity")),
                    Integer.parseInt(current.getAttribute("rearFanCapacity"))));
        }
        return result;
    }

    private static List<PowerSupply> readPsus() {
        Element psusElement = (Element) components.getElementsByTagName("PowerSupplies").item(0);
        NodeList psus = psusElement.getElementsByTagName("PowerSupply");
        List<PowerSupply> result = new ArrayList<>();
        Element current;
        for (int i = 0; i < psus.getLength(); i++) {
            current = (Element) psus.item(i);
            result.add(new PowerSupply(current.getAttribute("name"),
                    current.getAttribute("manufacturer"),
                    Integer.parseInt(current.getAttribute("value")),
                    Tier.valueOf(current.getAttribute("tier")),
                    Integer.parseInt(current.getAttribute("powerConsumption")),
                    Integer.parseInt(current.getAttribute("performance"))));
        }
        return result;
    }

    private static List<Motherboard> readMotherboards() {
        Element motherboardsElement = (Element) components.getElementsByTagName("Motherboards").item(0);
        NodeList motherboards = motherboardsElement.getElementsByTagName("Motherboard");
        List<Motherboard> result = new ArrayList<>();
        Element current;
        for (int i = 0; i < motherboards.getLength(); i++) {
            current = (Element) motherboards.item(i);
            result.add(new Motherboard(current.getAttribute("name"),
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
                    Integer.parseInt(current.getAttribute("amountOfSata"))));
        }
        return result;
    }

    private static List<CPU> readCpus() {
        Element cpusElement = (Element) components.getElementsByTagName("Processors").item(0);
        NodeList cpus = cpusElement.getElementsByTagName("Processor");
        List<CPU> result = new ArrayList<>();
        Element current;
        for (int i = 0; i < cpus.getLength(); i++) {
            current = (Element) cpus.item(i);
            result.add(new CPU(current.getAttribute("name"),
                    current.getAttribute("manufacturer"),
                    Integer.parseInt(current.getAttribute("value")),
                    Tier.valueOf(current.getAttribute("tier")),
                    Integer.parseInt(current.getAttribute("powerConsumption")),
                    current.getAttribute("memoryType"),
                    Integer.parseInt(current.getAttribute("coreClock")),
                    Boolean.parseBoolean(current.getAttribute("overclockable")),
                    current.getAttribute("socket"),
                    Integer.parseInt(current.getAttribute("threads")),
                    Integer.parseInt(current.getAttribute("cores"))));
        }
        return result;
    }

    private static List<Heatsink> readHeatsinks() {
        Element heatsinksElement = (Element) components.getElementsByTagName("Heatsinks").item(0);
        NodeList heatsinks = heatsinksElement.getElementsByTagName("Heatsink");
        List<Heatsink> result = new ArrayList<>();
        Element current;
        for (int i = 0; i < heatsinks.getLength(); i++) {
            current = (Element) heatsinks.item(i);
            result.add(new Heatsink(current.getAttribute("name"),
                    current.getAttribute("manufacturer"),
                    Integer.parseInt(current.getAttribute("value")),
                    Tier.valueOf(current.getAttribute("tier")),
                    Size.valueOf(current.getAttribute("size"))));
        }
        return result;
    }

    private static List<Fan> readFans() {
        Element fansElement = (Element) components.getElementsByTagName("Fans").item(0);
        NodeList fans = fansElement.getElementsByTagName("Fan");
        List<Fan> result = new ArrayList<>();
        Element current;
        for (int i = 0; i < fans.getLength(); i++) {
            current = (Element) fans.item(i);
            result.add(new Fan(current.getAttribute("name"),
                    current.getAttribute("manufacturer"),
                    Integer.parseInt(current.getAttribute("value")),
                    Tier.valueOf(current.getAttribute("tier")),
                    Integer.parseInt(current.getAttribute("powerConsumption")),
                    Integer.parseInt(current.getAttribute("rpm")),
                    Integer.parseInt(current.getAttribute("airflow"))));
        }
        return result;
    }

    private static List<Memory> readMemorys() {
        Element memoriesElement = (Element) components.getElementsByTagName("Memories").item(0);
        NodeList memories = memoriesElement.getElementsByTagName("Memory");
        List<Memory> result = new ArrayList<>();
        Element current;
        for (int i = 0; i < memories.getLength(); i++) {
            current = (Element) memories.item(i);
            result.add(new Memory(current.getAttribute("name"),
                    current.getAttribute("manufacturer"),
                    Integer.parseInt(current.getAttribute("value")),
                    Tier.valueOf(current.getAttribute("tier")),
                    Integer.parseInt(current.getAttribute("powerConsumption")),
                    Integer.parseInt(current.getAttribute("speed")),
                    Integer.parseInt(current.getAttribute("capacity")),
                    current.getAttribute("type"),
                    Integer.parseInt(current.getAttribute("amountOfSticks"))));
        }
        return result;
    }

    private static List<GraphicsCard> readGpus() {
        Element gpusElement = (Element) components.getElementsByTagName("GraphicsCards").item(0);
        NodeList gpus = gpusElement.getElementsByTagName("GraphicsCard");
        List<GraphicsCard> result = new ArrayList<>();
        Element current;
        for (int i = 0; i < gpus.getLength(); i++) {
            current = (Element) gpus.item(i);
            result.add(new GraphicsCard(current.getAttribute("name"),
                    current.getAttribute("manufacturer"),
                    Integer.parseInt(current.getAttribute("value")),
                    Tier.valueOf(current.getAttribute("tier")),
                    Integer.parseInt(current.getAttribute("powerConsumption")),
                    current.getAttribute("memoryType"),
                    Integer.parseInt(current.getAttribute("coreClock")),
                    Boolean.parseBoolean(current.getAttribute("overclockable")),
                    Integer.parseInt(current.getAttribute("vram")),
                    Size.valueOf(current.getAttribute("size"))));
        }
        return result;
    }

    private static List<SolidStateDrive> readSsds() {
        Element ssdsElement = (Element) components.getElementsByTagName("SolidStateDrives").item(0);
        NodeList ssds = ssdsElement.getElementsByTagName("SolidStateDrive");
        List<SolidStateDrive> result = new ArrayList<>();
        Element current;
        for (int i = 0; i < ssds.getLength(); i++) {
            current = (Element) ssds.item(i);
            result.add(new SolidStateDrive(current.getAttribute("name"),
                    current.getAttribute("manufacturer"),
                    Integer.parseInt(current.getAttribute("value")),
                    Tier.valueOf(current.getAttribute("tier")),
                    Integer.parseInt(current.getAttribute("powerConsumption")),
                    Integer.parseInt(current.getAttribute("capacity")),
                    Integer.parseInt(current.getAttribute("transferSpeed"))));
        }
        return result;
    }

    private static List<HardDiskDrive> readHdds() {
        Element hddsElement = (Element) components.getElementsByTagName("HardDiskDrives").item(0);
        NodeList hdds = hddsElement.getElementsByTagName("HardDiskDrive");
        List<HardDiskDrive> result = new ArrayList<>();
        Element current;
        for (int i = 0; i < hdds.getLength(); i++) {
            current = (Element) hdds.item(i);
            result.add(new HardDiskDrive(current.getAttribute("name"),
                    current.getAttribute("manufacturer"),
                    Integer.parseInt(current.getAttribute("value")),
                    Tier.valueOf(current.getAttribute("tier")),
                    Integer.parseInt(current.getAttribute("powerConsumption")),
                    Integer.parseInt(current.getAttribute("capacity")),
                    Integer.parseInt(current.getAttribute("transferSpeed")),
                    Integer.parseInt(current.getAttribute("rpm"))));
        }
        return result;
    }

}

class XmlLoader {

    Element getComponentsElement() {
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return (Element) docBuilder.parse(this.getClass().getResource("/components.xml").toExternalForm()).getElementsByTagName("Components").item(0);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}