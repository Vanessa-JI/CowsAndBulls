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
import java.util.List;

// @RestController annotation defines a RESTful web service endpoint
// every method in the class will return data that is written directly
// to the HTTP response as JSON or XML
@RestController
@RequestMapping("/api")
public class Controller {

    // defining private member variables
    private GameView view;
    private GameService service;
    private String answer;

    // @Autowired annotation automatically wires dependencies into a
    // Spring-managed bean
    @Autowired
    public Controller(GameView view, GameDAO dao) {
        this.view = view;
        this.service = new GameService(dao);
        this.answer = this.service.generateNumberArray();
    }

    // @GetMapping annotation defines HTTP GET endpoints in Spring
    // When a GET request is sent to the URL path that is associated with
    // the annotated method, Spring will invoke that method and return the
    // value that it produces as an HTTP response
    @GetMapping
    /**
     * This method starts the application and displays the main menu to the user to select an option
     */
    public void startApp() {
//        while (true) {
            view.displayMainMenu();
//            switch (selection) {
//                case 1 -> playGame();
//                case 2 -> displayAllGames();
//            }
//        }
    }

    @GetMapping("/displaygames")
    /**
     * This method uses the view to display to the user that the next output lines will display the game ID, answer,
     * status, and result of all games recorded in the games table of the database.
     *
     */
    public void displayAllGames() {
        view.displayAllGamesBanner(); // view displays to the user the method that is running
        List<Game> games = service.getAllGames(); // service layer returns a list of all games via the DAO
        view.displayTableOfGames(games); // view displays a summary of all games in the table to the user
    }

    @GetMapping("/gamebyid")
    /**
     * This method gets a game from the games table via an ID and displays all information regarding the game, including
     * the game ID, answer, in progress status, and result.
     * @param id
     *       integer representing the game ID that is used to locate the game of interest in the games table
     */
    public void getGameByID(int id) {
        view.displayGameBanner(id); // view displays selected method to the user
        List <Game> games = service.getGameInformation(id); // service layer returns a list of games to the user via the DAO
        if (games != null) {
            view.displayGameInformation(games); // if a game with the given ID is available, display its info
        }
    }

    @PostMapping("/playgame")
    /**
     * This method allows the user to play one round of the game via Postman by taking in a user input from Postman via
     * a POST request. The inputs represent the user's guess, which are taken in and compared to the real answer. The
     * game status is updated when the game begins and the answer is set. The table information is updated in the
     * database. The user guess is checked against the answer and a banner displaying a win or a loss is displayed
     * depending on whether they won or lost the round
     * @params a, b, c, d:
     *        these characters represent the user's input guess -- the characters are concatenated into a String and
     *        this String is checked for equality against the answer.
     */
    public void playGame(char a, char b, char c, char d) {
        view.displayPlayGameBanner();
        Game game = new Game(); // create a new Game record and insert into table
        game.setStatus(true); // when the round starts, the game status is set to true
        game.setAnswer(answer); // the answer field is set for the game also
        service.insertRecord(game); // game record is inserted into the database via the DAO

        for (int i = 0; i < answer.length(); i++) {
            System.out.println(answer.charAt(i)); // for debugging purposes -- answer is printed out
        }

        String userGuess = Character.toString(a)
                .concat(Character.toString(b))
                .concat(Character.toString(c))
                .concat(Character.toString(d)); // take user guess and concatenating into a String
        boolean gameInProgress = service.playRound(userGuess, answer);
        if (gameInProgress) {   // if the game is still in progress, the view prints out the results of the round
            view.printOutResults(service.exacts, service.partials);
        } else {    // if the game is no longer in progress, we get the result of the game
            boolean result = service.getGameResult(service.exacts, service.guessNumber, service.MAXGUESSES);
            if (result) {
                view.displayWinnerBanner(); // display a win banner if the user has correctly guessed the answer
                game.setWon(true);
            } else {
                view.displayLoserBanner(); // display a lose banner if the user doesn't manage to guess the answer
                game.setWon(false);
            }
            game.setStatus(false); // set the game status to false
        }
    }
}
