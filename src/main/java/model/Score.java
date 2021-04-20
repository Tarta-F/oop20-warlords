package model;

public interface Score {

    void setScoreP1(int scoreP1);

    void setScoreP2(int scoreP2);

    String toString();

    String getPlayer1();

    String getPlayer2();

    int getScoreP1();

    int getScoreP2();

    void setPlayer1(String player1);

    void setPlayer2(String player2);

}
