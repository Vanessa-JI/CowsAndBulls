package org.example.service;

import org.example.dao.GameDAO;
import org.example.dto.Game;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameService {
    public static final int MAXGUESSES = 5;
    public int guessNumber = 0;
    public int exacts = 0;
    public int partials = 0;
    public GameDAO dao;

    public GameService(GameDAO dao) {
        this.dao = dao;
    }

    public GameService() {

    }

    public int getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(int guessNumber) {
        this.guessNumber = guessNumber;
    }

    public int getExacts() {
        return exacts;
    }

    public void setExacts(int exacts) {
        this.exacts = exacts;
    }

    public int getPartials() {
        return partials;
    }

    public void setPartials(int partials) {
        this.partials = partials;
    }

    public GameDAO getDao() {
        return dao;
    }

    public void setDao(GameDAO dao) {
        this.dao = dao;
    }

    /**
     * This method generates a string representation of a four-digit number containing unique digits using a random
     * number generator. A HashSet is created to store unique digits and a random object is created to generate random
     * numbers. A loop runs until the HashSet contains four unique digits. In each iteration, a random number between 0
     * and 9 is generated using the nextInt() method of the Random object, and it is added to the HashSet Once the
     * HashSet contains four unique digits, a StringBuilder is used to concatenate the digits into a string. The
     * toString() method is called on the StringBuilder to get the final string representation of the four-digit number.
     * @return answer -- a String representing the answer to the game is returned. This is the sequence of numbers the
     * user is attempting to guess.
     */
    public String generateNumberArray() {
        Set<Integer> answer = new HashSet<>();
        Random random = new Random();

        while (answer.size() < 4) {
            int randomNumber = random.nextInt(10);
            answer.add(randomNumber);
        }
        String answerStr;
        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num);
        }
        answerStr = sb.toString();
        return answerStr;
    }

    /**
     * This method takes two input strings: userGuess and answer, representing the user's guess and the correct answer,
     * respectively.
     * @return -- boolean value indicating whether the game should continue (true) or end (false).
     */
    public boolean playRound(String userGuess, String answer) {
        int[] guessResult = new int[]{-1, -1, -1, -1}; // an array used to count exacts and partials is initialised with -1's
        for (int i = 0; i < answer.length(); i++) {
            if (exacts == 4) {
                return false; // if we can immediately find 4 exacts in the guess, return to cause the game to end
            }
            if (userGuess.charAt(i) == answer.charAt(i)) {
                guessResult[i] = 1; // if the correct number has been guessed in the correct position, update guess with 1
            } else if (Arrays.asList(answer).contains(userGuess.charAt(i))) {
                guessResult[i] = 0; // if the user guesses a number in the incorrect position, update guess with a 0
            }
        }   // end of for loop

        // update guessNumber, exacts, and partials
        guessNumber++;
        exacts = (int) Arrays.stream(guessResult)
                .filter(num -> num == 1)
                .count(); // counting the number of exacts in the guess array
        partials = (int) Arrays.stream(guessResult)
                .filter(num -> num == 0)
                .count(); // counting the number of partials in the guess array

        // once we have compared all the values in the user guess and the answer,
        // if we have not got 4 exacts, and the number of rounds is still less than
        // the maximum number of guesses, we continue playing
        if (exacts == 4) {
            return false;
        } else if ((guessNumber < MAXGUESSES)) {
            return true;
        } else { // final case is that we've run out of matches and haven't found the right answer
            return false;
        }
    }

    /**
     * This method determines the result of the game at hand by checking how many exacts and partials there are when
     * comparing the user's guess with the correct answer.
     * @param exacts -- number of exact matches between user's guess and answer
     * @param guessNumber -- how many guesses the user has currently made
     * @param maxGuesses -- the maximum number of guesses that someone can make
     * @return boolean to say whether (true) or not (false) the user has won the game
     */
    public boolean getGameResult(int exacts, int guessNumber, int maxGuesses) {

        // the only condition that you win is if the maximum number of guesses
        if ((exacts == 4) && (guessNumber <= maxGuesses)) {
            return true;
        }
        return false;
    }

    /**
     * This method gets all the game information currently displayed in the game table
     * @return -- returns a list of game objects obtained from the MySQL database via the DAO
     */
    public List<Game> getAllGames() {
        return dao.getAllGames(); // no business logic required to be defined, so dao method is returned immediately
    }

    /**
     * This method inserts a game record into the database. It sets whether or not the game has won, and the status of
     * the game and uses a prepared statement to insert the record appropriately.
     * @param game -- game object contains all information for a single game
     */
    public void insertRecord(Game game) {
        String ans = game.getAnswer();
        boolean isWon = game.isWon();
        boolean inProg = game.getStatus();
        String insertString = "insert into Games (answer, inProgress, win) " +
                "values (?, ?, ?)";
        dao.insertRecord(insertString, ans, inProg, isWon);
    }

    /**
     * This method gets the information about a given game given its unique identifier (gameID) from the table
     * @param id -- unique identifier for each game record stored in the database
     * @return -- return a list of games taken from the database
     */
    public List <Game> getGameInformation(int id) {
        return dao.getGameByID(id);
    }

}
