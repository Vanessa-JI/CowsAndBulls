package org.example.dao;

import junit.framework.TestCase;
import org.example.TestApplicationConfiguration;
import org.example.dto.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDAOTest extends TestCase {

    @Autowired
    GameDAO dao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    // before each test, clear the test database
    @BeforeEach
    public void setUp() throws Exception {
        jdbcTemplate = new JdbcTemplate();
        dao = new GameDAO(jdbcTemplate);
        List<Game> games = dao.getAllGames();
        for(Game game : games) {
            dao.getGameByID(game.getId());
        }
    }

    @Test
    public void testInsertRecord() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(true);
        game.setWon(true);
        String insertString = "insert into Games (answer, inProgress, win) " +
                "values (?, ?, ?)";
        dao.insertRecord(insertString, game.getAnswer(), game.getStatus(), game.isWon());

        List<Game> fromDao = dao.getGameByID(game.getId());
        Game testGame = fromDao.get(0);

        assertEquals(game, testGame);
    }
}