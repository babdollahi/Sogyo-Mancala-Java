package mancala.domain;

public class Player {

    private final Player opponent;
    private boolean hasTurn;

    public Player() {
        this.hasTurn = true;
        this.opponent = new Player(this);
    }

    private Player(Player opponent) {
        this.hasTurn = false;
        this.opponent = opponent;
    }

    public boolean hasTurn() {
        return this.hasTurn;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public void switchTurn() {
        this.switchTurnAlone();
        this.opponent.switchTurnAlone();
    }

    private void switchTurnAlone() {
        this.hasTurn = !this.hasTurn;
    }

    public void gameOver() {
        this.gameOverAlone();
        this.getOpponent().gameOverAlone();
    }

    private void gameOverAlone() {
        this.hasTurn = false;
    }

}
