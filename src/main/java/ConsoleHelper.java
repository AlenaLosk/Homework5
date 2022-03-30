import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    public static void printMessage(String message, boolean ... hasLineBreak) {
        System.out.print(message);
        if (hasLineBreak.length != 0 && hasLineBreak[0]) {
            System.out.println();
        }
    }

    public static String readMessage() {
        String result = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
