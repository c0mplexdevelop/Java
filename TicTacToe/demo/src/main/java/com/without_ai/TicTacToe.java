package com.without_ai;

import java.util.Scanner;

public class TicTacToe {
    static String[][] board = create_board();
    public static void main(String[] args)
    {
        String current_turn = "X";
        Scanner input = new Scanner(System.in);
        while(!check_for_draw())
        {
            System.out.println(show_board());
            player_input(input, current_turn);
            if(check_for_win(board)){
                break;
            }
            if(current_turn == "X") {
                current_turn = "O";
            } else {
                current_turn = "X";
            }
        }

        System.out.println(show_board());
    
    }


    private static String[][] create_board()
    {
        String[] first_row = {" ", " ", " "};
        String[] second_row = {" ", " ", " "};
        String[] third_row = {" ", " ", " "};
        
        String[][] board = {first_row, second_row, third_row};

        return board;
    }

    public static boolean check_for_draw()
    {
        for(String[] row : board)
        {
            for(String value : row)
            {
                if(value == " ")
                {
                    return false;
                }
            }
        }
    
        return true;
    }

    public static boolean check_for_win(String[][] board)
    {
        //Check the rows for winning conditions:
        // 7 8 9
        // 4 5 6
        // 1 2 3
        for(String[] row : board){
            if(row[0].equals(row[1]) && row[1].equals(row[2]) && row[2] != " "){
                return true;
            }
        }

        //Check the columns for winning conditions
        for(int col = 0; col < 2; col++){
            if(board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col]) && board[2][col] != " ") {
                return true;
            }
        }

        //Check the left diagonal for winning condition
        if(board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[2][2] != " ") {
            return true;
        }

        //Check the right diagonal for winning condition
        if(board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[2][0] != " ") {
            return true;
        }

        return false;
    }

    private static void player_input(Scanner input, String player_letter) 
    {
        int position = 0;
        while(true)
        {
            if (input.hasNextInt()) {
                position = input.nextInt() - 1;
            } else {
                System.out.println("Invalid");
                input.next();
                continue;
            }
            
            int row = position / 3;
            int col = position % 3;
            if(check_if_empty(row, col)) {
                board[row][col] = player_letter;
                break;
            }
        }  
    }

    private static boolean check_if_empty(int row, int position)
    {
        if(board[row][position].equals(" ")){
            return true;
        }
        System.out.printf("Invalid positon, occupied by %s \n", board[row][position]);
        return false;
    }

    public static String show_board(){
        String visual_board = "";
        for(int row = 2; row >= 0; row--){
            for(int col = 0; col < 3; col++){
                if(col != 2){
                    visual_board += String.format(" %s |", board[row][col]);
                    continue;
                } else {
                    visual_board += String.format(" %s \n", board[row][col]);
                }
                
            }
        }
        // String board = String.format(
        //     "%s | %s | %s\n%s | %s | %s\n%s | %s | %s")
        return visual_board;
    }
}
