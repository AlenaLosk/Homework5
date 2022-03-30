import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;

public class JSONWriter implements Writer {
    @Override
    public void write(Game game, String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try (FileOutputStream stream = new FileOutputStream(fileName)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(stream, game);
        } catch (Exception e) {
            ConsoleHelper.printMessage("The file for writing game steps wasn't found!" + System.lineSeparator(), true);
        }
    }

    public String write(Step step) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(step);
    }

    public String write(Player player) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(player);
    }

    public String write(Player[] players) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(players);
    }
}
