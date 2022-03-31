import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleUserInterfaceTest {

    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private ByteArrayOutputStream outputStreamCaptor;
    private ByteArrayInputStream inputStreamCaptor;

    @BeforeEach
    public void setUpOutput() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    private void provideInput(String data) {
        inputStreamCaptor = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStreamCaptor);
    }

    private String getOutput() {
        return outputStreamCaptor.toString();
    }
    @Test
    void test1() {
        String step1 = "3";
        provideInput(step1);
        ConsoleUserInterface.start();
        String expeted = "Welcome to game 'TicTacToe' menu!" + System.lineSeparator() +
                "Enter '1', if you want to play TicTacToe," + System.lineSeparator() +
                "Enter '2' - to see previous game" + System.lineSeparator() +
                "Enter '3', if you want to exit: Menu is closed.";
        assertEquals(expeted, getOutput());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

}