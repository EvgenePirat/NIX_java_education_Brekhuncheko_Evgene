package nix.education.java.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Banking Bank = new Banking();
        Bank.cashDispenser();
    }
}

