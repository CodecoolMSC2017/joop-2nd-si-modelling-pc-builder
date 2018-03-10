package com.codecool.api;

public class Store extends Inventory {

    private static XmlLoader xmlLoader = new XmlLoader();

    public Store() {
        super(
                xmlLoader.getCases(),
                xmlLoader.getPsus(),
                xmlLoader.getMotherboards(),
                xmlLoader.getCpus(),
                xmlLoader.getHeatsinks(),
                xmlLoader.getFans(),
                xmlLoader.getMemories(),
                xmlLoader.getGpus(),
                xmlLoader.getSsds(),
                xmlLoader.getHdds());
    }

}