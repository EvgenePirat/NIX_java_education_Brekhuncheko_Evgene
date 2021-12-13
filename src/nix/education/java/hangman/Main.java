package nix.education.java.hangman;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        playHangman();
    }
    public static void playHangman (){
        Hangman Hangman = new Hangman();
        Hangman.greetings();
        System.out.println("HANGMAN");
        System.out.println("Guess the word: java");
        Hangman.hangmanLite();
        Scanner in = new Scanner(System.in);
        String wordAnswerOne = Hangman.randomLetters();
        System.out.println("HANGMAN");
        System.out.println("Guess the word:"+wordAnswerOne);
        Hangman.hangmanLetterPlus(wordAnswerOne);
        String wordAnswerTwo = Hangman.randomLetters();
        Hangman.openTwoLetters(wordAnswerTwo);
        OneLetters oneletters = new OneLetters();
        oneletters.openOneLetters();
    }

}
