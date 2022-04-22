package mancala.api.models;

// This package is a collection of DTO's (data transfer objects).
// A DTO is a simple datastructure which models the
// data your web API sends back to the client. The Java
// objects will be converted to JSON objects.
public class Mancala {
    public Mancala(mancala.domain.Mancala mancala,
            String namePlayer1, String namePlayer2) {
        players = new PlayerModel[2];
        players[0] = new PlayerModel();
        players[1] = new PlayerModel();

        players[0].setName(namePlayer1);
        players[1].setName(namePlayer2);

        gameStatus = new GameStatus(mancala, namePlayer1, namePlayer2);
    }

    PlayerModel[] players;

    public PlayerModel[] getPlayers() {
        return players;
    }

    GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}