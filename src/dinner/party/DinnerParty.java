package dinner.party;

import java.text.DecimalFormat;
import java.util.*;

public class DinnerParty {
    public HashMap <String,Double> mapParty;
    double check;
    public static void main(String[] args) {
        dinnerParty();
    }
    public static void dinnerParty() {
        Scanner in = new Scanner(System.in);
        DinnerParty Party = new DinnerParty();
        Party.mapParty = new HashMap<>();
        System.out.println("Enter the number of friends joining (including you):");
        try{
            int number = in.nextInt();
            if (number == 0){
                System.out.println("No one is joining for the party");
            }
            else {
                Party.enterName(number,Party);
                System.out.println("Enter the total amount");
                Party.check = in.nextInt();
                System.out.println("Do you want to use the \"Who is lucky?\" feature? Write Yes/No:");
                String answer = in.next();
                Party.lucky(answer,Party, number);
            }
        } catch (InputMismatchException exception){
            System.out.println("No one is joining for the party");
        }
    }
    private double roundup (double check, int number){
        check = check / number;
        check = Math.round(check*100);
        check = check / 100;
        return check;
    }
    private void lucky(String answer, DinnerParty Party, int number){
        if (answer.equals("Yes")){
            answerYES(Party,number);
        }
        else {
            System.out.println("No one is going to be lucky");
            Party.check = Party.roundup(Party.check,number);
            for (String key : Party.mapParty.keySet()){
                Party.mapParty.put(key, Party.check);
            }
            System.out.println(Party.mapParty);
        }
    }
    private void enterName(int number, DinnerParty Party){
        Scanner in = new Scanner(System.in);
        for (int i = 1; i <= number;i++  ){
            System.out.println("Enter your name");
            String name = in.next();
            Party.mapParty.put(name,0.0);
        }
    }
    private void answerYES (DinnerParty Party, int number){
        Random random = new Random();
        List<String> randomKeys = new ArrayList<>(Party.mapParty.keySet());
        String randomKey = randomKeys.get(random.nextInt(randomKeys.size()));
        System.out.println(randomKey+" is the lucky one!");
        Party.check = Party.roundup(Party.check,(number-1));
        for (String key : Party.mapParty.keySet()){
            if (key == randomKey) {
                Party.mapParty.put(key,0.00);
            }
            else {
                Party.mapParty.put(key, Party.check);
            }

        }
        System.out.println(Party.mapParty);
    }
}
