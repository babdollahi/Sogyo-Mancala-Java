package mancala.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayableBowlTest {

    @Test
    public void testFirstBowlBelongsToStartingPlayer() {
        Bowl firstBowl = new PlayableBowl();
        assertTrue(firstBowl.getOwner().hasTurn());
    }

    @Test
    public void testWalkingBackwardsIsNotAllowed() {
        Bowl firstBowl = new PlayableBowl();
        assertThrows(IllegalArgumentException.class, () -> {
            firstBowl.getBowlAtDistance(-1);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12 })
    public void testCorrectBowlsArePlayable(int index) {
        Bowl firstBowl = new PlayableBowl();
        Bowl playableBowl = firstBowl.getBowlAtDistance(index);
        assertTrue(playableBowl instanceof PlayableBowl);
    }

    @ParameterizedTest
    @ValueSource(ints = { 6, 13 })
    public void testCorrectBowlsAreKalahas(int index) {
        Bowl firstBowl = new PlayableBowl();
        Bowl kalaha = firstBowl.getBowlAtDistance(index);
        assertTrue(kalaha instanceof Kalaha);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12 })
    public void testPlayableBowlsHaveFourStones(int index) {
        Bowl firstBowl = new PlayableBowl();
        Bowl playableBowl = firstBowl.getBowlAtDistance(index);
        assertEquals(4, playableBowl.getNumberOfStones());
    }

    @ParameterizedTest
    @ValueSource(ints = { 6, 13 })
    public void testKalahasHaveNoStones(int index) {
        Bowl firstBowl = new PlayableBowl();
        Bowl kalaha = firstBowl.getBowlAtDistance(index);
        assertEquals(0, kalaha.getNumberOfStones());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6 })
    public void testFirstHalfOfBoardHasSameOwner(int index) {
        Bowl firstBowl = new PlayableBowl();
        Bowl bowl = firstBowl.getBowlAtDistance(index);
        assertEquals(firstBowl.getOwner(), bowl.getOwner());
    }

    @ParameterizedTest
    @ValueSource(ints = { 7, 8, 9, 10, 11, 12, 13 })
    public void testSecondHalfOfBoardHasOpponentAsOwner(int index) {
        Bowl firstBowl = new PlayableBowl();
        Bowl bowl = firstBowl.getBowlAtDistance(index);
        assertEquals(firstBowl.getOwner().getOpponent(), bowl.getOwner());
    }

    @Test
    public void testTheLoopIsClosed() {
        Bowl firstBowl = new PlayableBowl();
        Bowl bowl = firstBowl.getBowlAtDistance(14);
        assertEquals(firstBowl, bowl);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 })
    public void testInitializeNonstandardBoard(int index) {
        Bowl firstBowl = new PlayableBowl(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 });
        Bowl bowl = firstBowl.getBowlAtDistance(index);
        assertEquals(index, bowl.getNumberOfStones());
    }

    @Test
    public void testMoveEmptiesBowl() {
        PlayableBowl firstBowl = new PlayableBowl();
        firstBowl.play();
        assertEquals(0, firstBowl.getNumberOfStones());
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4 })
    public void testMoveDistributesTheStones(int index) {
        PlayableBowl firstBowl = new PlayableBowl();
        Bowl bowl = firstBowl.getBowlAtDistance(index);
        firstBowl.play();
        assertEquals(5, bowl.getNumberOfStones());
    }

    @Test
    public void testMoveDoesNotDistributeStonesItDoesNotHave() {
        PlayableBowl firstBowl = new PlayableBowl();
        firstBowl.play();
        Bowl bowl = firstBowl.getBowlAtDistance(5);
        assertEquals(4, bowl.getNumberOfStones());
    }

    @Test
    public void testEmptyBowlIsNotPlayable() {
        PlayableBowl firstBowl = new PlayableBowl(new int[] { 0, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0 });
        assertThrows(IllegalStateException.class, () -> {
            firstBowl.play();
        });
    }

    @Test
    public void testOpponentsBowlIsNotPlayable() {
        Bowl firstBowl = new PlayableBowl();
        PlayableBowl bowl = (PlayableBowl) firstBowl.getBowlAtDistance(7);
        assertThrows(IllegalStateException.class, () -> {
            bowl.play();
        });
    }

    @Test
    public void testMoveDoesNotSkipMyKalaha() {
        Bowl firstBowl = new PlayableBowl();
        PlayableBowl bowl = (PlayableBowl) firstBowl.getBowlAtDistance(5);
        Bowl bowlToInspect = firstBowl.getBowlAtDistance(6);
        bowl.play();
        assertEquals(1, bowlToInspect.getNumberOfStones());
    }

    @Test
    public void testMoveSkipsOpponentsKalaha() {
        PlayableBowl firstBowl = new PlayableBowl(new int[] { 15, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0 });
        Bowl bowl = firstBowl.getBowlAtDistance(13);
        firstBowl.play();
        assertEquals(0, bowl.getNumberOfStones());
    }

    @Test
    public void testMoveCanGoRound() {
        PlayableBowl firstBowl = new PlayableBowl(new int[] { 15, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0 });
        firstBowl.play();
        assertEquals(1, firstBowl.getNumberOfStones());
    }

    @Test
    public void testAfterMoveTheTurnIsSwitched() {
        PlayableBowl firstBowl = new PlayableBowl();
        firstBowl.play();
        assertFalse(firstBowl.getOwner().hasTurn());
    }

    @Test
    public void testMoveEndingInKalahaDoesNotSwitchTurn() {
        Bowl firstBowl = new PlayableBowl();
        PlayableBowl bowl = (PlayableBowl) firstBowl.getBowlAtDistance(2);
        bowl.play();
        assertTrue(firstBowl.getOwner().hasTurn());
    }

    @Test
    public void testStealing() {
        Bowl firstBowl = new PlayableBowl(new int[] { 4, 4, 4, 4, 1, 0, 0, 4, 4, 4, 4, 4, 4, 0 });
        PlayableBowl bowl = (PlayableBowl) firstBowl.getBowlAtDistance(4);
        bowl.play();
        assertEquals(0, firstBowl.getBowlAtDistance(5).getNumberOfStones());
        assertEquals(5, firstBowl.getBowlAtDistance(6).getNumberOfStones());
        assertEquals(0, firstBowl.getBowlAtDistance(7).getNumberOfStones());
    }

    @Test
    public void testStealingDoesNotHappenOnOpponentsSide() {
        Bowl firstBowl = new PlayableBowl(new int[] { 4, 4, 4, 4, 3, 4, 0, 0, 4, 4, 4, 4, 4, 0 });
        PlayableBowl bowl = (PlayableBowl) firstBowl.getBowlAtDistance(4);
        bowl.play();
        assertEquals(5, firstBowl.getBowlAtDistance(5).getNumberOfStones());
        assertEquals(1, firstBowl.getBowlAtDistance(6).getNumberOfStones());
        assertEquals(1, firstBowl.getBowlAtDistance(7).getNumberOfStones());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5 })
    public void testAtGameOverAllBowlsAreEmptied(int index) {
        PlayableBowl firstBowl = new PlayableBowl(new int[] { 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0 });
        Bowl bowl = firstBowl.getBowlAtDistance(index);
        firstBowl.play();
        assertEquals(0, bowl.getNumberOfStones());
    }

    @Test
    public void testAtGameOverAllStonesGoToKalaha() {
        PlayableBowl firstBowl = new PlayableBowl(new int[] { 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0 });
        Bowl kalaha = firstBowl.getBowlAtDistance(6);
        firstBowl.play();
        assertEquals(24, kalaha.getNumberOfStones());
    }

    @Test
    public void testBoardIsNotCleanedIfGameIsNotOver() {
        Bowl firstBowl = new PlayableBowl(new int[] { 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0 });
        PlayableBowl bowl = (PlayableBowl) firstBowl.getBowlAtDistance(2);
        bowl.play();
        assertEquals(4, firstBowl.getNumberOfStones());
    }

    @Test
    public void testAtGameOverNoneHasTurn() {
        PlayableBowl firstBowl = new PlayableBowl(new int[] { 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0 });
        firstBowl.play();
        assertFalse(firstBowl.getOwner().hasTurn());
        assertFalse(firstBowl.getOwner().getOpponent().hasTurn());
    }

}
