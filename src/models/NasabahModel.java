package models;

import java.util.List;

public class NasabahModel {
    private String name;

    List<TransactionModel> transactionList;

    private final String accountNumber;
    private final String pin;
    private double balance;

    public NasabahModel(String name, String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public void setTransactionList(List<TransactionModel> transactionList) {
        this.transactionList = transactionList;
    }

    public List<TransactionModel> getTransactionList() {
        return transactionList;
    }

    public String getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getDoubleBalance() {
        return balance;
    }

    public String getBalance() {
        return String.format("Rp%,.2f", balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
