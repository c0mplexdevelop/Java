package game.checkers;

public class InvalidPositionException extends Exception {
    public InvalidPositionException(String message) {
        super(message);
    }
}
