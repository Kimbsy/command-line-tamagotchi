package com.kimbsy.tamagotchi.domain;

import com.kimbsy.tamagotchi.TamagotchiApp;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Waste generated by the pet.
 *
 * @author kimbsy
 */
public class WasteItem {

    private Timer timer;

    public WasteItem() {
        this.timer = new Timer();

        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                TamagotchiApp tamagotchiApp = TamagotchiApp.getInstance();
                Pet pet = tamagotchiApp.getPet();
                pet.infect();
            }
        }, 100000, 100000);
    }

    public void cleanUp() {
        this.timer.cancel();
    }
}
