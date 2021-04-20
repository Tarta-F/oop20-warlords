package model;

public final class ScoreImpl {
    private String player1Name;
    private String player2Name;
    private int scoreP1;
    private int scoreP2;

    public ScoreImpl(final String player1Name, final String player2Name, final int scoreP1, final int scoreP2) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.scoreP1 = scoreP1;
        this.scoreP2 = scoreP2;
    }

    public ScoreImpl(final String p1, final String p2) {
        this.player1Name = p1;
        this.player2Name = p2;
    }

    public void setScoreP1(final int scoreP1) {
        this.scoreP1 = scoreP1;
    }

    public void setScoreP2(final int scoreP2) {
        this.scoreP2 = scoreP2;
    }

    @Override
    public String toString() {
        return this.player1Name + " Vs " + this.player2Name + " Result: " + this.scoreP1 + " - " + this.scoreP2;
    }
    public String getPlayer1() {
        return this.player1Name;
    }

    public String getPlayer2() {
        return this.player2Name;
    }

    public int getScoreP1() {
        return this.scoreP1;
    }

    public int getScoreP2() {
        return scoreP2;
    }

    public void setPlayer1(final String player1) {
        player1Name = player1;
    }

    public void setPlayer2(final String player2) {
        player2Name = player2;
    }
}
