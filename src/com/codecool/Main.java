package com.codecool;

import java.util.*;

public class Main {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        Store store = new Store();
        System.out.println(Arrays.toString(store.getCases()));
        System.out.println(Arrays.toString(store.getPsus()));
        System.out.println(Arrays.toString(store.getMotherboards()));
        System.out.println(Arrays.toString(store.getCpus()));
        System.out.println(Arrays.toString(store.getHeatsinks()));
        System.out.println(Arrays.toString(store.getFans()));
        System.out.println(Arrays.toString(store.getMemories()));
        System.out.println(Arrays.toString(store.getGpus()));
        System.out.println(Arrays.toString(store.getSsds()));
        System.out.println(Arrays.toString(store.getHdds()));
    }

}