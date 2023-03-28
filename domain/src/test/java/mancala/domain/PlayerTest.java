package mancala.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    public void testNewPlayerHasTurn() {
        Player player = new Player();
        assertTrue(player.hasTurn());
    }

    @Test
    public void testOpponentOfNewPlayerDoesNotHaveTurn() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        assertFalse(opponent.hasTurn());
    }

    @Test
    public void testPlayerDoesNotEqualOpponent() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        assertNotEquals(player, opponent);
    }

    @Test
    public void testOpponentOfOpponentIsPlayer() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        assertEquals(player, opponent.getOpponent());
    }

    @Test
    public void testSwitchingTurnSwitchesTurn() {
        Player player = new Player();
        player.switchTurn();
        assertFalse(player.hasTurn());
        assertTrue(player.getOpponent().hasTurn());
    }

    @Test
    public void testSwitchingTurnOnOpponentAlsoSwitchesTurn() {
        Player player = new Player();
        player.getOpponent().switchTurn();
        assertFalse(player.hasTurn());
        assertTrue(player.getOpponent().hasTurn());
    }

    @Test
    public void testAfterGameOverNooneHasTurn() {
        Player player = new Player();
        player.gameOver();
        assertFalse(player.hasTurn());
        assertFalse(player.getOpponent().hasTurn());
    }

    @Test
    public void testAfterOpponentGameOverNooneHasTurn() {
        Player player = new Player();
        player.getOpponent().gameOver();
        assertFalse(player.hasTurn());
        assertFalse(player.getOpponent().hasTurn());
    }

}
