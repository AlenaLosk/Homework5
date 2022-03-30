import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {
    @JsonProperty("_num")
    private int num;
    @JsonProperty("_playerId")
    private int playerId;
    @JsonProperty("_text")
    private int cell;

    public Step() {
    }
    public Step(int num, int playerId, int cell) {
        this.num = num;
        this.playerId = playerId;
        this.cell = cell;
    }

    public Step(int playerId, int cell) {
        this.num = 0;
        this.playerId = playerId;
        this.cell = cell;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
