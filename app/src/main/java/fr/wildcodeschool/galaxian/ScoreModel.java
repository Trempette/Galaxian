package fr.wildcodeschool.galaxian;

public class ScoreModel {
    String name;
    int score;

    public ScoreModel(String name, int score){
        this.name = name;
        this.score = score;
    }

    public ScoreModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
