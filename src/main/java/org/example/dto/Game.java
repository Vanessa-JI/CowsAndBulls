package org.example.dto;

import java.util.Objects;

public class Game {

    // defining private member variables
    private int id;
    private int numberOfGuesses;
    private String answer;
    private boolean isWon;
    private boolean status;

    public Game() {
    }

    // constructor initialises the appropriate member variables
    public Game(int id, boolean status, String answer, boolean isWon) {
        this.id = id;
        this.status = status;
        this.answer = answer;
        this.isWon = isWon;
    }

    // defining the appropriate accessors and mutators
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Implementing the equals and hashCode methods to ensure tests can be run
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game game)) return false;
        return getId() == game.getId() && getNumberOfGuesses() == game.getNumberOfGuesses() && isWon() == game.isWon()
                && getStatus() == game.getStatus() && getAnswer().equals(game.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumberOfGuesses(), getAnswer(), isWon(), getStatus());
    }
}
