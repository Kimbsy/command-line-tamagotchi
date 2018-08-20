package com.kimbsy.tamagotchi.domain;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This class implements a game of Rock, Paper, Scissors.
 */
public class Game {

    /**
     * Cheap and cheery way of playing RPS.
     *
     * @param scanner StdIn Scanner object.
     * @param isRetry whether this invocation is a retry.
     */
    public static void playGame(Scanner scanner, boolean isRetry) {
        System.out.println("Choose Rock, Paper or Scissors:");
        String choice = scanner.nextLine().toLowerCase();

        if (Arrays.asList("rock", "paper", "scissors").contains(choice)) {
            switch (new Random().nextInt(3)) {
                case 0:
                    System.out.println("You win!");
                    break;
                case 1:
                System.out.println("You Lose!");
                    break;
                case 2:
                System.out.println("It's a Draw!");
                    break;
            }
        } else {
            System.out.println(String.format("Unknown choice `%s`", choice));
            playGame(scanner, true);
        }
    }

    /**
     * Overloaded implementation with isRetry defaulting to false.
     */
    public static void playGame(Scanner scanner) {
        playGame(scanner, false);
    }
}
