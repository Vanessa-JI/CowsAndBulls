package org.example.view;

//import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class GameView {

    public static int displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Bulls and Cows.");
        System.out.println("===========================\n");
        System.out.println("Pick an option:");
        System.out.println("1. Play a game");
        System.out.println("2. See all game statistics");
        System.out.println("3. Exit");
        int selection = scanner.nextInt();

        return selection;
    }

}
