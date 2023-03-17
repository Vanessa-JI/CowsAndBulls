package org.example.dao;

import org.example.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Random;

//@Repository
public class GameDAO {
//    @Autowired
    private JdbcTemplate jdbc;

    // could get user input to set the max guesses and length of the array to guess as an improvement?
    public static final int MAXGUESSES = 5;
    public int guessNumber = 0;
    public int exacts = 0;
    public int partials = 0;


    public void listAll() {

        List<Game> games = jdbc.query("SELECT * FROM todo", new GameRowMapper());
        for (Game game : games) {
            System.out.printf("%s: %s -- %s -- %s\n",
                    game.getId(),
                    game.getAnswer(),
                    game.getNumberOfGuesses(),
                    game.isWon());
        }
        System.out.println("");

    }


    public Integer[] generateNumberArray() {
        Set<Integer> answer = new HashSet<>();
        Random random = new Random();

        while (answer.size() < 4) {
            int randomNumber = random.nextInt(10);
            answer.add(randomNumber);
        }

        Integer[] answerArr = new Integer[answer.size()]; // create an array with the same size as the set
        answer.toArray(answerArr); // copy the elements of the set to the array

        return answerArr;
    }

    //need to fix functionality to not add up already added exacts/partials and also to not
    // count a partial as number that has already been counted as an exact
    public boolean playRound(Integer[] userGuess, Integer[] answer) {
        for (int i = 0; i < answer.length; i++) {
            if (exacts == 4) {
                return false;
            }
            if (userGuess[i].equals(answer[i])) {
                exacts += 1;
            } else if (Arrays.asList(answer).contains(userGuess[i])) {
                partials += 1;
            }
        }   // end of for loop

        guessNumber ++;

        // once we have compared all the values in the user guess and the answer,
        // if we have not got 4 exacts, and the number of rounds is still less than
        // the maximum number of guesses, we continue playing
        if (exacts == 4) {
            return false;

        } else if ( (guessNumber < MAXGUESSES) ) {
            return true;
        } else { // final case is that we've run out of matches and haven't found the right answer
            return false;
        }

    }



    public boolean getGameResult(int exacts, int partials, int guessNumber, int maxguesses) {

        // the only condition if you win is if the maximum number of guesses
        if ( (exacts == 4) && (guessNumber <= maxguesses) ) {
            return true;
        }

        return false;
    }
}
