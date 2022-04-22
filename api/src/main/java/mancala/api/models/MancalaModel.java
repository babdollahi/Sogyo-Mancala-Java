package mancala.api.models;

public class MancalaModel {
    private final PlayerModel player1;
    private final PlayerModel player2;

    public MancalaModel(PlayerModel player1, PlayerModel player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public PlayerModel getPlayer1() {
        return player1;
    }

    public PlayerModel getPlayer2() {
        return player2;
    }

}
