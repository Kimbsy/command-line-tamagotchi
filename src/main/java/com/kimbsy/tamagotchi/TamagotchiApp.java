package com.kimbsy.tamagotchi;

import com.kimbsy.tamagotchi.command.Executor;
import com.kimbsy.tamagotchi.command.Parser;
import com.kimbsy.tamagotchi.domain.Pet;
import com.kimbsy.tamagotchi.domain.WasteItem;

import java.util.*;

/**
 * Main application class.
 *
 * @author kimbsy
 */
public class TamagotchiApp implements Runnable {

    private Pet pet;
    private List<WasteItem> wasteItems;
    private Scanner scanner;
    private Parser parser;
    private Executor executor;
    private boolean running;
    private Timer updateTimer;

    private static final TamagotchiApp instance = new TamagotchiApp();
    public static TamagotchiApp getInstance() {
        return instance;
    }

    private TamagotchiApp() {
        this.scanner = new Scanner(System.in);
        this.parser = new Parser(scanner);
        this.executor = new Executor();
        this.pet = new Pet();
        this.wasteItems = new ArrayList<WasteItem>();
        this.running = true;
        this.updateTimer = new Timer();
    }

    /**
     * Initialize the application state.
     */
    public void init() {
        System.out.println("Welcome to CommandLineTamagotchi!\n");
        this.pet.setName(promptPetName());
        System.out.println(String.format("\nEnter commands to interact with %s.", this.pet.getName()));
        this.parser.displayCommands();

        setUpTimingLoop();
    }

    private void setUpTimingLoop() {
        this.updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                pet.update();
                running = !pet.shouldDie();
            }
        }, 1000, 1000);
    }

    /**
     * Run the application.
     */
    public void run() {
        while (running) {
            Parser.Command c = parser.parse();
            if (c != null) {
                executor.execute(c);
            }
        }

        System.out.println("Game Over!");
        quit();
    }

    /**
     * Ask the user for the name of their pet.
     *
     * @param isRetry Whether this invocation is a retry.
     * @return The name of the pet.
     */
    public String promptPetName(boolean isRetry) {
        if (isRetry) {
            System.out.println("Please enter an actual name:");
        } else {
            System.out.println("Please enter the name of your pet:");
        }

        String name = scanner.nextLine();
        if (name.isEmpty()) {
            name = promptPetName(true);
        }

        return name;
    }

    /**
     * Overloaded implementation with isRetry defaulting to false.
     *
     * @return The name of the pet.
     */
    public String promptPetName() {
        return promptPetName(false);
    }

    public void addWaste() {
        System.out.println("Waste created!");
        this.getWasteItems().add(new WasteItem());
    }

    /**
     * Exit the application.
     */
    public void quit() {
        System.out.println(String.format("Thanks for playing!\n%s lived to be %s!", pet.getName(), pet.getAge()));
        System.exit(0);
    }

    public Pet getPet() {
        return pet;
    }

    public List<WasteItem> getWasteItems() {
        return wasteItems;
    }

    public void setWasteItems(List<WasteItem> wasteItems) {
        this.wasteItems = wasteItems;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Parser getParser() {
        return parser;
    }
}
