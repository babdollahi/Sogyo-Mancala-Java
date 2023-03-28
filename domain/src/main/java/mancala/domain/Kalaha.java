package mancala.domain;

import java.util.Optional;

public class Kalaha extends Bowl {

    Kalaha(int[] remainingStones, Player owner, Bowl firstBowl) {
        this.numberOfStones = remainingStones[0];
        this.owner = owner;
        int[] remainingStonesAfterThis = dropFirstnumber(remainingStones);
        if (remainingStonesAfterThis.length == 0) {
            this.nextBowl = firstBowl;
        } else {
            this.nextBowl = new PlayableBowl(remainingStonesAfterThis, owner.getOpponent(), firstBowl);
        }
    }

    @Override
    void continueMove(int receivedStones) {
        if (this.owner.hasTurn()) {
            super.continueMove(receivedStones);
        } else {
            this.nextBowl.continueMove(receivedStones);
        }
    }

    @Override
    Kalaha getKalahaOf(Player player) {
        if (this.owner == player) {
            return this;
        }
        return this.nextBowl.getKalahaOf(player);
    }

    void receiveStones(int stones) {
        this.numberOfStones += stones;
    }

    @Override
    Bowl getOppositeAndShift(int amount) {
        return this.getBowlAtDistance(amount);
    }

    @Override
    void checkGameOver() {
        this.nextBowl.sweepToKalaha();
    }

    @Override
    void sweepToKalaha() {
        this.endGame();
    }

    private void endGame() {
        this.owner.gameOver();
    }

    public Optional<Player> whoIsAhead() {
        int myStones = this.numberOfStones;
        Kalaha oppositeKalaha = this.getKalahaOf(this.owner.getOpponent());
        return oppositeKalaha.whoIsAhead(myStones);
    }

    Optional<Player> whoIsAhead(int opponentStones) {
        int myStones = this.numberOfStones;
        if (myStones > opponentStones) {
            return Optional.of(this.owner);
        } else if (myStones < opponentStones) {
            return Optional.of(this.owner.getOpponent());
        } else {
            return Optional.empty();
        }
    }

}
