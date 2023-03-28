package mancala.domain;

import java.util.Arrays;

public abstract class Bowl {

    // Instance variables have to be default to share with child classes
    Bowl nextBowl;
    Player owner;
    int numberOfStones;

    static int[] dropFirstnumber(int[] array) {
        return Arrays.copyOfRange(array, 1, array.length);
    }

    public Player getOwner() {
        return this.owner;
    }

    public int getNumberOfStones() {
        return this.numberOfStones;
    }

    public Bowl getBowlAtDistance(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("cannot get bowl at negative distance");
        }
        if (distance == 0) {
            return this;
        }
        return this.nextBowl.getBowlAtDistance(distance - 1);
    }

    void continueMove(int receivedStones) {
        this.numberOfStones++;
        if (receivedStones > 1) {
            this.nextBowl.continueMove(receivedStones - 1);
        } else {
            endMove();
        }
    }

    void endMove() {
        this.getCurrentsPlayersFirstBowl().checkGameOver();
    };

    abstract Kalaha getKalahaOf(Player player);

    abstract Bowl getOppositeAndShift(int amount);

    Bowl getCurrentsPlayersFirstBowl() {
        Player currentPlayer = this.owner.hasTurn() ? this.owner : this.owner.getOpponent();
        return this.getKalahaOf(currentPlayer.getOpponent()).getBowlAtDistance(1);
    }

    abstract void checkGameOver();

    abstract void sweepToKalaha();

}
