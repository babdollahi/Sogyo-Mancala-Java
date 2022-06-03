package mancala.domain;

public class Mancala implements IMancala {

    public Mancala(String playerOneName, String playerTwoName) {
        // Initialize the game here.
    }

    @Override
    public String getNameOfPlayerOne(){
        return "Mario";
    }

    @Override
    public String getNameOfPlayerTwo(){
        return "Luigi";
    }

    @Override
    public boolean isPlayersTurn(String player) {
        return true;
    }

    @Override
	public void playPit(int index) {
        // Implement playing a pit.
    }
	
	@Override
	public int getStonesForPit(int index) {
        return 20;
    }

	@Override
	public boolean isEndOfGame() {
        return false;
    }

	@Override
	public Winner getWinner() {
        return Winner.NO_ONE;
    }
}