package com.kimbsy.tamagotchi.command;

import java.util.Formatter;
import java.util.Scanner;

/**
 * This class is responsible for interpreting user commands.
 *
 * @author kimbsy
 */
public class Parser {

    private Scanner scanner;

    public Parser(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * The available commands.
     */
    public enum Command {
        FEED("feed", "Feed", "Feed your pet to stop it starving."),
        CLEAN("clean", "Clean", "Clean up your pet's waste to stop it getting sick."),
        PLAY("play", "Play", "Play with you pet to keep it entertained."),
        CURE("cure", "Cure", "Cure your pet if it is sick."),
        SLEEP("sleep", "Sleep", "Send your pet to sleep."),
        WAKE("wake", "Wake", "Wake up your pet."),
        HELP("help", "Help", "Display available commands with help text."),
        QUIT("quit", "Quit", "Exit the game.");

        private String machineName;
        private String displayText;
        private String helpText;

        Command(String machineName, String displayText, String helpText) {
            this.machineName = machineName;
            this.displayText = displayText;
            this.helpText = helpText;
        }

        public String getDisplayText() {
            return displayText;
        }

        public String getHelpText() {
            return helpText;
        }

        private static Command fromMachineName(String machineName) {
            for (Command c : Command.values()) {
                if (c.machineName.equals(machineName)) {
                    return c;
                }
            }
            return null;
        }
    }

    /**
     * Print the available commands.
     */
    public void displayCommands() {
        System.out.println("Available commands:");

        Formatter fmt = new Formatter();
        for (Command c : Command.values()) {
            fmt.format("\t%s\n", c.getDisplayText());
        }

        System.out.println(fmt);
    }

    /**
     * Print the available commands with their help text.
     */
    public void displayCommandsWithHelp() {
        System.out.println("Available commands:");

        Formatter fmt = new Formatter();
        for (Command c : Command.values()) {
            fmt.format("\t%-6s| %s\n", c.getDisplayText(), c.getHelpText());
        }

        System.out.println(fmt);
    }

    /**
     * Determine the command a user is attempting to invoke.
     *
     * @return The associated command.
     */
    public Command parse() {
        System.out.println("Enter a command:");
        String userCommand = scanner.nextLine();
        Command command = Command.fromMachineName(userCommand.toLowerCase());
        System.out.print("\n");

        if (command == null) {
            System.out.println(String.format("[ERROR]: Unknown command `%s`\n", userCommand));
        }

        return command;
    }
}
