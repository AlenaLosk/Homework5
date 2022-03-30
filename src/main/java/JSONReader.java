import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class JSONReader implements Reader {
    private ArrayList<Step> steps = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private String[][] gameField = {{"-", "-", "-"},
            {"-", "-", "-"},
            {"-", "-", "-"}};
    private String status = "Drawn game!";

    private Game read(String file) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Game result = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            result = mapper.readValue(fileInputStream, Game.class);
        } catch (Exception e) {
            ConsoleHelper.printMessage("The file with game steps wasn't found!" + System.lineSeparator(), true);
        }
        return result;
    }

    public String gameToJson(String file) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Game game = read(file);
        return mapper.writeValueAsString(game);
    }

    public String winnerToJson(String file) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Game game = read(file);
        return mapper.writeValueAsString(game.getGameplay().getResult().getWinner());
    }

    @Override
    public void readAndPlay(String file) {
        Game game = read(file);
        if (game != null) {
            players.addAll(Arrays.stream(game.getGameplay().getPlayers()).toList());
            players.add(game.getGameplay().getResult().getWinner());
            steps.addAll(game.getGameplay().getSteps().getSteps());
            String symbol = "-";
            int playerId;
            Step currentStep;
            int currentCell;
            if (players.size() >= 2) {
                System.out.println();
                System.out.printf("Player 1 -> %s as '%s'" +
                        System.lineSeparator(), players.get(0).getName(), players.get(0).getSymbol());
                System.out.printf("Player 2 -> %s as '%s'" +
                        System.lineSeparator(), players.get(1).getName(), players.get(1).getSymbol());
            } else {
                ConsoleHelper.printMessage("The file with game result doesn't include all players!");
            }
            for (int i = 0; i < steps.size(); i++) {
                currentStep = steps.get(i);
                playerId = currentStep.getPlayerId();
                if (playerId == players.get(0).getId()) {
                    symbol = players.get(0).getSymbol();
                }
                if (playerId == players.get(1).getId()) {
                    symbol = players.get(1).getSymbol();
                }
                currentCell = (currentStep.getCell() - 1);
                gameField[currentCell / 3][currentCell % 3] = symbol;
                formatAndPrint(800, gameField);
                System.out.println();
            }
            Player winner = players.get(2);
            if (winner != null) {
                System.out.printf("Player %d -> %s is winner as '%s'!\n", winner.getId(), winner.getName(), winner.getSymbol());
            } else {
                System.out.println(status);
            }
        }
    }
}
