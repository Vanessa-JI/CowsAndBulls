package org.example.controller;

import org.example.dao.GameDAO;
import org.example.dto.Game;
import org.example.service.GameService;
import org.example.view.GameView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.sql.MysqlDataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api")
public class Controller {
    private GameView view;
    private GameDAO dao;

    private GameService service;
    private String answer;


    @Autowired
    public Controller(GameDAO dao, GameView view, GameService service) {
        this.dao = dao;
        this.view = view;
        this.service = service;
        this.answer = this.service.generateNumberArray();
    }

    @GetMapping
    public void startApp() {

//        while (true) {

//            this.answer = dao.generateNumberArray();
            view.displayMainMenu();

//            switch (selection) {
//                case 1 -> playGame();
//                case 2 -> displayAllGames();
//            }
//        }
    }

    @GetMapping("/displaygames")
    public void displayAllGames() {
        view.displayAllGamesBanner();
        List<Game> games = service.getAllGames();
        view.displayTableOfGames(games);
    }

    @PostMapping("/playgame")
    public void playGame(char a, char b, char c, char d) {
        view.displayPlayGameBanner();
        // create a new Game record and insert into table
        Game game = new Game();
        game.setStatus(true);
        game.setAnswer(answer);
        service.insertRecord(game);

        for (int i = 0; i < answer.length(); i++) {
            System.out.println(answer.charAt(i));
        }

        String userGuess = Character.toString(a).concat(Character.toString(b)).concat(Character.toString(c)).concat(Character.toString(d)); // may need to have a look at this
        boolean gameInProgress = service.playRound(userGuess, answer);
        if (gameInProgress) {
            view.printOutResults(service.exacts, service.partials);
        } else {
            boolean result = service.getGameResult(service.exacts, service.partials, service.guessNumber, service.MAXGUESSES);
            if (result) {
                view.displayWinnerBanner();
                game.setWon(true);
            } else {
                view.displayLoserBanner();
                game.setWon(false);
            }
        }
    }


//
//        boolean play = true;
//        while (play) {
////            Integer[] userGuess = view.getUserGuess();
//            String userGuess = String.valueOf(a + b + c + d); // may need to have a look at this
//            boolean gameInProgress = dao.playRound(userGuess, answer);
//
//            if (gameInProgress) {
//                view.printOutResults(dao.exacts, dao.partials);
//            } else {
//                boolean result = dao.getGameResult(dao.exacts, dao.partials, dao.guessNumber, dao.MAXGUESSES);
//                if (result) {
//                    view.displayWinnerBanner();
//                    game.setWon(true);
//                    play = false;
//                } else {
//                    view.displayLoserBanner();
//                    game.setWon(false);
//                }
//            }
//        } // end of while loop

//        game.setStatus(false);

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
