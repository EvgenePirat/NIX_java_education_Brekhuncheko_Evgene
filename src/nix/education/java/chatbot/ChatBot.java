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
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3,5 and 7");
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int n3 = in.nextInt();
        int age = (n1 * 70 + n2 * 21 + n3 * 15) % 105;
        System.out.println("Your age is " + age + "; that`s a good time to start programming!");
        System.out.println("Now I will prove to you that I can count to any number you want.");
        int n = in.nextInt();
        for (int i = 0; i <= n; i++) {
            System.out.println(i + " !");
        }
        System.out.println("Сколько бит в двух байтах");
        System.out.println("1) 4");
        System.out.println("2) 8");
        System.out.println("3) 16");
        System.out.println("4) 32");
        for (; ; ) {
            System.out.print("Введите ответ: ");
            int o = in.nextInt();
            if (o == 1) {
                System.out.println("Please, try again");
            } else if (o == 2) {
                System.out.println("Please, try again");
            } else if (o == 3) {
                System.out.println("Great.you right");
                break;
            } else if (o == 4) {
                System.out.println("Please, try again");
            }
        }
        System.out.println("Goodbye, have a nice day!");
    }
}
