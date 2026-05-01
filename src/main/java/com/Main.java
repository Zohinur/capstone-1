package com;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static String userSelection;
    public static final String TRANSACTION_FILE_NAME = "src/main/resources/transactions.csv";
    static ArrayList<Transaction> allTransactions = loadTransaction(TRANSACTION_FILE_NAME);

    public static void main(String[] args) {
        //The Main Menu for the project of where it all starts
        mainMenu();
    }

    //Display method to print out the object in the format from csv file
    private static void display(Transaction e) {
        System.out.println(e.getDate() + "|" + e.getTime() + "|" + e.getDescription() + "|" + e.getVendor() + "|" + e.getAmount());
    }

    //displayAll method is to display the whole object itself
    public static void displayAll(ArrayList<Transaction> transactions) {
        System.out.println("date|time|description|vendor|amount");
        //This loops through all the objects and displays them 1 by 1.
        for (Transaction e : transactions) {
            display(e);
        }
    }

    //This is mainMenu method where the users can select what they want to do.
    private static void mainMenu() {
        boolean running = true;

        do {
            String prompt = ("""
                     🏠 Home Screen Options
                    D. Add Deposit
                    P. Make Payment
                    L. Ledger Screen
                    X. Exit This Application
                    input:""");
            System.out.print(prompt);
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
                    //Added an emoji and ASCII text
                    System.out.println("We're sad to see you go\uD83D\uDE15" +
                            " ____  _     _    _____ _____ _  _      _____   ____  ____  _      _           \n" +
                            "/ ___\\/ \\ /|/ \\ /Y__ __Y__ __Y \\/ \\  /|/  __/  /  _ \\/  _ \\/ \\  /|/ \\  /|      \n" +
                            "|    \\| |_||| | || / \\   / \\ | || |\\ ||| |  _  | | \\|| / \\|| |  ||| |\\ ||      \n" +
                            "\\___ || | ||| \\_/| | |   | | | || | \\||| |_//  | |_/|| \\_/|| |/\\||| | \\||______\n" +
                            "\\____/\\_/ \\|\\____/ \\_/   \\_/ \\_/\\_/  \\|\\____\\  \\____/\\____/\\_/  \\|\\_/  \\|\\/\\/\\/\n" +
                            "                                                                               ");
                    running = false;
                    break;
                default:
                    System.err.println("Entered wrong syntax");
            }
        } while (running);
    }

    //This methods reads the CSV file and creates the object but also parsing it as well
    private static ArrayList<Transaction> loadTransaction(String filePath) {

        ArrayList<Transaction> transaction = new ArrayList<Transaction>();
        try (
                FileReader reader = new FileReader(filePath);
                BufferedReader buffer = new BufferedReader(reader)
        ) {
            buffer.readLine();
            String currentLine;
            while ((currentLine = buffer.readLine()) != null) {

                //parsing the objects into parts of arrays
                String[] splitLine = currentLine.split("\\|");
                LocalDate storeDate = LocalDate.parse(splitLine[0]);
                LocalTime storeTime = LocalTime.parse(splitLine[1]);
                String storeDescription = splitLine[2];
                String storeVendor = splitLine[3];
                double storeAmount = Double.parseDouble(splitLine[4]);

                //Using the constructor from the Transaction class to store the data
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
             BufferedWriter buffWrite = new BufferedWriter(write)
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
            System.out.println();
            System.out.println("\uD83D\uDCD2 Welcome to the ledger Screen! ");
            String prompt = """
                    what would you like to do?
                    A) display all entries
                    D) display all entries with deposit
                    P) Display all entries with payment
                    R) Run a custom search
                    H) Return Home
                    User Input:""";
            System.out.print(prompt);
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
            System.out.println();
            String prompt = """
                    How would you like to search?
                    1. Month to date
                    2. Previous Month
                    3. Year to date
                    4. Previous year
                    5. Search by vendor
                    6. Custom filter
                    0. Return to Ledger Screen
                    User Input:""";
            System.out.println(prompt);
            String userSelection = myScanner.nextLine();
            switch (userSelection) {
                case "1":
                    monthToDate();
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
                case "6":
                    customFilter();
                    break;
                case "0":
                    running = false;
            }
        } while (running);
    }
//This method has the ability to search the transaction based of the vendor name
    private static void byVendor() {
        System.out.println();
        System.out.println("Enter the Vendor name: ");
        String userSelection = myScanner.nextLine();
        for (Transaction e : allTransactions) {
            if (Objects.equals(userSelection, e.vendor)) {
                System.out.println(e);
            }
        }

    }

    //Created method to get transaction from previous year using Localdate now to get Date for now storing it in "today"
    //Created another variable called "lastYear" to get the today's year then subtract by 1
    //Then Looped through all the objects in alltransactions and printing out objects that only equal to "lastYear
    private static void prevYear() {
        System.out.println();
        System.out.println("Transactions from previous year: ");
        LocalDate today = LocalDate.now();
        int lastYear = today.getYear() - 1;
        for (Transaction e : allTransactions) {
            if (e.getDate().getYear() == lastYear) {
                System.out.println(e);
            }
        }

    }

    private static void yearToDate() {
        System.out.println("Transaction from Year to Date");
        LocalDate year = LocalDate.now();
        int thisYear = year.getYear();
        for (Transaction e : allTransactions) {
            if (e.getDate().getYear() == thisYear) {
                display(e);
            }
        }
    }

    //Got today's day date first using LocalDate
    //initialized variable preMonth to subract  -1 to get previous month
    //if statement to print out last month transactions
    private static void prevMonth() {
        System.out.println("Transaction from previous Month ");
        //January works!
        LocalDate today = LocalDate.now();
        //found getMonthValue from the error
        int preMonth = today.getMonthValue() - 1;
        for (Transaction e : allTransactions) {
            if (e.getDate().getMonthValue() == preMonth) {
                display(e);
            }
        }
    }

    //Initialized variable "now" to gets today's date using LocalDate.now
    //Created another variable "todayNow" with the data type of Month to get month of "now"
    //Looped through all the objects and used if to print out transaction of the month
    private static void monthToDate() {
        System.out.println("Transaction from month to date: ");
        LocalDate now = LocalDate.now();
        //gave me error saying I need data type of Month then initiated to import the class
        Month todayNow = now.getMonth();
        for (Transaction e : allTransactions) {
            if (e.getDate().getMonth() == todayNow) {
                display(e);
            }
        }

    }

    //Looped through all the objects from "allTransaction"
    //If condition to check if amount is less than 0 to display all transaction that are - which displays all the payment
    private static void displayPayment() {
        System.out.println();
        System.out.println("Payment Transaction only:");
        for (Transaction e : allTransactions) {
            if (e.getAmount() < 0) {
                display(e);
            }
        }
    }

    //Looped through all the objects in "allTransaction"
    //Using if condition I only printed out amount that were greater than 0
    private static void displayDeposit() {
        System.out.println("Deposits Transaction only: ");
        for (Transaction e : allTransactions) {
            if (e.getAmount() >= 0) {
                display(e);
            }
        }
    }

    private static void displayEntries() {
        //sorted out all the objects from earliest to latest using Comparator class and comparing method from that class
        //Using reversed method I was able to print out eh earliest transaction first
        allTransactions.sort(Comparator.comparing(Transaction::getDate).reversed());
        displayAll(allTransactions);
    }

    //Filtering features
    public static void customFilter() {
        ArrayList<Transaction> results = filterByDate(allTransactions);
        results = filterByDescription(results);
        results = filterByVendor(results);
        results = filterByAmount(results);
        displayAll(results);

    }

    private static ArrayList<Transaction> filterByVendor(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> results = new ArrayList<>();
        System.out.print("Enter the vendor name: ");
        String userInput = myScanner.nextLine();

        for (Transaction e : transactions) {
            if (userInput.isEmpty() || e.getVendor().equalsIgnoreCase(userInput)) {
                results.add(e);
            }
        }
        return results;
    }

    private static ArrayList<Transaction> filterByAmount(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> results = new ArrayList<Transaction>();
        System.out.println("Enter the amount: ");
        String userInput = myScanner.nextLine();
        //This condition checks if its true or false
        Double amount = userInput.isEmpty() ? null : Double.parseDouble(userInput);

        for (Transaction e : transactions) {
            if (amount == null || e.getAmount() == amount) {
                results.add(e);
            }
        }
        return results;
    }

    private static ArrayList<Transaction> filterByDescription(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> results = new ArrayList<>();
        System.out.print("Enter the Description: ");
        String userInput = myScanner.nextLine();

        for (Transaction e : transactions) {
            if (userInput.isEmpty() || e.getDescription().equalsIgnoreCase(userInput)) {
                results.add(e);
            }
        }
        return results;
    }

    private static ArrayList<Transaction> filterByDate(ArrayList<Transaction> transactions) {
        System.out.print("Enter your start date: ");
        String userInput = myScanner.nextLine();
        //this uses the condition and if its true its null and if its true then it parses the userInput
        LocalDate startDate = userInput.isEmpty() ? null : LocalDate.parse(userInput);
        System.out.print("Enter your end date: ");
        String userInput1 = myScanner.nextLine();
        LocalDate endDate = userInput1.isEmpty() ? null : LocalDate.parse(userInput1);

        ArrayList<Transaction> results = new ArrayList<>();

        for (Transaction t : transactions) {
            //This checks if start and end date is null, if so it will skip the program
            //Checks if the start date is equal or after to get the transactions from those dates
            // The end date is before so the user can have the range of the transactions
            if ((startDate == null || t.getDate().isEqual(startDate) || t.getDate().isAfter(startDate))
                    && (endDate == null || t.getDate().isEqual(endDate) || t.getDate().isBefore(endDate))) {
                results.add(t);
            }
        }
        return results;
    }


    private static void makePayment() {
        ArrayList<Transaction> transactions = getTransaction();
        for (Transaction e : transactions) {
            e.setAmount((e.getAmount() * -1));
        }
        allTransactions.addAll(transactions);
        writeTransaction(transactions);
    }

    private static void addDeposit() {
        ArrayList<Transaction> transactions = getTransaction();
        allTransactions.addAll(transactions);
        writeTransaction(transactions);
    }

    private static ArrayList<Transaction> getTransaction() {
        ArrayList<Transaction> transaction = new ArrayList<>();
        boolean running = true;
        do {
            System.out.println("\n Please enter the information for your transaction: ");

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
            System.out.println("Your transaction has been recorded!!!");
            System.out.println();
            String confrimation = ("Would you like to add another transaction: Yes or No?");
            System.out.println(confrimation);
            System.out.println();
            String userInput = myScanner.nextLine();
            if (userInput.equalsIgnoreCase("no")) {
                running = false;
            }
        } while (running);
        return transaction;
    }
}

