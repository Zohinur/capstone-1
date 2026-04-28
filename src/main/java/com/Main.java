package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static String userSelection;
    public static final String TRANSACTION_FILE_NAME = "src/main/resources/Transaction.csv";

    static ArrayList<ledgerScreen> transactionFile = loadTransaction(TRANSACTION_FILE_NAME);

    public static void main(String[] args) {
        mainMenu();
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

    private static ArrayList<ledgerScreen> loadTransaction(String filePath) {

        ArrayList<ledgerScreen> transaction = new ArrayList<ledgerScreen>();
        try (
                FileReader reader = new FileReader(filePath);
                BufferedReader buffer = new BufferedReader(reader);
        ) {
            buffer.readLine();
            String currentLine;
            while ((currentLine = buffer.readLine()) != null) {
                System.out.println("Here is the line" + currentLine);

                String[] splitLine = currentLine.split("\\|");
                LocalDate storeDate = LocalDate.parse(splitLine[0]);
                LocalTime storeTime = LocalTime.parse(splitLine[1]);
                String storeDescription = splitLine[2];
                String storeVendor = splitLine[3];
                double storeAmount = Double.parseDouble(splitLine[4]);

                transaction.add(new ledgerScreen(storeDate, storeTime, storeDescription, storeVendor, storeAmount));
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

    private static ArrayList<ledgerScreen> addDeposit() {
        ArrayList<ledgerScreen> addDate = new ArrayList<ledgerScreen>();

        System.out.println("Please enter the information for your Deposit transaction: ");

        System.out.println("Please enter the date: ");
        userSelection = myScanner.nextLine();
        DateFormat dateFormat = userSelection;

    }

}