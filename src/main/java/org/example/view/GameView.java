package org.example.view;

import org.example.dto.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class GameView {

    public static void displayMainMenu() {
//        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to Bulls and Cows.");
        System.out.println("===========================\n");
        System.out.println("Pick an option:");
        System.out.println("1. Play a game");
        System.out.println("2. See all game statistics");
        System.out.println("3. Exit");
//        int selection = scanner.nextInt();

//        return selection;
    }

    public void displayPlayGameBanner() {
        System.out.println("\n===== PLAYING GAME =====\n");
    }

    public Integer[] getUserGuess() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Make your guess! Enter the first number: ");
        int a = scan.nextInt();
        System.out.println("Second number: ");
        int b = scan.nextInt();
        System.out.println("Third number: ");
        int c = scan.nextInt();
        System.out.println("Fourth number: ");
        int d = scan.nextInt();

        Integer[] answer = new Integer[]{a, b, c, d};
        return answer;

    }

    public void printOutResults(int exacts, int partials) {
        System.out.println("e:" + exacts + ":p:" + partials);
    }

    public void displayWinnerBanner() {

        System.out.println("\nCongratulations! You won the game!\n");
    }

    public void displayLoserBanner() {
        System.out.println("\nYou lost! Try again another time.\n");
    }

    public void displayAllGamesBanner() {
        System.out.println("\n===== DISPLAYING ALL GAME INFORMATION =====\n");
    }

    public void displayTableOfGames(List<Game> games) {
        for (Game game : games) {
            System.out.printf("%s: %s -- %s -- %s\n",
                    game.getId(),
                    game.getAnswer(),
                    game.getStatus(),
                    game.isWon());
        }
        System.out.println("");
    }
}
