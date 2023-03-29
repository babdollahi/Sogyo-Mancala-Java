package mancala.domain;

import java.util.Optional;

public class MancalaGame implements Playable{
    private String player1;
    private String player2;
	private Bowl firstBowl;
    
    public MancalaGame(String player1, String player2){
        this.player1 = player1;
        this.player2 = player2;
		this.firstBowl = new PlayableBowl();

    }


	@Override
	public String getNameOfPlayerOne() {
        return player1;
    }

	@Override
	public String getNameOfPlayerTwo() {
        return player2;
	}

	@Override
	public int getStonesForPit(int index) {
		return firstBowl.getBowlAtDistance(index).getNumberOfStones();
	}

	@Override
	public Winner getWinner() {
		Kalaha kalaha = (Kalaha) firstBowl.getBowlAtDistance(6);
        Optional<Player> winner = kalaha.whoIsAhead();
		if (!isEndOfGame()){
			return Winner.NO_ONE;
		}
		
		if(winner.isEmpty()){
			return Winner.DRAW;
		}

		if(winner.get() == firstBowl.getOwner()){
			return Winner.PLAYER_1;
		}
		return Winner.PLAYER_2;
	}

	@Override
	public boolean isEndOfGame() {
		if (!firstBowl.getOwner().hasTurn() && !firstBowl.getOwner().getOpponent().hasTurn()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isPlayersTurn(String name) {
		if (name == getNameOfPlayerOne() && firstBowl.getOwner().hasTurn()) {
			return true;
		}
		return false;
	}

	@Override
	public void playPit(int index) {
        ((PlayableBowl) firstBowl.getBowlAtDistance(index)).play();
	}
	
}
