package com.with_ai;

import java.util.Scanner;

class TicTacToe {
    static int turns = 0; // instance variable to allow access in the entire class for refactoring

    public static void main(String[] args) throws Exception {
        char currentTurn = 'X';
        char[] board = {
             ' ', ' ', ' ',
             ' ', ' ', ' ',
             ' ', ' ', ' ' 
            };

        while (turns < 9) {
            showBoard(board);

            if(currentTurn == 'X') {
                int playerPosition = playerInput(board, currentTurn);
                if (!checkBoardIfEmpty(board, playerPosition)) {
                    System.out.printf("Taken by %s\n", board[playerPosition]);
                    continue;
                }
                placePlayerInput(board, playerPosition, currentTurn);
            } else {
                placeAIInput(board, currentTurn);
            }

            if (checkIfWon(board, currentTurn)) {
                showBoard(board);
                System.out.printf("%s WON!\n", currentTurn);
                return; // Stops the while loop while preventing the draw function to start.
            }

            if (currentTurn == 'X') {
                currentTurn = 'O';
            } else {
                currentTurn = 'X';
            }

            turns++;

        }

        showBoard(board); // should only happen IF and ONLY IF there is no turns left.
        System.out.println("Its a draw!");
    }

    private static int playerInput(char[] board, char currentTurn) {
            Scanner input = new Scanner(System.in);
            int position = 0;
            if (input.hasNextInt()) // Check for ints
            {
                position = input.nextInt() - 1; // subtract by 1 to get a valid position
            } else {
                input.next(); // remove the next invalid token
            }

            if(checkIfWon(board, currentTurn) || turns > 8) { // close the scanner if someone already won
                input.close();
            }

            return position;
            

    }

    public static boolean checkBoardIfEmpty(char[] board, int position) {
        if (board[position] != ' ') {
            return false;
        }

        return true;
    }

    private static void placePlayerInput(char[] board, int position, char currentTurn) {
        board[position] = currentTurn;
    }

    private static void placeAIInput(char[] board, char currentTurn) {
        int bestScore = -100000; // sets initial beginning score for AI to overcome in its move
        int bestMove = 0;   // best move later

        for (int board_position = 0; board_position < board.length; board_position++) {
            if(board[board_position] == ' ') {
                board[board_position] = currentTurn;    // sets the board positionfor ai letter
                int score = minimax(board, currentTurn, false);
                board[board_position] = ' '; // reverses it since we dont want it just yet

                if(score > bestScore) {
                    bestScore = score; // new score to overcome
                    bestMove = board_position; // best move in this iteration
                }
            }
        }

        board[bestMove] = currentTurn; // we assign the move finally
    }

    private static int minimax(char[] board, char computerLetter, boolean isMaximizing) {
        char playerLetter;

        if(computerLetter == 'X') {
            playerLetter = 'O';
        } else {
            playerLetter = 'X';
        }

        if(checkIfWon(board,computerLetter)) {
            return 10000;
        } else if(checkIfWon(board, playerLetter)) {
            return -10000;
        } else if(checkForDraw(board)) {
            return 0;
        }

        if(isMaximizing) {
            int bestScore = -1000; // sets initial beginning score for AI to overcome in its move

            for (int board_position = 0; board_position < board.length; board_position++) {
                if (board[board_position] == ' ') {
                    board[board_position] = computerLetter; // sets the board positionfor ai letter
                    int score = minimax(board, computerLetter, false);
                    board[board_position] = ' '; // reverses it since we dont want it just yet

                    if (score > bestScore) {
                        bestScore = score; // new score to overcome
                    }
                }
            }
            return bestScore;   // change best score to be the best score

        } else {
            int bestScore = 1000; // sets initial beginning score for AI to overcome in its move

            for (int board_position = 0; board_position < board.length; board_position++) {
                if (board[board_position] == ' ') {
                    board[board_position] = playerLetter; // sets the board positionfor ai letter
                    int score = minimax(board, computerLetter, true);
                    board[board_position] = ' '; // reverses it since we dont want it just yet

                    if (score < bestScore) {
                        bestScore = score; // new score to overcome
                    }
                }
            }

            return bestScore; // change best score to be the best score
        }
    }

    public static boolean checkForDraw(char[] board) {
        for(char position : board) {
            if(position == ' ') {
                return false;
            }
        }

        return true;
    }

    private static boolean checkIfWon(char[] board, char currentTurn) {
        int[][] winningConditions = { { 6, 7, 8 }, { 3, 4, 5 }, { 0, 1, 2 }, // rows
                { 6, 3, 0 }, { 7, 4, 1 }, { 8, 5, 2 }, // cols
                { 6, 4, 2 }, { 8, 4, 0 } }; // diags

        for (int[] conditions : winningConditions) {
            if (board[conditions[0]] == (board[conditions[1]]) &&
                    board[conditions[1]] == board[conditions[2]] &&
                    board[conditions[2]] == currentTurn) {
                return true;
            }
        }

        return false;
    }

    private static void showBoard(char[] board) {
        System.out.printf(" %s | %s | %s\n", board[6], board[7], board[8]);
        System.out.printf(" %s | %s | %s\n", board[3], board[4], board[5]);
        System.out.printf(" %s | %s | %s\n", board[0], board[1], board[2]);
    }
}
