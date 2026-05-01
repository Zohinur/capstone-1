package com;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    public LocalDate date;
    private LocalTime time;
    public String description;
    public String vendor;
    public double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.amount = amount;
        this.vendor = vendor;
        this.description = description;
        this.time = time;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
