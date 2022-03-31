import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameResultTest {
    GameResult gameResult = new GameResult();

    @ParameterizedTest
    @MethodSource("playerProvider")
    void WinnerTest1(Player player) {
        gameResult.setWinner(player);
        assertEquals(player, gameResult.getWinner());
    }

    static Stream<Player> playerProvider() {
        return Stream.of(new Player(0, null, ""),
                new Player(15, "_&0981?", "127"),
                new Player(18000000, "Котик", "}"));
    }

}