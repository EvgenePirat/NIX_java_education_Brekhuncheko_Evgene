package nix.education.java.bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Banking {
    Connection conn = null;
    boolean a = true;
    Connect Conn;
    int command;
    Scanner in = new Scanner(System.in);
    boolean b = true;
    public void cashDispenser (){
        Conn = new Connect();
        conn = Conn.Connection();
        while (a){
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");
            command = in.nextInt();
            performance(command);
        }
    }
    private void performance(int command){
        switch (command){
            case 1:
                createdAccount();
                break;
            case 2:
                logAccount();
                break;
            case 0:
                a = false;
                break;
            default:
                break;
        }
    }
    private void logAccount(){
        List<Accountt> accountPeople;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your card number:");
        long cardEnter = in.nextLong();
        System.out.println("Enter your PIN:");
        int cardPINEnter = in.nextInt();
        accountPeople = findAccount(cardEnter,cardPINEnter);
        if (accountPeople.size() != 0){
            Accountt account = accountPeople.get(0);
            System.out.println("");
            System.out.println("You have successfully logged in!");
            System.out.println("");
            accountPeople(account);
        }
    }
    private void accountPeople(Accountt account){
        boolean w = true;
        while (w){
            System.out.println("1. Balance");
            System.out.println("2. Add income");
            System.out.println("3. Do transfer");
            System.out.println("4. Close account");
            System.out.println("5. Log out");
            System.out.println("0. Exit");
            command = in.nextInt();
            w = activeInAccount(command, w, account);
        }
    }
    private boolean activeInAccount(int command, boolean w,Accountt account){
        switch (command){
            case 1:
                System.out.println("Balance = "+ account.getCardBalance());
                return w;
            case 2:
                addMoney(account);
                return w;
            case 3:
                transferMoney(account);
                return w;
            case 4:
                w = deletedAccount(account, w);
                return w;
            case 5:
                w = false;
                return w;
            case 0:
                System.out.println("Bye");
                w = false;
                a = false;
                return w;
        }
        return false;
    }
    public void transferMoney (Accountt account){
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        long cardNumber = in.nextLong();
        if (algorithmMoon(cardNumber) == true){
            List<Accountt> listAccount;
            String queryOne = "SELECT * FROM account WHERE cardNumber = "+cardNumber;
            listAccount = selectAccount(queryOne);
            if (listAccount.size() == 0){
                System.out.println("Such a card does not exist.");
            }
            else {
                Accountt accountTransfer = listAccount.get(0);
                if (account.getCardNumber() == accountTransfer.getCardNumber()){
                    System.out.println("You can't transfer money to the same account!");
                }
                else{
                    System.out.println("Enter how much money you want to transfer:");
                    int moneyTransfer = in.nextInt();
                    if (account.getCardBalance() >= moneyTransfer){
                        String queryTwo = "UPDATE account SET cardBalance = "+(account.getCardBalance()-moneyTransfer)+" WHERE cardNumber = "+account.getCardNumber();
                        String queryThree = "UPDATE account SET cardBalance = "+(accountTransfer.getCardBalance()+moneyTransfer)+" WHERE cardNumber = "+accountTransfer.getCardNumber();
                        System.out.println("Success!");
                        transfer(queryTwo);
                        transfer(queryThree);
                        account.minusMoney(moneyTransfer);
                    }
                    else {
                        System.out.println("Not enough money!");
                    }
                }
            }
        }
        else{
            System.out.println("Probably you made a mistake in the card number. Please try again!");
        }
    }
    private void transfer(String query){
        try{
            Statement statement = conn.createStatement();
            statement.execute(query);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private boolean deletedAccount(Accountt account, boolean w){
        String query = "DELETE FROM account WHERE cardNumber = "+account.getCardNumber();
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            System.out.println("The account has been closed!");
            w = false;
            return w;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return w;
        }
    }
    private void addMoney(Accountt account){
        System.out.println("Enter income:");
        int money = in.nextInt();
        String query = "UPDATE account SET cardBalance = "+(account.getCardBalance()+money)+" WHERE cardNumber = "+account.getCardNumber();
        try{
            Statement statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Income was added!");
            account.addMoney(money);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private List<Accountt> findAccount (long cardEnter, int cardPINEnter){
        List<Accountt> account;
        String query = "SELECT * FROM account WHERE cardNumber = "+cardEnter;
        account = selectAccount(query);
        if (account.size() == 0){
            System.out.println("Wrong number card!");
            return account;
        }
        Accountt personal = account.get(0);
        if (personal.getCardPin() != cardPINEnter){
            System.out.println("Wrong PIN!");
            account.clear();
            return account;
        }
        return account;
    }
    private List<Accountt> selectAccount(String query){
        List<Accountt> account = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Accountt accountt = new Accountt(resultSet.getLong("cardNumber"),resultSet.getInt("cardPin"),resultSet.getInt("cardBalance"));
                account.add(accountt);
            }
            return account;

        }catch (SQLException e){
            System.out.println(e.getMessage());
            return account;
        }
    }
    private void createdAccount (){
        long cardNumber;
        boolean v;
        cardNumber = (long)(Math.random()*10000000000000000L);
        if (algorithmMoon(cardNumber) == true){
            int cardPIN = (int)(Math.random()*10000);
            String str = Integer.toString(cardPIN);
            if (str.length() == 4){
                int cardBalance = 0;
                System.out.println("Your card has been created");
                System.out.println("Your card number:");
                System.out.println(cardNumber);
                System.out.println("Your card PIN:");
                System.out.println(cardPIN);
                String query = "INSERT INTO account(cardNumber, cardPIN, cardBalance) VALUES (?,?,?)";
                addAccount(query,cardNumber,cardPIN,cardBalance);
            }
            else {
                createdAccount();
            }
        }
        else {
            createdAccount();
        }

    }
    private boolean algorithmMoon(long cardNumber){
        String str = Long.toString(cardNumber);
        String[]numb=str.split("");
        int numArr[] = new int[numb.length];
        for (int i = 0; i < numb.length; i++) {
            numArr[i] = Integer.parseInt(numb[i]);
        }
        for (int j=0; j < numArr.length; j=j+2){
            int k=0;
            k = 2 * numArr[j];
            if (k > 9){
                numArr[j] = k - 9;
            }
            else{
                numArr[j] = k;
            }
        }
        int sum=0;
        for (int j=0; j < numArr.length; j++){
            sum = sum + numArr[j];
        }
        if (sum % 10 == 0){
            return true;
        }
        return false;
    }
    private void addAccount(String query, long cardNumber, int cardPIN, int cardBalance){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setLong(1,cardNumber);
            preparedStatement.setInt(2,cardPIN);
            preparedStatement.setInt(3,cardBalance);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
class Connect{
    Connection conn = null;
    String url = "jdbc:sqlite:datebase.db";
    public Connection Connection (){
        connect();
        return conn;
    }
    public void disconnect(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void connect(){

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQL basedate");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
class Accountt{
    public Long getCardNumber(){
        return cardNumber;
    }
    public Integer getCardPin(){
        return cardPin;
    }
    public Integer getCardBalance(){
        return balance;
    }
    private long cardNumber;
    private int cardPin;
    private int balance;
    public Accountt (long cardNumb, int cardPIN, int balanc){
        this.cardNumber = cardNumb;
        this.cardPin = cardPIN;
        this.balance = balanc;
    }

    @Override
    public String toString() {
        return "Accountt{" +
                "cardNumber=" + cardNumber +
                ", cardPin=" + cardPin +
                "cardBalance=" + balance +
                '}';
    }
    public Integer addMoney(int money){
        balance = balance + money;
        return balance;
    }
    public Integer minusMoney(int money){
        balance = balance - money;
        return balance;
    }
}

