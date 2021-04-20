package model;

public final class ScoreImpl implements Score {
    private String player1Name;
    private String player2Name;
    private int scoreP1;
    private int scoreP2;

    public ScoreImpl(final String player1Name, final String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public void setScoreP1(final int scoreP1) {
        this.scoreP1 = scoreP1;
    }

    @Override
    public void setScoreP2(final int scoreP2) {
        this.scoreP2 = scoreP2;
    }

    @Override
    public String toString() {
        return this.player1Name + " Vs " + this.player2Name + " Result: " + this.scoreP1 + " - " + this.scoreP2;
    }
    @Override
    public String getPlayer1() {
        return this.player1Name;
    }

    @Override
    public String getPlayer2() {
        return this.player2Name;
    }

    @Override
    public int getScoreP1() {
        return this.scoreP1;
    }

    @Override
    public int getScoreP2() {
        return scoreP2;
    }

    @Override
    public void setPlayer1(final String player1) {
        player1Name = player1;
    }

    @Override
    public void setPlayer2(final String player2) {
        player2Name = player2;
    }
}
