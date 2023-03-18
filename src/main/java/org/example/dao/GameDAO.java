package org.example.dao;

import org.example.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Random;

@Repository
public class GameDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // could get user input to set the max guesses and length of the array to guess as an improvement?
    public static final int MAXGUESSES = 5;
    public int guessNumber = 0;
    public int exacts = 0;
    public int partials = 0;

    public GameDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void listAll() {

        List<Game> games = jdbcTemplate.query("SELECT * FROM todo", new GameRowMapper());
        for (Game game : games) {
            System.out.printf("%s: %s -- %s -- %s\n",
                    game.getId(),
                    game.getAnswer(),
                    game.getNumberOfGuesses(),
                    game.isWon());
        }
        System.out.println("");

    }


//    public String generateNumberArray() {
//        Set<Integer> answer = new HashSet<>();
//        Random random = new Random();
//
//        while (answer.size() < 4) {
//            int randomNumber = random.nextInt(10);
//            answer.add(randomNumber);
//        }
//
//        String answerStr = new String();
//
//        StringBuilder sb = new StringBuilder();
//        for (int num : answer) {
//            sb.append(num);
//        }
//
//        answerStr = sb.toString();
//
////        Integer[] answerArr = new Integer[answer.size()]; // create an array with the same size as the set
////        answer.toArray(answerArr); // copy the elements of the set to the array
//
//        return answerStr;
//    }

    public List<Game> getAllGames() {
        List <Game> games = jdbcTemplate.query("SELECT * FROM games", new GameRowMapper());
        return games;
    }

    public void insertRecord(String insertString, String ans, boolean inProg, boolean iswon) {
        jdbcTemplate.update(insertString, ans, inProg, iswon);
//        System.out.println("Result of insert is " +result);
    }
}
