package org.example.service;

import org.example.dao.GameDAO;
import org.example.dao.GameRowMapper;
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


    public void listAll() {
        dao.listAll();
    }

    public String generateNumberArray() {
        Set<Integer> answer = new HashSet<>();
        Random random = new Random();

        while (answer.size() < 4) {
            int randomNumber = random.nextInt(10);
            answer.add(randomNumber);
        }

        String answerStr = new String();

        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num);
        }

        answerStr = sb.toString();

        return answerStr;
    }


    public boolean playRound(String userGuess, String answer) {
        int[] guessResult = new int[]{-1, -1, -1, -1};
        for (int i = 0; i < answer.length(); i++) {
            if (exacts == 4) {
                return false;
            }
            if (userGuess.charAt(i) == answer.charAt(i)) {
                guessResult[i] = 1;
            } else if (Arrays.asList(answer).contains(userGuess.charAt(i))) {
                guessResult[i] = 0;
            }
        }   // end of for loop

        // update guessNumber, exacts, and partials
        guessNumber++;
        exacts = (int) Arrays.stream(guessResult)
                .filter(num -> num == 1)
                .count();
        partials = (int) Arrays.stream(guessResult)
                .filter(num -> num == 0)
                .count();


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


    public boolean getGameResult(int exacts, int partials, int guessNumber, int maxguesses) {

        // the only condition that you win is if the maximum number of guesses
        if ((exacts == 4) && (guessNumber <= maxguesses)) {
            return true;
        }
        return false;
    }


    public List<Game> getAllGames() {
        return dao.getAllGames();
    }

    public void insertRecord(Game game) {
        String ans = game.getAnswer();
        boolean isWon = game.isWon();
        boolean inProg = game.getStatus();
        String insertString = "insert into Games (answer, inProgress, win) " +
                "values (?, ?, ?)";
        dao.insertRecord(insertString, ans, inProg, isWon);
    }
}
