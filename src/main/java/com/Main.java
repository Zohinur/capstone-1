package com;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static String userSelection;
    public static final String TRANSACTION_FILE_NAME = "src/main/resources/PrevTransaction.csv";
    static ArrayList<Transaction> transactionFile = loadTransaction(TRANSACTION_FILE_NAME);

    public static void main(String[] args) {
        mainMenu();
        displayAll(transactionFile);
    }

    private static void display(Transaction e) {
        System.out.println(e.getDate() + "|" + e.getTime() + "|" + e.getDescription());
    }

    public static void displayAll(ArrayList<Transaction> transactions) {
        System.out.println("header");
        for (Transaction e : transactions) {
            display(e);
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
                    X. Exit This Application
                    input:""");
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

    private static void writeTransaction(ArrayList<Transaction> transactions) {
        try (FileWriter write = new FileWriter(TRANSACTION_FILE_NAME, true);
             BufferedWriter buffWrite = new BufferedWriter(write);
        ) {
            for (Transaction e : transactions) {
                buffWrite.write(e.getDate() + "|" + e.getTime() + "|" + e.getDescription() + "|" + e.getVendor() + "|" + e.getAmount());
            }
            buffWrite.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void ledgerScreen() {
        boolean running = true;
        do {
            System.out.println("Welcome to the ledger Screen! ");
            String prompt = """
                    what would you like to do?
                    A) display all entries
                    D) display all entries with deposit
                    P) Display all entries with payment
                    bR) Run a custom search
                    H) Return Home""";
            System.out.println(prompt);
            String userSelection = myScanner.nextLine();

            switch (userSelection) {
                case ("A"):
                    displayEntries();
                    break;
                case ("D"):
                    displayDeposit();
                    break;
                case ("P"):
                    displayPayment();
                    break;
                case ("R"):
                    reports();
                    break;
                case ("H"):
                    running = false;
            }
        } while (running);

    }

    private static void reports() {
        boolean running = true;
        do {
            String prompt = """
                    How would you like to search?
                    1. Month to date
                    2. Previous Month
                    3. Year to date
                    4. Previous year
                    5. Search by vendor
                    0. Return to Ledger Screen""";
            System.out.println(prompt);
            String userSelection = myScanner.nextLine();
            switch (userSelection) {
                case "1":
                    monthDate();
                    break;
                case "2":
                    prevMonth();
                    break;
                case "3":
                    yearToDate();
                    break;
                case "4":
                    prevYear();
                    break;
                case "5":
                    byVendor();
                    break;
                case "0":
                    running = false;
            }
        } while (running);
    }

    private static void byVendor() {

        System.out.println("Enter the Vendor name: ");
        //ArrayList<Transaction> vendorTrans = new ArrayList<Transaction>();
        String userSelection = myScanner.nextLine();
        for (Transaction e : transactionFile) {
            if (Objects.equals(userSelection, e.vendor)) {
               // vendorTrans.add(e);
                System.out.println(e);
            }
            //writeTransaction(vendorTrans);
        }

    }

    private static void prevYear() {
        System.out.println("Here is the transaction from previous year: ");
        for(Transaction e: transactionFile) {
                if(e.getDate().getYear() == 2025){
                    System.out.println(e);
                }
        }

    }

    private static void yearToDate() {
////        System.out.println("Transaction from Year to Date");
////        for(Transaction e: transactionFile) {
////            if(e.getDate().getYear()==2026){
////                System.out.println(e);
//            }
//        }
    }

    private static void prevMonth() {
        System.out.println("Transaction from previous Month ");
    }

    private static void monthDate() {
    }


    private static void displayPayment() {
    }

    private static void displayDeposit() {
    }

    private static void displayEntries() {
    }


    private static void makePayment() {
        ArrayList<Transaction> transactions = getTransaction();
        for (Transaction e : transactions) {
            e.setAmount((e.getAmount() * -1));
        }
        transactionFile.addAll(transactions);
        writeTransaction(transactions);
    }

    private static void addDeposit() {
        ArrayList<Transaction> transactions = getTransaction();
        transactionFile.addAll(transactions);
        writeTransaction(transactions);
    }

    private static ArrayList<Transaction> getTransaction() {
        ArrayList<Transaction> transaction = new ArrayList<>();
        boolean running = true;
        do {
            System.out.println("\n Please enter the information for your Deposit transaction: ");

            System.out.println("Please enter the date (yyyy-MM-dd): ");
            userSelection = myScanner.nextLine();
            LocalDate date = LocalDate.parse(userSelection);

            System.out.println("Please enter the time of transaction (HH:mm:ss): ");
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

