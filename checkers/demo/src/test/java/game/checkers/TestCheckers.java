package game.checkers;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
    void testValidateUserInputBelowOne() {
        Checkers game = new Checkers();
        int[] position = {0,0};
        String belowOneExceptMessage = "The input \"0\" is invalid";
        InvalidPositionException belowOneException = assertThrows(InvalidPositionException.class, () -> game.validateUserInput(position));
        System.out.println(belowOneException.getMessage());
        assertEquals(belowOneExceptMessage, belowOneException.getMessage());
    }

    @Test
    void testValidateUserInputAboveEight() {
        Checkers game = new Checkers();
        int[] position = {9,9};
        String aboveEightExceptMessage = "The input \"9\" is invalid";
        InvalidPositionException aboveEightException = assertThrows(InvalidPositionException.class, () -> {
            game.validateUserInput(position);
        });
        assertEquals(aboveEightExceptMessage, aboveEightException.getMessage());
    }

    @Test
    void testGetPieceInput() throws InvalidPositionException {
        Checkers game = new Checkers();
        ByteArrayInputStream simulatedInputStream = new ByteArrayInputStream("2 2".getBytes());
        Scanner scanner = new Scanner(simulatedInputStream);
        int[] result = game.getPieceInput(scanner);
        assertArrayEquals(new int[] {2,2}, result);
    }

    @Test
    void testGetPieceReturnWhitePiece() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        /*
         * We use reflection to check if the getPiece method 
         * actually returns a Piece object.
         */
        Checkers game = new Checkers();
        int[] position = {0,0};
        Piece piece = game.getPiece(position);
        /*
         * The generic is a wildcard that extends the Checkers
         * class as the return value of getClass is
         * Class<? extends class>
         */
        Class<? extends Checkers> gameClass = game.getClass();

        // Get the createBoard method and set that to accessible
        // or not private.
        Method gameCreateBoard = gameClass.getDeclaredMethod("createBoard");
        gameCreateBoard.setAccessible(true);

        // We invoke the method to get the board, get the piece, and
        // use the implemented isEqual to check if they are the same
        // WhitePiece Object.
        Piece[][] gameBoard = (Piece[][])gameCreateBoard.invoke(game);
        Piece actualPiece = gameBoard[0][0];
        assertTrue(actualPiece.isEqual(piece));
    }

    @Test
    void testGetPieceReturnBlackPiece() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Checkers game = new Checkers();
        int[] position = {7,3};
        Piece piece = game.getPiece(position);
        Class<? extends Checkers> gameClass = game.getClass();
        Method gameCreateBoard = gameClass.getDeclaredMethod("createBoard");
        gameCreateBoard.setAccessible(true);
        Piece[][] gameBoard = (Piece[][]) gameCreateBoard.invoke(game);
        Piece actualPiece = gameBoard[7][3];
        assertTrue(actualPiece.isEqual(piece));

    }
}
