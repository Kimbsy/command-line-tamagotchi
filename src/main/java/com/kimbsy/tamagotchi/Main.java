package com.kimbsy.tamagotchi;

/**
 * Application entry point.
 *
 * @author kimbsy
 */
public class Main {

    public static void main(String[] args) {
        TamagotchiApp tamagotchiApp = TamagotchiApp.getInstance();
        tamagotchiApp.init();
        tamagotchiApp.run();
    }
}
