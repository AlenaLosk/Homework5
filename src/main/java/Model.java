import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Model {
    private static Logger logger = LoggerFactory.getLogger(Model.class);
    private String[][] gameField = new String[3][3];
    private final int GAME_CELLS = 9;

    public Model() {
        gameField = Arrays.stream(gameField).map(s -> Arrays.stream(s).map(e -> "-").toArray(String[]::new)).toArray(String[][]::new);
    }

    public Step putSymbol(Player player, boolean isConsoleMode) {
        Step step = null;
        if (isConsoleMode) {
            int x = -1;
            ConsoleHelper.printMessage(String.format("%dst player is moving", player.getId()), true);
            while (x < 0) {
                ConsoleHelper.printMessage(String.format("Enter the number of the cell (from 1 to %s): ", GAME_CELLS));
                try {
                    x = Integer.parseInt(ConsoleHelper.readMessage()) - 1;
                    if (x < 0 || x > 8) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    x = -1;
                    ConsoleHelper.printMessage("This cell doesn't exist!", true);
                }
            }
            if (gameField[x / 3][x % 3].equals("-")) {
                gameField[x / 3][x % 3] = player.getSymbol();
                step = new Step(player.getId(), x + 1);
            } else {
                ConsoleHelper.printMessage("This cell isn't free! Please, try again!", true);
                step = putSymbol(player, isConsoleMode);
            }
        }
        return step;
    }

    public Step putSymbol(Player player, int cell) throws Exception {
        int x = 0;
        Step step = null;
        try {
            x = cell - 1;
            if (x < 0 || x > 8) {
                throw new NumberFormatException();
            }
            if (gameField[x / 3][x % 3].equals("-")) {
                gameField[x / 3][x % 3] = player.getSymbol();
                step = new Step(player.getId(), x + 1);
            } else {
                throw new Exception("This cell isn't free! Please, try again!");
            }
        } catch (NumberFormatException ex) {
            throw new Exception("This cell doesn't exist!");
        }
        return step;
    }

    public boolean hasFreeCell(String[][] gameField) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j].equals("-")) return true;
            }
        }
        return false;
    }

    public boolean isWin(Player player) {
        String symbol = player.getSymbol();
        for (int i = 0; i < 3; i++) {
            if ((gameField[i][0].equals(symbol) && gameField[i][1].equals(symbol) && gameField[i][2].equals(symbol)) ||
                    (gameField[0][i].equals(symbol) && gameField[1][i].equals(symbol) && gameField[2][i].equals(symbol))) {
                return true;
            }

            if (gameField[0][0].equals(symbol) && gameField[1][1].equals(symbol) && gameField[2][2].equals(symbol))
                return true;
            if (gameField[0][2].equals(symbol) && gameField[1][1].equals(symbol) && gameField[2][0].equals(symbol))
                return true;
        }
        return false;
    }

    public String[][] getGameField() {
        return gameField;
    }
}
