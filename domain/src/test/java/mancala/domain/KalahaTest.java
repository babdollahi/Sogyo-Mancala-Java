package mancala.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KalahaTest {

    @ParameterizedTest
    @ValueSource(ints = { 6, 13 })
    public void testDeclareCorrectWinner(int kalahaIndex) {
        Bowl firstBowl = new PlayableBowl(new int[] { 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 18 });
        Kalaha kalaha = (Kalaha) firstBowl.getBowlAtDistance(kalahaIndex);
        Optional<Player> winner = kalaha.whoIsAhead();
        assertEquals(Optional.of(firstBowl.owner), winner);
    }

    @ParameterizedTest
    @ValueSource(ints = { 6, 13 })
    public void testRecognizingADraw(int kalahaIndex) {
        Bowl firstBowl = new PlayableBowl(new int[] { 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 24 });
        Kalaha kalaha = (Kalaha) firstBowl.getBowlAtDistance(kalahaIndex);
        Optional<Player> winner = kalaha.whoIsAhead();
        assertEquals(Optional.empty(), winner);
    }

}
