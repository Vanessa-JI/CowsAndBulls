package org.example.service;

public class GameService {
    private int[] randomNums;
    public static final int MAXNUMBEROFGOES = 5; // static is shared!!!
    private int noGoes = 0;

    public GameService() {
    }

    public int[] getRandomNums() {
        return randomNums;
    }



    public int getNoGoes() {
        return noGoes;
    }

    public void setNoGoes(int noGoes) {
        this.noGoes = noGoes;
    }
}
