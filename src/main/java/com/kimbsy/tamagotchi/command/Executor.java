package com.kimbsy.tamagotchi.command;

import com.kimbsy.tamagotchi.TamagotchiApp;
import com.kimbsy.tamagotchi.domain.Game;
import com.kimbsy.tamagotchi.domain.WasteItem;

import java.util.ArrayList;

/**
 * This class is responsible for executing user commands.
 *
 * @author kimbsy
 */
public class Executor {

    /**
     * Execute a command.
     *
     * @param command The command the user wants to execute.
     */
    public void execute(Parser.Command command) {
        TamagotchiApp tamagotchiApp = TamagotchiApp.getInstance();
        switch (command) {
            case FEED:
                if (ensureAwake() && ensureHappy()) {
                    tamagotchiApp.getPet().feed();
                }
                break;
            case CLEAN:
                cleanWaste();
                break;
            case PLAY:
                if (ensureAwake() && ensureHealthy()) {
                    Game.playGame(tamagotchiApp.getScanner());
                    tamagotchiApp.getPet().increaseHappiness();
                }
                break;
            case CURE:
                tamagotchiApp.getPet().cure();
                break;
            case SLEEP:
                if (ensureAwake() && ensureNotHungry()) {
                    tamagotchiApp.getPet().sleep();
                }
                break;
            case WAKE:
                tamagotchiApp.getPet().wake();
                break;
            case HELP:
                tamagotchiApp.getParser().displayCommandsWithHelp();
                break;
            case QUIT:
                tamagotchiApp.quit();
                break;
            default:
                System.out.println(String.format("Command `%s` not implemented yet", command.getDisplayText()));
        }
    }

    private boolean ensureHealthy() {
        TamagotchiApp tamagotchiApp = TamagotchiApp.getInstance();
        boolean healthy = !tamagotchiApp.getPet().isSick();
        if (!healthy) {
            System.out.println(String.format("%s is sick.\n", tamagotchiApp.getPet().getName()));
        }
        return healthy;
    }

    private boolean ensureAwake() {
        TamagotchiApp tamagotchiApp = TamagotchiApp.getInstance();
        boolean sleeping = tamagotchiApp.getPet().isSleeping();
        if (sleeping) {
            System.out.println(String.format("%s is sleeping.\n", tamagotchiApp.getPet().getName()));
        }
        return !sleeping;
    }

    private boolean ensureHappy() {
        TamagotchiApp tamagotchiApp = TamagotchiApp.getInstance();
        boolean happy = tamagotchiApp.getPet().getHappiness() > 50;
        if (!happy) {
            System.out.println(String.format("%s is unhappy.\n", tamagotchiApp.getPet().getName()));
        }
        return happy;
    }

    private boolean ensureNotHungry() {
        TamagotchiApp tamagotchiApp = TamagotchiApp.getInstance();
        boolean hungry = tamagotchiApp.getPet().getHunger() > 50;
        if (hungry) {
            System.out.println(String.format("%s is hungry.\n", tamagotchiApp.getPet().getName()));
        }
        return !hungry;
    }

    /**
     * Cancel the timers in each WasteItem, then empty the list.
     */
    private void cleanWaste() {
        TamagotchiApp tamagotchiApp = TamagotchiApp.getInstance();
        for (WasteItem wasteItem : tamagotchiApp.getWasteItems()) {
            wasteItem.cleanUp();
        }
        tamagotchiApp.setWasteItems(new ArrayList<WasteItem>());
    }
}
