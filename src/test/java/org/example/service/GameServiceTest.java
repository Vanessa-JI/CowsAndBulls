package org.example.service;

import org.example.dao.GameDAO;
import org.example.dao.GameDAOTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    public GameServiceTest() {

    }

    @Autowired
    GameService service;
//    GameDAO dao;
//
//    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
//        jdbcTemplate = new JdbcTemplate();
//        dao = new GameDAO(jdbcTemplate);
        service = new GameService();
    }

    @Test
    void getGameResult() {
        // the only condition that you win is if the maximum number of guesses
        int exacts = 4;
        int guessNumber = 3;
        int maxguesses = 4;

        boolean result = service.getGameResult(exacts, guessNumber, maxguesses);
        assertEquals(result, true);
    }
}