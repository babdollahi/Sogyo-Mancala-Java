package mancala.domain;

public class MancalaImpl implements Mancala {
    Bowl game;

    public MancalaImpl() {
        this.game = new Bowl("player1","player2");
    }

    @Override
    public boolean isPlayersTurn(int player) {
        if(player == 1){
            return this.game.getPlayer().hasTurn();
        }
        return this.game.getPlayer().getOpponent().hasTurn();
    }

    @Override
	public void playPit(int index) {
        try{
            Bowl bowlToPlay = (Bowl)this.game.getNeighbor(index);
            bowlToPlay.play();
        } catch (ClassCastException e){ }
    }
	
	@Override
	public int getStonesForPit(int index) {
        return this.game.getNeighbor(index).getContent();
    }

	@Override
	public boolean isEndOfGame() {
        return !(
            this.game.getPlayer().canStillPlay() 
            && this.game.getPlayer().getOpponent().canStillPlay()
        );
    }

	@Override
	public int getWinner() {
        boolean isEndOfGame = isEndOfGame();
        String whoWon = this.game.getPlayer().whoWon();
        if(isEndOfGame){
            switch(whoWon){
                case "player1":
                    return Mancala.PLAYER_ONE;
                case "player2":
                    return Mancala.PLAYER_TWO;
                case "no-one":
                    return Mancala.NO_PLAYERS;
                default:
                    return Mancala.BOTH_PLAYERS;
            }
        }
        return Mancala.NO_PLAYERS;
    }
}