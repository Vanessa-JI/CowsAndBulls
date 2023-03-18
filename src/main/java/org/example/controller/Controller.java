package org.example.controller;

import org.example.dao.GameDAO;
import org.example.dto.Game;
import org.example.service.GameService;
import org.example.view.GameView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.sql.MysqlDataSource;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api")
public class Controller {
    private GameView view;
    private GameDAO dao;

    private GameService service;

    @Autowired
    public Controller(GameDAO dao, GameView view, GameService service) {
        this.dao = dao;
        this.view = view;
        this.service = service;
    }

    @GetMapping
    public void startApp() {

//        while (true) {

            view.displayMainMenu();

//            switch (selection) {
//                case 1 -> playGame();
//                case 2 -> displayAllGames();
//            }
//        }
    }

    @GetMapping("/playgame")
    public void playGame() {
        view.displayPlayGameBanner();
        Integer[] answer = dao.generateNumberArray();

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }

        boolean play = true;
        while (play) {
            Integer[] userGuess = view.getUserGuess();
            boolean gameInProgress = dao.playRound(userGuess, answer);

            if (gameInProgress) {
                view.printOutResults(dao.exacts, dao.partials);
            } else {
                boolean result = dao.getGameResult(dao.exacts, dao.partials, dao.guessNumber, dao.MAXGUESSES);
                if (result) {
                    view.displayWinnerBanner();
                    play = false;
                } else {
                    view.displayLoserBanner();
                }
            }
        }

        // return a boolean to see if playRounds is still eligible
        // to play the game:
        // 0. Display playing game banner (view)
        // 1. generate an array of random numbers to be guessed (service)
        // 2. ask the user to guess 4 numbers and get their input (view)
        // 3. Check if the guesses match the original array (service) and
            // return boolean to say if game is still in progress (exacts !=4)
        // 4. Print out partials and exacts to the user if game is still in progress
        // 5. play rounds until rounds are up
    }

    @GetMapping("/displaygames")
    public void displayAllGames() {
        view.displayAllGamesBanner();
        List<Game> games = dao.getAllGames();

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
