package game.checkers;

import java.util.Arrays;
import java.util.Scanner;

public class Checkers {

    Piece[][] board = createBoard();

    private static void sprint(Object str) {
        System.out.println(str);
    }


    public void print(Object str) {
        System.out.println(str);
    }

    public static void main(String[] args) throws InvalidPositionException {
        Checkers game = new Checkers();
        Scanner scanner = new Scanner(System.in);
        sprint(Arrays.deepToString(game.board));
        game.showBoard();
        int[] position = game.getInput(scanner);

    }

    private final Piece[][] createBoard() {
        Piece[][] board = new Piece[8][4];

        for (int blackRow = 0; blackRow < 3; blackRow++) {
            for (int blackCol = 0; blackCol < 4; blackCol++) {
                board[blackRow][blackCol] = new BlackPiece(blackRow, blackCol);
            }
        }

        for (int whiteRow = 5; whiteRow < board.length; whiteRow++) {
            for (int whiteCol = 0; whiteCol < 4; whiteCol++) {
                board[whiteRow][whiteCol] = new WhitePiece(whiteRow, whiteCol);
            }
        }

        return board;
    }

    private void showBoard() {
        int count = 0;
        for (int row = 0; row < 8; row++) {
            if (row == 3 || row == 4) {
                System.out.print("|  |  |  |  |  |  |  |  |\n");
                count++;
                continue;
            }

            if (row % 2 != 0) {
                System.out.print(String.format("|  |%s|  |%s|  |%s|  |%s|\n", board[count][0], board[count][1],
                        board[count][2], board[count][3]));
            } else {
                System.out.print(String.format("|%s|  |%s|  |%s|  |%s|  |\n", board[count][0], board[count][1],
                        board[count][2], board[count][3]));
            }

            count++;
        }
    }

    final boolean checkIfPositionIsEmpty(int row, int col) {
        if(board[row][col] == null) {
            return true;
        };

        return false;
    }

    final Piece getPiece(int row, int col) {
        return board[row][col];
    }

    final boolean checkIfMovable(int pieceRow, int pieceCol) {
        for(int checkingRow = pieceRow - 1; checkingRow <= pieceRow + 1 ; checkingRow++) {
            for(int checkingCol = pieceCol - 1; checkingCol < pieceCol + 1; checkingCol++) {
                if(checkingRow == pieceRow) continue;
                // This is to avoid checking undesired rows, as checking the row wouldnt
                // be in the "X" movement pattern of the piece.

                int row = Math.max(checkingRow, 0); // This takes care of corners where row would be -1
                int col = Math.max(checkingCol, 0); // This takes care of corners where col would be -1

                if(row == pieceRow && col == pieceCol) {
                    continue; // Avoid checking the same position
                }

                if(board[row][col] == null) { // position is empty
                    return true;
                }
            }
        }

        return false; // Immovable
    }

    int[] getInput(Scanner scanner) throws InvalidPositionException {
        System.out.print("Input your row: ");
        int inputtedRow = scanner.nextInt();

        System.out.print("Input your col: ");
        int inputtedCol = scanner.nextInt();

        int arrayRow = inputtedRow - 1;
        int arrayCol = inputtedCol - 1;

        return new int[] {arrayRow, arrayCol};
    }

    final void validateUserInput(int[] input) throws InvalidPositionException {
        for(int position : input) {
            if (position < 1 || position > 8) {
                throw new InvalidPositionException(String.format("The input \"%d\" is invalid", input));
            }
        }
    }
}




