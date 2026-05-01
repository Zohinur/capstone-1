# 💰 Java Transaction Ledger Application

A simple command-line Java application for managing financial transactions such as deposits and payments. This program allows users to record, view, and filter transactions stored in a CSV file.


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

### Prerequisites

* Java JDK 8 or higher

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



## ✨ Some Future Improvements

* Add input validation and error handling
* Add ability to edit/delete transactions
* Add category tagging




