package tictactoe;

import java.util.Scanner;

public class FullGame {
    public int move = 1;
    public int _number=0;
    public char[][] createdField () {
        char[][] gameField = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        printFieldGame(gameField);
        return gameField;
    }
    private void printFieldGame(char[][] gameField){
        System.out.print("--------- ");
        for (int i = 0; i < gameField.length; ++i) {
            System.out.println("\t");
            for (int j = 0; j < gameField[i].length; ++j) {
                if (j == 0) {
                    System.out.print("|");
                }
                System.out.print(gameField[i][j]);
                if (j != 2) {
                    System.out.print("\t");
                }
                if (j == 2) {
                    System.out.print("|");
                }
            }
        }
        System.out.println("");
        System.out.print("--------- ");
        System.out.println("");
    }
    public boolean gameTicTacToe(char[][] gameField) {
        try{
            if (checkWin(gameField)){
                Scanner in = new Scanner(System.in);
                System.out.println("Enter the coordinates");
                if (move == 1){
                    System.out.print("move X: ");
                }
                else {
                    System.out.print("move O: ");
                }
                int coordinates = in.nextInt();
                openField(coordinates, gameField);
            }
            else {
                return false;
            }
        } catch (Exception exc){
            System.out.println("You should enter numbers!");
        }
        return true;
    }
    private void openField(int coordinates, char[][] gameField){
        while (true) {
            int coordinateString = coordinates / 10;
            int coordinateColumn = coordinates % 10;
            if ((coordinateString) >= 1 && (coordinateString) <= 3 && (coordinateColumn) >= 1 && (coordinateColumn) <= 3) {
                if (gameField[(coordinateString-1)][(coordinateColumn-1)] != 'X' && gameField[(coordinateString-1)][(coordinateColumn-1)] != 'O'){
                    if (checkWin(gameField)){
                        if (move == 1){
                            gameField[(coordinateString-1)][(coordinateColumn-1)] = 'X';
                            printFieldGame(gameField);
                            move -= 1;
                            break;
                        }
                        if (move == 0){
                            gameField[(coordinateString-1)][(coordinateColumn-1)] = 'O';
                            printFieldGame(gameField);
                            move += 1;
                            break;
                        }
                    }
                }
                else {
                    System.out.println("This cell is occupied! Choose another one!");
                    break;
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                break;
            }
        }
    }
    private boolean checkWin (char[][] gameField){
        for (int i = 0; i < gameField.length; ++i) {
            for (int j = 0; j < gameField[i].length; ++j) {
                if (gameField[i][j] == ' '){
                    _number++;
                }
            }
        }
        if (_number == 0){
            System.out.println("Draw");
            return false;
        }
        _number = 0;
        if (winHorizontal(gameField) == true){
            return false;
        }
        if (winVertical(gameField) == true){
            return false;
        }
        if (winDiagonal(gameField)==true){
            return false;
        }
        return true;
    }
    private boolean winDiagonal(char[][] gameField){
        if (gameField[0][0] == gameField[1][1] && gameField[1][1]==gameField[2][2]){
            if (winWhy(gameField, 0, 0)){
                return true;
            }
        }
        if (gameField[0][2] == gameField[1][1]&&gameField[1][1]==gameField[2][0]){
            if (winWhy(gameField, 0, 2)){
                return true;
            }
        }
        return false;
    }
    private boolean winHorizontal(char[][] gameField){
        if (gameField[0][0] == gameField[0][1] && gameField[0][1] == gameField[0][2]){
            if (winWhy(gameField,0,0)){
                return true;
            }
        }
        if (gameField[1][0] == gameField[1][1] && gameField[1][1] == gameField[1][2]) {
            if (winWhy(gameField, 1, 0)){
                return true;
            }
        }
        if (gameField[2][0] == gameField[2][1] && gameField[2][1] == gameField[2][2]) {
            if (winWhy(gameField, 2, 0)){
                return true;
            }
        }
        return false;
    }
    private boolean winVertical(char[][] gameField){
        if (gameField[0][0] == gameField[1][0] && gameField[1][0] == gameField[2][0]){
            if (winWhy(gameField, 0, 0)){
                return true;
            }
        }
        if (gameField[0][1] == gameField[1][1] && gameField[1][1] == gameField[2][1]){
            if (winWhy(gameField, 0, 1)){
                return true;
            }
        }
        if (gameField[0][2] == gameField[1][2] && gameField[1][2] == gameField[2][2]){
            if (winWhy(gameField, 0, 2)){
                return true;
            }
        }
        return false;
    }
    private boolean winWhy(char[][] gameField,int i, int j){
        if (gameField[i][j] == 'X'){
            System.out.println("X wins");
            return true;
        }
        if (gameField[i][j] == 'O'){
            System.out.println("O wins");
            return true;
        }
        return false;
    }
}
