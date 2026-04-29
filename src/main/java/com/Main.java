package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static String userSelection;
    public static final String TRANSACTION_FILE_NAME = "src/main/resources/PrevTransaction.csv";
    static ArrayList<Transaction> transactionFile = loadTransaction(TRANSACTION_FILE_NAME);

    public static void main(String[] args) {
        mainMenu();
        for (Transaction e : transactionFile) {
            System.out.println(e.getTime());
        }
    }

    private static void mainMenu() {
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

    private static ArrayList<Transaction> loadTransaction(String filePath) {

        ArrayList<Transaction> transaction = new ArrayList<Transaction>();
        try (
                FileReader reader = new FileReader(filePath);
                BufferedReader buffer = new BufferedReader(reader);
        ) {
            buffer.readLine();
            String currentLine;
            while ((currentLine = buffer.readLine()) != null) {

                String[] splitLine = currentLine.split("\\|");
                LocalDate storeDate = LocalDate.parse(splitLine[0]);
                LocalTime storeTime = LocalTime.parse(splitLine[1]);
                String storeDescription = splitLine[2];
                String storeVendor = splitLine[3];
                double storeAmount = Double.parseDouble(splitLine[4]);

                transaction.add(new Transaction(storeDate, storeTime, storeDescription, storeVendor, storeAmount));
            }

        } catch (
                IOException e) {
            System.err.println("File is not found" + filePath);
        }
        return transaction;
    }

    private static void ledgerScreen() {


    }


    private static void makePayment() {

    }

    private static void addDeposit() {
        ArrayList<Transaction> transactions = getTransaction();
        transactionFile.addAll(transactions);
    }

    private static ArrayList<Transaction> getTransaction() {
        ArrayList<Transaction> transaction = new ArrayList<>();
        boolean running = true;
        do {
            System.out.println("\n Please enter the information for your Deposit transaction: ");

            System.out.println("Please enter the date (yyyy-MM-dd): ");
            userSelection = myScanner.nextLine();
            LocalDate date = LocalDate.parse(userSelection);

            System.out.println("Please enter the time of transaction (HH:MM:SS");
            LocalTime time = LocalTime.parse(myScanner.nextLine());

            System.out.println("please enter the description of the transaction: ");
            String description = myScanner.nextLine();

            System.out.println("Please enter the vendor name: ");
            String vendor = myScanner.nextLine();

            System.out.println("Please enter the amount: ");
            double amount = Double.parseDouble(myScanner.nextLine());

            transaction.add(new Transaction(date, time, description, vendor, amount));

            String confrimation = ("Would you like to add another transaction: Yes or No?");
            System.out.println(confrimation);

            String userInput = myScanner.nextLine();
            if (userInput.equalsIgnoreCase("no")) {
                running = false;
            }
        } while (running);
        return transaction;
    }
}

