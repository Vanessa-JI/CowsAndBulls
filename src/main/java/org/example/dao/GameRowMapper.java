package org.example.dao;

import org.example.dto.Game;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRowMapper implements RowMapper {

    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
        Game a = new Game();
        a.setId(rs.getInt(1));
        a.setAnswer(rs.getString(2));
        a.setNumberOfGuesses(rs.getInt(3));
        a.setWon(rs.getBoolean(4));
        // System.out.println(a.toString());
        return a;
    }
}
