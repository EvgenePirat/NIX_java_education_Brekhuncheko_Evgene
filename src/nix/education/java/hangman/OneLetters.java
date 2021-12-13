package nix.education.java.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class OneLetters {
    boolean e,g = true;
    int n = 0,attempt=8;
    static Scanner in;
    String answer;
    char[] randAnswer,userResponse;
    char lastLetter = ' ';
    public void openOneLetters() {
        ArrayList<String> ArrLst = new ArrayList<>();
        ArrLst.add("python");
        ArrLst.add("java");
        ArrLst.add("javascript");
        ArrLst.add("kotlin");
        Random rand = new Random();
        in = new Scanner(System.in);
        while (g) {
            System.out.println("Введите <play>, чтобы начать игру, <exit>, чтобы выйти:");
            String action = in.next();
            answer = ArrLst.get(rand.nextInt(ArrLst.size()));
            randAnswer = answer.toCharArray();
            userResponse = answer.toCharArray();
            for (var i = 0; i < userResponse.length; i++) {
                randAnswer[i] = '-';
            }
            action(action);
        }
    }
    private void oneAttempt (){
        System.out.println("Введите слово целиком: ");
        String fullWord = in.next();
        if (fullWord.equals(answer)) {
            System.out.println("Вы победили!");
            e = false;
            n = 1;
        } else {
            System.out.println("Вы не угадали слово");
            e = false;
            n = 1;
        }
    }
    private void letterCheck (){
        System.out.println("Эта буква не встречается в слове");
        attempt = attempt - 1;
        System.out.println("У вас осталось " + attempt + " попыток");
        n = 0;
    }
    private void winHangman (){
        System.out.println(randAnswer);
        System.out.println("Вы победили!");
        e = false;
    }
    private void action (String action){
        switch (action) {
            case "play":
                startGame();
            case "exit":
                g = false;
                break;
        }
    }
    private void startGame () {
        e = true;
        while (e) {
            System.out.println(randAnswer);
            if (attempt == 1) {
                oneAttempt();
                break;
            }
            System.out.println("Input a letter:");
            String userLetter = in.next();
            if (checkString(userLetter) == false){
                continue;
            }
            char letter = userLetter.charAt(0);
            if (checkLetter(letter) == false){
                continue;
            }
            openLetter(letter);
            n = checkLast(letter,n);
            lastLetter = letter;
            if (attempt == 0) {
                break;
            }
            if (n == 0) {
                letterCheck();
            } else {
                n = 0;
            }
        }
        System.out.println("Спасибо за игру");
    }
    private boolean checkString(String userLetter){
        if (userLetter.length() > 1) {
            System.out.println("Вы должны ввести одну букву");
            return false;
        }
        return true;
    }
    private boolean checkLetter(char letter){
        char Letter = Character.toUpperCase(letter);
        if (letter == Letter) {
            System.out.println("Введите строчную букву");
            return false;
        }
        return true;
    }
    private void openLetter(char letter){
        for (var i = 0; i < userResponse.length; i++) {
            if (userResponse[i] == letter) {
                if (randAnswer[i] != '-') {
                    System.out.println("Вы уже отгадали эту букву");
                    n = 1;
                    lastLetter = ' ';
                    break;
                }
                randAnswer[i] = letter;
                n = n + 1;
            }
            if (Arrays.equals(randAnswer, userResponse)) {
                winHangman();
                break;
            }
        }
    }
    private int checkLast(char letter, int n){
        if (lastLetter == letter) {
            System.out.println("Вы уже пытались открыть эту букву в прошлый раз");
            return 1;
        }
        return n;
    }
}
