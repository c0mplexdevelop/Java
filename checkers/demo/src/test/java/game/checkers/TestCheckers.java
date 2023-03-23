package game.checkers;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for simple App.
 */
class TestCheckers {
    /**
     * Rigorous Test.
     */
    @Test
    void testCheckIfPositionIsEmpty() {
        Checkers game = new Checkers();
        assertTrue(game.checkIfPositionIsEmpty(3,0)); // Row 3 is the fourth row, which no piece should be at the beginning
        assertFalse(game.checkIfPositionIsEmpty(0,0)); // Top left, should be a white piece.
    }

    @Test
    void testValidateUserInput() {
        Checkers game = new Checkers();
        String belowOneExceptMessage = "The input \"0\" is invalid";
        InvalidPositionException belowOneException = assertThrows(InvalidPositionException.class, () -> game.validateUserInput(0));
        System.out.println(belowOneException.getMessage());
        assertEquals(belowOneExceptMessage, belowOneException.getMessage());
    }

    @Test
    void testValidateUserInputAboveEight() {
        Checkers game = new Checkers();
        String aboveEightExceptMessage = "The input \"9\" is invalid";
        InvalidPositionException aboveEightException = assertThrows(InvalidPositionException.class, () -> {
            game.validateUserInput(9);
        });
        assertEquals(aboveEightExceptMessage, aboveEightException.getMessage());
    }

    @Test
    void testGetInput() {
        Checkers game = new Checkers();
        /*
         * We backup the current Sys.in, then we would set the ByteIS as System.in
         * to simulate user Input, then restore the original Sys in
         */
        ByteArrayInputStream simulatedInputStream = new ByteArrayInputStream("2 2".getBytes());
        Scanner scanner = new Scanner(simulatedInputStream);
        

        scanner.tokens().forEach(System.out::println);
    }
}
