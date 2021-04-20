package model;

public interface Score {

    int getScoreP1();

    int getScoreP2();

    String getPlayer1();

    String getPlayer2();

    void setScores(int scoreP1, int scoreP2);

    void setPlayer1(String player1);

    void setPlayer2(String player2);

    String toString();
}
