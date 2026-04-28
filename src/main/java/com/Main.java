package com;

import java.util.Scanner;

public class pluralsight {
    static Scanner myScanner = new Scanner(System.in);
    static String userSelection;
    static String filePath = "src/main/resources/Transaction.csv";

    public static void main(String[] args) {
        boolean running = true;

        do {
            String prompt = ("""
                     Home Screen
                    D. Add Deposit
                    P. Make Payment
                    L. Ledger Screen
                    X. Exit This Application""");
            System.out.println(prompt);
            userSelection = myScanner.nextLine();
            switch (userSelection) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerScreen();
                    break;
                case "X":
                    System.out.println("We're sad to see you go\uD83D\uDE15");
                    running = false;
                    break;
                default:
                    System.err.println("Entered wrong syntax");
            }
        } while (running);

    }

    private static void ledgerScreen() {
    }

    private static void makePayment() {
    }

    private static void addDeposit() {
    }
}
