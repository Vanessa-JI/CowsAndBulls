package org.example.view;

import org.example.dto.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class GameView {

    /**
     * This method displays a main menu to the user, showing all available options for inputs.
     */
    public static void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to Bulls and Cows.");
        System.out.println("===========================\n");
        System.out.println("Pick an option:");
        System.out.println("1. Play a game");
        System.out.println("2. See all game statistics");
        System.out.println("3. Exit");
        int selection = scanner.nextInt();
//        return selection;
    }

    /**
     * This method prints out to the user that the game is beginning
     */
    public void displayPlayGameBanner() {
        System.out.println("\n===== PLAYING GAME =====\n");
    }

    /**
     * This method gets the user guesses from stdin. The user inputs 4 numbers and outputs them as an integer array.
     * @return answer -- an Integer array of size 4 that consists of all 4 numbers received as input from the user.
     */
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

    /**
     * This method prints out the results of a round in the form 'e:X:p:Y', where X and Y show the number of exact and
     * partial matches there are between the user's guess and the correct answer for this game.
     * @param exacts -- shows how many of the numbers in the user's guess that directly match the correct answer and
     *               have been guessed in the same position
     * @param partials -- shows how many of the numbers in the user's guess appear in the correct answer, but have not
     *                 been guessed in the correct location.
     */
    public void printOutResults(int exacts, int partials) { System.out.println("e:" + exacts + ":p:" + partials); }

    /**
     * This method displays a message to the user declaring they have won the game.
     */
    public void displayWinnerBanner() { System.out.println("\nCongratulations! You won the game!\n"); }

    /**
     * This method displays a message to the user declaring they have lost the game.
     */
    public void displayLoserBanner() {
        System.out.println("\nYou lost! Try again another time.\n");
    }

    /**
     * This method displays a message to the user declaring that all game information will be displayed in the lines
     * below.
     */
    public void displayAllGamesBanner() {
        System.out.println("\n===== DISPLAYING ALL GAME INFORMATION =====\n");
    }

    /**
     * This method gets and displays the ID, answer, status, and result of every game recorded in the games table to
     * the user.
     * @param games -- a list of Game objects obtained from the MySQL database
     */
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

    /**
     * This method displays a method to the user declaring that game information from a specific game will be displayed
     * to the user in the lines below.
     * @param id -- the unique identifier for each game record in the database
     */
    public void displayGameBanner(int id) {
        System.out.println("\n===== DISPLAYING GAME INFO =====\n");
        System.out.println("Game " + id + ": ");
    }

    /**
     * This method displays the information about each game in the database
     * @param games -- a list of game objects obtained from the MySQL database
     */
    public void displayGameInformation(List <Game> games) {
        for (Game game : games) {
            System.out.printf("%s: %s -- %s -- %s\n",
                    game.getId(),
                    game.getAnswer(),
                    game.getStatus(),
                    game.isWon());
        }
    }
}
