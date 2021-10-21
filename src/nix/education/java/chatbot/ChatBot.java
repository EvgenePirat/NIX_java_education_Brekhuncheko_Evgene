package nix.education.java.chatbot;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        System.out.println("Hello! My name is AnastasiaBot.");
        System.out.println("I was created in 2002.");
        System.out.println("Please, remind me your name.");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        System.out.println("What a great name you have, " + name + "!");
    }
}
