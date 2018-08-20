package com.kimbsy.tamagotchi.domain;

import com.kimbsy.tamagotchi.TamagotchiApp;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A tamagotchi pet to look after.
 *
 * @author kimbsy
 */
public class Pet {

    private String name;
    private int age;
    private boolean sleeping;
    private boolean sick;
    private long sickSince;
    private int hunger;
    private int happiness;
    private Timer wasteTimer;

    public Pet() {
        this.age = 0;
        this.sleeping = false;
        this.sick = false;
        this.sickSince = 0L;
        this.hunger = 0;
        this.happiness = 100;
        this.wasteTimer = new Timer();

        this.wasteTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                TamagotchiApp tamagotchiApp = TamagotchiApp.getInstance();
                tamagotchiApp.addWaste();
            }
        }, 50000, 50000);
    }

    public void update() {
        if (!this.sleeping) {
            this.age++;
            this.hunger++;
            this.happiness--;
        }
    }

    public void feed() {
        System.out.println("Om nom nom.\n");
        this.hunger -= 10;
    }

    public void cure() {
        System.out.println(String.format("%s was cured!\n", this.name));
        this.sick = false;
        this.sickSince = 0L;
    }

    public void infect() {
        System.out.println(String.format("%s is sick!\n", this.name));
        this.sick = true;
        this.sickSince = System.currentTimeMillis();
    }

    public void sleep() {
        System.out.println(String.format("%s has gone to sleep.\n", this.name));
        this.sleeping = true;
    }

    public void wake() {
        System.out.println(String.format("%s has woken up\n", this.name));
        this.sleeping = false;
    }

    public void increaseHappiness() {
        System.out.println(String.format("%s loves to play!\n", this.name));
        this.happiness += 10;
    }

    public boolean shouldDie() {
        return this.hunger > 100
                || (this.sickSince > 0
                    && (System.currentTimeMillis() - this.sickSince) > 100000);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public boolean isSick() {
        return sick;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }
}
