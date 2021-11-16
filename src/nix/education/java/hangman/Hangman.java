package nix.education.java.hangman;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    static Random rand;
    public void greetings(){
        System.out.println("HANGMAN");
        System.out.println("The game will be available soon.");
    }
    public void hangmanLite(){
        String reply = "java";
        Scanner in = new Scanner(System.in);
        String string = in.nextLine();
        if (Objects.equals(string, reply)){
            System.out.println("You survived!");
        } else{
            System.out.println("You lost!");
        }
    }
    public void hangmanLetterPlus(String randAnswer){
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        if (Objects.equals(answer, randAnswer)){
            System.out.println("You survived!");
        } else{
            System.out.println("You lost!");
        }
    }
    public void openTwoLetters (String randAnswer){
        Scanner in = new Scanner(System.in);
        char[] sl1 = randAnswer.toCharArray();
        for (var i = 2; i < sl1.length;i++) {
            sl1[i] = '-';
        }
        String rdAnswer = String.valueOf(sl1);
        System.out.println("HANGMAN");
        System.out.println("Guess the word:"+rdAnswer);
        String answerPerson = in.nextLine();
        if (Objects.equals(answerPerson, randAnswer)){
            System.out.println("You survived!");
        } else{
            System.out.println("You lost!");
        }
    }
    public String randomLetters(){
        ArrayList<String> ArrLst = new ArrayList<>();
        ArrLst.add("python");
        ArrLst.add("java");
        ArrLst.add("javascript");
        ArrLst.add("kotlin");
        rand = new Random();
        String wordAnswer = ArrLst.get(rand.nextInt(ArrLst.size()));
        return  wordAnswer;
    }
}
