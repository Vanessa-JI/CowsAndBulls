package org.example.dao;

import org.example.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class GameDAO {
//    @Autowired
    private JdbcTemplate jdbc;

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


}
