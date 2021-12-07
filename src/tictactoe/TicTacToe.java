package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        playTicTacToe();
    }
    public static void playTicTacToe(){
        boolean a = true;
        System.out.println("X O X");
        System.out.println("O O X");
        System.out.println("X X O");
        System.out.println("");
        while (a) {
            boolean q = true;
            FullGame FUllGAME = new FullGame();
            char[][] fieldGame = FUllGAME.createdField();
            System.out.println("Game Start");
            while (q) {
                q = FUllGAME.gameTicTacToe(fieldGame);
            }
            System.out.println("Thanks for playing");
            a = false;
        }
    }
}


