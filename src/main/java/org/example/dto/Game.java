package org.example.dto;

public class Game {

    private int id;
    private int numberOfGuesses;
    private String answer;
    private boolean isWon;

    public Game() {

    }

    public Game(int id, int numberOfGuesses, String answer, boolean isWon) {
        this.id = id;
        this.numberOfGuesses = numberOfGuesses;
        this.answer = answer;
        this.isWon = isWon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public void setNumberOfGuesses(int numberOfGuesses) {
        this.numberOfGuesses = numberOfGuesses;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean won) {
        isWon = won;
    }
}
