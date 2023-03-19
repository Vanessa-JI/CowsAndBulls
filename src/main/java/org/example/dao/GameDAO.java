package org.example.dao;

import org.example.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public GameDAO() {

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

    public List<Game> getAllGames() {
        List <Game> games = jdbcTemplate.query("SELECT * FROM games", new GameRowMapper());
        return games;
    }

    public void insertRecord(String insertString, String ans, boolean inProg, boolean iswon) {
        jdbcTemplate.update(insertString, ans, inProg, iswon);
//        System.out.println("Result of insert is " +result);
    }

    public List<Game> getGameByID(int id) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM games WHERE gameid = ?";
            return jdbcTemplate.query(SELECT_GAME_BY_ID, new GameRowMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }
}
