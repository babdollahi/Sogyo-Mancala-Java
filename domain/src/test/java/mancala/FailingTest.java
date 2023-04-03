package mancala;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mancala.domain.Player;

class PlayerTest {

    @Test
    public void testNewPlayerHasTurn() {
        Player player = new Player();
        assertTrue(player.hasTurn());
    }
}