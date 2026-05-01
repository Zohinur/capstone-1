# 💰 Java Transaction Ledger Application

Java application for managing financial transactions such as deposits and payments. This program allows users to record, view, and filter transactions stored in a CSV file.


## 📌 Features

### 🏠 Home Screen Options

* **D** – Add Deposit
* **P** – Make Payment
* **L** – Open Ledger Screen
* **X** – Exit Application


## 📒 Ledger Capabilities

From the Ledger screen, users can:

* View **all transactions**
* Filter **deposits only**
* Filter **payments only**
* Run **custom reports**



## 🔍 Reporting Options

The application supports several built-in reports:

1. **Month to Date**
2. **Previous Month**
3. **Year to Date**
4. **Previous Year**
5. **Search by Vendor**
6. **Custom Filter**


## 🧠 Custom Filter Options

Users can combine multiple filters:

* 📅 Date Range (Start & End)
* 📝 Description
* 🏢 Vendor Name
* 💵 Amount

Leave any field blank to skip that filter.


## 🗂️ File Structure

Transactions are stored in:

```
src/main/resources/transactions.csv
```

### CSV Format:

```
date|time|description|vendor|amount
```

Example:

```
2026-04-15|14:30:00|Groceries|ShopRite|-54.23
```



## ⚙️ How It Works

* The application loads all transactions from the CSV file at startup.
* Users can add deposits or payments (payments are stored as negative values).
* New transactions are appended to the CSV file.
* Data is displayed and filtered using Java collections and date/time APIs.


## 🛠️ Technologies Used

* Java
* File I/O (`BufferedReader`, `FileWriter`)
* Java Time API (`LocalDate`, `LocalTime`)
* Collections (`ArrayList`)
* Comparator for sorting


## 🚀 Getting Started


### Run the Application

1. Clone or download the project
2. Open in your preferred IDE (e.g., IntelliJ, Eclipse)
3. Ensure the CSV file exists at the correct path
4. Run the `Main` class



## ⚠️ Notes & Limitations

* Input validation is minimal (incorrect formats may cause errors)
* Dates must follow: `yyyy-MM-dd`
* Time must follow: `HH:mm:ss`
* CSV file must exist before running the program



##  Improvement for the future 

* Add input validation and error handling
* Add ability to edit/delete transactions
* Add category tagging
* Making code more organized 

## favorite part about the project 

* The emojis in the code 
* Using the Arraylist to store data 
* Making most code a method 

## Some challenges 
* Organizing the code well, it was hard to know where to start the method 
* Not enough time to add design to the terminal.
* Adding more comments to the application 
* Knowing when to commit 

## Favorite block of code  
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

Another favorite block of code


            System.out.println("Please enter the date (yyyy-MM-dd): ");
            userSelection = myScanner.nextLine();
            //Using Localdate to parse the String into a date
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
                //If the user enters no then it will get out the loop
                running = false;
            }
        } while (running);
        return transaction;
    }

