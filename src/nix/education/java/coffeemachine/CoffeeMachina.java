package nix.education.java.coffeemachine;

import java.util.Scanner;

public class CoffeeMachina {
    int milk, money,water,coffeeBeans,cups;
    public static void main(String[] args) {
        workCoffeeMachine ();

    }
    public static void workCoffeeMachine (){
        Scanner in = new Scanner(System.in);
        CoffeeMachina CoffeeMachina = new CoffeeMachina(400,540,120,9,550);
        boolean a = true;
        while (a == true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = in.next();
            if (action.equals("exit")) {
                a = false;
                break;
            }
            CoffeeMachina.coffeeMachine(action, CoffeeMachina);
        }
    }
    public CoffeeMachina(int water, int milk, int coffeeBeans, int cups, int money){
        Scanner in = new Scanner(System.in);
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
        recipe();
        System.out.println("Write how many cups of coffee you will need:");
        int numberCup = in.nextInt();
        computation(numberCup);
        calculation();
    }
    private void coffeeMachine (String action, CoffeeMachina coffeeMachina) {
        if (action.equals("buy")) {
            buy(coffeeMachina);
        } else if (action.equals("fill")) {
            fill(coffeeMachina);
        } else if (action.equals("take")) {
            System.out.println("I gave you " + coffeeMachina.money);
            coffeeMachina.money = 0;
        } else if (action.equals("remaining")) {
            quantity(coffeeMachina);
        } else {
            System.out.println("Попытайтесь снова");
        }
    }
    private void quantity(CoffeeMachina coffeeMachina){
        System.out.println("The coffee machine has:");
        System.out.println(coffeeMachina.water + " of water");
        System.out.println(coffeeMachina.milk + " of milk");
        System.out.println(coffeeMachina.coffeeBeans + " of coffee beans");
        System.out.println(coffeeMachina.cups + " of disposable cups");
        System.out.println(coffeeMachina.money + " of money");
    }

    private void calculation() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has:");
        int waterInMachine = in.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        int milkInMachine = in.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int coffeeBeansInMachine = in.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        int numberCup = in.nextInt();
        int waterInMachineRemainder = waterInMachine - 200 * numberCup;
        int milkInMachineRemainder = milkInMachine - 50 * numberCup;
        int coffeeInMachineRemainder = coffeeBeansInMachine - 15 * numberCup;
        int counterNumber = 0;
        while (true) {
            if (waterInMachine >= 200 && milkInMachine >= 50 && coffeeBeansInMachine >= 15) {
                waterInMachine = waterInMachine - 200;
                milkInMachine = milkInMachine - 50;
                coffeeBeansInMachine = coffeeBeansInMachine - 15;
                counterNumber = counterNumber + 1;
            } else {
                break;
            }
        }
        if (waterInMachineRemainder >= 0 && milkInMachineRemainder >= 0 && coffeeInMachineRemainder >= 0) {
            if (waterInMachineRemainder >= 200 && milkInMachineRemainder >= 50 && coffeeInMachineRemainder >= 15) {
                System.out.println("Yes, I can make that amount of coffee (and even " + (counterNumber - numberCup) + " more than that)");
            } else {
                System.out.println("Yes, I can make that amount of coffee.");
            }
        } else {
            System.out.println("No, I can make only " + counterNumber + " cups of coffee");
        }
    }

    private void buy(CoffeeMachina CoffeeMachina) {
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String coffeeBuy = in.nextLine();
        switch (coffeeBuy) {
            case "1":
                if (CoffeeMachina.water >= 250 && CoffeeMachina.coffeeBeans >= 16 && CoffeeMachina.cups > 0) {
                    withdrawal(4,250,0,16,1,CoffeeMachina);
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    shortage(250,0,16,0,CoffeeMachina);
                }
                break;
            case "2":
                if (CoffeeMachina.water >= 350 && CoffeeMachina.milk >= 75 && CoffeeMachina.coffeeBeans >= 20 && CoffeeMachina.cups > 0) {
                    withdrawal(7,350,75,20,1,CoffeeMachina);
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    shortage(350,75,20,0,CoffeeMachina);
                }
                break;
            case "3":
                if (CoffeeMachina.water >= 200 && CoffeeMachina.milk >= 100 && CoffeeMachina.coffeeBeans >= 12 && CoffeeMachina.cups > 0) {
                    withdrawal(6,200,100,12,1,CoffeeMachina);
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    shortage(200,100,12,0,CoffeeMachina);
                }
                break;
            case "back":
                break;
        }
    }
    private void withdrawal(int cashMoney, int cashWater, int cashMilk, int cashCoffeeBeans, int cashCups, CoffeeMachina CoffeeMachina){
        CoffeeMachina.money = CoffeeMachina.money + cashMoney;
        CoffeeMachina.water = CoffeeMachina.water - cashWater;
        CoffeeMachina.milk = CoffeeMachina.milk - cashMilk;
        CoffeeMachina.coffeeBeans = CoffeeMachina.coffeeBeans - cashCoffeeBeans;
        CoffeeMachina.cups = CoffeeMachina.cups - cashCups;
    }
    private void shortage (int cashWater, int cashMilk, int cashCoffeeBeans, int cashCups, CoffeeMachina CoffeeMachina){
        if (CoffeeMachina.water < cashWater) {
            System.out.println("Sorry, not enough water!");
        }
        if (CoffeeMachina.milk < cashMilk) {
            System.out.println("Sorry, not enough milk!");
        }
        if (CoffeeMachina.coffeeBeans < cashCoffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        }
        if (CoffeeMachina.cups < cashCups) {
            System.out.println("Sorry, not enough disposable cups!");
        }
    }
    private void fill(CoffeeMachina CoffeeMachina) {
        System.out.println("Write how many ml of water you want to add:");
        Scanner in = new Scanner(System.in);
        int waterAdd = in.nextInt();
        CoffeeMachina.water = CoffeeMachina.water + waterAdd;
        System.out.println("Write how many ml of milk you want to add:");
        int milkAdd = in.nextInt();
        CoffeeMachina.milk = CoffeeMachina.milk + milkAdd;
        System.out.println("Write how many grams of coffee beans you want to add:");
        int coffeeAdd = in.nextInt();
        CoffeeMachina.coffeeBeans = CoffeeMachina.coffeeBeans + coffeeAdd;
        System.out.println("Write how many disposable coffee cups ypu want to add:");
        int cupsAdd = in.nextInt();
        CoffeeMachina.cups = CoffeeMachina.cups + cupsAdd;
        System.out.println(" ");
    }
    private void recipe(){
        System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");
    }
    private void computation(int number){
        System.out.println("For "+number+" cups of coffee you will need:");
        System.out.println(200 * number + " ml of water");
        System.out.println(50 * number + " ml of milk");
        System.out.println(15 * number + " g of coffee beans");
    }
}
