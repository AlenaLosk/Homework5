import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gameplay {
    @JsonIgnore
    private Model model;
    @JsonProperty("Player")
    private Player[] players;

    @JsonProperty("Game")
    private Steps steps;
    @JsonProperty("GameResult")
    private GameResult result;
    @JsonIgnore
    private int stepCounter;
    @JsonIgnore
    private static Logger log = LoggerFactory.getLogger(Gameplay.class);
    @JsonIgnore
    private Player currentPlayer;

    public Gameplay() {
        steps = new Steps(new ArrayList<Step>());
        result = new GameResult();
        stepCounter = 1;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
        setCurrentPlayer(players[0]);
    }

    public Steps getSteps() {
        return steps;
    }

    public void setSteps(Steps steps) {
        this.steps = steps;
    }

    public GameResult getResult() {
        return result;
    }

    public void setResult(GameResult result) {
        this.result = result;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Player process(Player[] players, boolean isConsoleMode) {
        setPlayers(players);
        this.model = new Model();
        steps = new Steps(new ArrayList<Step>());
        while (model.hasFreeCell(model.getGameField())) {
            Step step = model.putSymbol(currentPlayer, isConsoleMode);
            step.setNum(stepCounter++);
            steps.getSteps().add(step);
            View.refresh(model.getGameField());
            if (model.isWin(currentPlayer)) {
                result.setWinner(currentPlayer);
                break;
            }
            currentPlayer = changePlayer(currentPlayer);
        }
        return result.getWinner();
    }

    public Player process(Player player, int cell) throws Exception {
        if (model == null) {
            model = new Model();
        }
        if (model.hasFreeCell(model.getGameField())) {
            if (this.currentPlayer.equals(player)) {
                Step step = model.putSymbol(currentPlayer, cell);
                if (step != null) {
                    step.setNum(stepCounter++);
                    steps.getSteps().add(step);
                    if (model.isWin(currentPlayer)) {
                        result.setWinner(currentPlayer);
                    }
                    currentPlayer = changePlayer(currentPlayer);
                }
            } else {
                throw new Exception("This is a step for another player!");
            }
        } else {
            throw new Exception("There is no free cell!");
        }
        return result.getWinner();
    }

    public Player changePlayer(Player player) {
        if (player.equals(players[0])) {
            return players[1];
        } else {
            return players[0];
        }
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }
}
