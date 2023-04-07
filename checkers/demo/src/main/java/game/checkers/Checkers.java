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
        System.out.println(Arrays.deepToString(game.board));
        while(true) {
            Piece piece = game.handlePieceInput(scanner);
            piece.moveDirection("left");
            break;
        }

        game.showBoard();
        
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

    protected final boolean checkIfPositionIsEmpty(int row, int col) {
        if(board[row][col] == null) {
            return true;
        };

        return false;
    }

    public final Piece getPiece(int[] position) {
        int row = position[0];
        int col = position[1];

        return board[row][col];
    }

    public final boolean checkIfMovable(int pieceRow, int pieceCol) {
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

    public int[] getPieceInput(Scanner scanner) {
        System.out.print("Input your row: ");
        int inputtedRow = scanner.nextInt();

        System.out.print("Input your col: ");
        int inputtedCol = scanner.nextInt();

        return new int[] {inputtedRow, inputtedCol};
    }

    public Piece handlePieceInput(Scanner scanner) {
        Piece piece = null;
        boolean validPosition = false;
        while(!validPosition) {
            showBoard();
            int[] position = getPieceInput(scanner);
            try {
                validateUserInput(position);
            } catch (InvalidPositionException exception) {
                System.out.println(exception.getMessage());
                continue;
            }
            
            position = turnInputToArrayIndex(position);
            System.out.println(Arrays.toString(position));
            piece = getPiece(position);
            validPosition = true;
        }
        
        return piece;
    }

    protected final void validateUserInput(int[] input) throws InvalidPositionException {
        for(int position : input) {
            if(position < 1 || position > 8) {
                throw new InvalidPositionException(String.format("The input \"%d\" is invalid", position));
            }
        }
    }

    private int[] turnInputToArrayIndex(int[] position) {
        int[] convertedPosition = new int[2];
        for(int idx = 0; idx < position.length; idx++) {
            convertedPosition[idx] = position[idx] - 1;
        }

        return convertedPosition;
    }
}




