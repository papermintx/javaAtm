package account;

import transaction.TransactionHistory;


public class Account {
    private String name;
    private final String accountNumber;
    private final String pin;
    private double balance;
    protected TransactionHistory transactionHistory = new TransactionHistory();

    public Account(String name, String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public Account(String accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
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

    public void addTransaction(transaction.Transaction transaction) {
        transactionHistory.addTransaction(transaction);
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }
}
