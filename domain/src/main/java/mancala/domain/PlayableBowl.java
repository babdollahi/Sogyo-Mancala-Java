package mancala.domain;

public class PlayableBowl extends Bowl {

    public PlayableBowl() {
        this(new int[] { 4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0 });
    }

    PlayableBowl(int[] stoneDistribution) {
        if (stoneDistribution.length != 14) {
            throw new IllegalArgumentException("board must have 14 bowls");
        }
        this.numberOfStones = stoneDistribution[0];
        this.owner = new Player();
        this.nextBowl = new PlayableBowl(dropFirstnumber(stoneDistribution), this.owner, this);
    }

    PlayableBowl(int[] remainingStones, Player owner, Bowl firstBowl) {
        this.numberOfStones = remainingStones[0];
        this.owner = owner;
        int[] remainingStonesAfterThis = dropFirstnumber(remainingStones);
        if (remainingStonesAfterThis.length % 7 == 1) {
            this.nextBowl = new Kalaha(remainingStonesAfterThis, owner, firstBowl);
        } else {
            this.nextBowl = new PlayableBowl(remainingStonesAfterThis, owner, firstBowl);
        }
    }

    public void play() {
        if (!this.canBePlayed()) {
            throw new IllegalStateException("bowl cannot be played");
        }
        this.startMove();
    }

    private boolean canBePlayed() {
        return this.numberOfStones > 0 && this.owner.hasTurn();
    }

    private void startMove() {
        int contents = this.numberOfStones;
        this.numberOfStones = 0;
        this.nextBowl.continueMove(contents);
    }

    @Override
    void endMove() {
        if (this.canSteal()) {
            this.steal();
        }
        this.owner.switchTurn();
        super.endMove();
    }

    private boolean canSteal() {
        return this.numberOfStones == 1 && this.owner.hasTurn();
    }

    private void steal() {
        this.giveStonesToMyKalaha();
        this.getOpposite().giveStonesToOpponentsKalaha();
    }

    @Override
    Kalaha getKalahaOf(Player player) {
        return this.nextBowl.getKalahaOf(player);
    }

    private void giveStonesToMyKalaha() {
        this.giveStonesToKalahaOf(this.owner);
    }

    void giveStonesToOpponentsKalaha() {
        this.giveStonesToKalahaOf(this.owner.getOpponent());
    }

    private void giveStonesToKalahaOf(Player player) {
        Kalaha target = this.getKalahaOf(player);
        int stonesToTransfer = this.numberOfStones;
        this.numberOfStones = 0;
        target.receiveStones(stonesToTransfer);
    }

    PlayableBowl getOpposite() {
        return (PlayableBowl) this.getOppositeAndShift(0);
    }

    @Override
    Bowl getOppositeAndShift(int amount) {
        return this.nextBowl.getOppositeAndShift(amount + 1);
    }

    @Override
    void checkGameOver() {
        if (this.numberOfStones == 0) {
            this.nextBowl.checkGameOver();
        }
    }

    @Override
    void sweepToKalaha() {
        this.giveStonesToMyKalaha();
        this.nextBowl.sweepToKalaha();
    }

}
