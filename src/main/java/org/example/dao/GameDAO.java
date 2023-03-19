package org.example.dao;

import org.example.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

// @Repository annotation is used to indicate that a class is a repository, which is a type of Spring-managed component
// that is responsible for data access and storage
@Repository
public class GameDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public GameDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This method lists all game information present in the games table. Accesses the database via JDBCTemplate object,
     * and use RowMapper to appropriately map the table information into a results set via a prepared statement.
     */
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

    /**
     * This method returns a list of games to the controller, where all the games are obtained from the database.
     * @return games:
     *         the list of games obtained from the database
     */
    public List<Game> getAllGames() {
        List <Game> games = jdbcTemplate.query("SELECT * FROM games", new GameRowMapper());
        return games;
    }

    /**
     * This method inserts a record into the database given a prepared statement, the game's answer, a boolean
     * representing whether or not the game has been won.
     */
    public void insertRecord(String insertString, String ans, boolean inProg, boolean iswon) {
        jdbcTemplate.update(insertString, ans, inProg, iswon);
    }

    /**
     * This method retrieves a game record from the database using the unique ID
     * @param id -- the unique ID automatically assigned to each game record when it's added to the database. Used to
     *           retrieve a record and field information from the games table.
     * @return the game in list from the game record if the ID is present in the table, otherwise return null if no
     * record can be found.
     */
    public List<Game> getGameByID(int id) {
        try {   // try to find the game record in the table
            final String SELECT_GAME_BY_ID = "SELECT * FROM games WHERE gameid = ?";
            return jdbcTemplate.query(SELECT_GAME_BY_ID, new GameRowMapper(), id); // return the game if found
        } catch(DataAccessException ex) {
            return null; // return null and throw an exception if the game record couldn't be found
        }
    }
}
