package com.madridlabs.contadordepocha;

public class Player {
    private int score;
    private String name;

    public Player(String name) {
        this.name = name;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void increaseFive(){
        score += 5;
    }

    public void decreaseFive(){
        if (score != 0) score -= 5;
    }
}
