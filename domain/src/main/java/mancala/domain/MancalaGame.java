package mancala.domain;

import java.util.Optional;

public class MancalaGame implements Playable{
    private String player1;
    private String player2;
	private Bowl playebleBowl;
    
    public MancalaGame(String player1, String player2){
		this.playebleBowl = new PlayableBowl();
        this.player1 = player1;
        this.player2 = player2;

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
		return playebleBowl.getBowlAtDistance(index).getNumberOfStones();
	}

	@Override
	public Winner getWinner() {
		Kalaha kalaha = (Kalaha) playebleBowl.getBowlAtDistance(6);
        Optional<Player> winner = kalaha.whoIsAhead();
		if (!isEndOfGame()){
			return Winner.NO_ONE;
		}
		
		if(winner.isEmpty()){
			return Winner.DRAW;
		}

		if(winner.get() == playebleBowl.getOwner()){
			return Winner.PLAYER_1;
		}
		return Winner.PLAYER_2;
	}

	@Override
	public boolean isEndOfGame() {
		return false;
	}

	@Override
	public boolean isPlayersTurn(String name) {
		return false;
	}

	@Override
	public void playPit(int index) {	
	}

	
}
