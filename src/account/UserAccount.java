package account;

import transaction.Transaction;
import transaction.TransactionType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccount extends Account {

    private static final int pin = 123;
    private List<Transaction> myHistoryTransaction;

    public UserAccount(String name, String accountNumber, double balance) {
        super(name,accountNumber, String.valueOf(pin), balance);
    }

    public List<Transaction> getTransactions() {
        myHistoryTransaction = new ArrayList<>();
        for (Transaction transaction : transactionHistory.getTransactions()) {
            if (transaction.getAccountNumber().equals(this.getAccountNumber())) {
                myHistoryTransaction.add(transaction);
            }
        }
        return myHistoryTransaction;
    }

    public boolean transfer(String recipientAccountNumber, double amount, List<UserAccount> userAccounts) {
        if (amount > 0 && (amount <= super.getDoubleBalance())) {
            UserAccount recipient = findUserAccount(recipientAccountNumber, userAccounts);
            if (recipient != null) {
                this.setBalance(this.getDoubleBalance() - amount);
                recipient.setBalance( recipient.getDoubleBalance() + amount);

                // Menambahkan transaksi untuk pengirim

                Transaction transactionSender = new Transaction("TRANSFERTX" + System.currentTimeMillis(), this.getAccountNumber(), TransactionType.TRANSFER, -amount, new Date());
                myHistoryTransaction.add(transactionSender);
                this.addTransaction(transactionSender);

                // Menambahkan transaksi untuk penerima
                Transaction transactionRecipient = new Transaction("GETBALANCETX" + System.currentTimeMillis(), recipient.getAccountNumber(), TransactionType.GET_BALANCE, amount, new Date());
                recipient.myHistoryTransaction.add(transactionRecipient);
                recipient.addTransaction(transactionRecipient);

                System.out.println("Sisa saldo: " + this.getBalance());
                return true;
            } else {
                System.out.println("Nomor akun penerima tidak valid.");
            }
        } else {
            System.out.println("Jumlah transfer tidak valid.");
        }
        return false;
    }

    private UserAccount findUserAccount(String recipientAccountNumber, List<UserAccount> userAccounts) {
        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getAccountNumber().equals(recipientAccountNumber)) {
                return userAccount;
            }
        }
        return null;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= super.getDoubleBalance()) {
            this.setBalance(this.getDoubleBalance() - amount);

            // Menambahkan transaksi penarikan
            Transaction transaction = new Transaction("TX" + System.currentTimeMillis(), this.getAccountNumber(), TransactionType.WITHDRAWAL, -amount, new Date());
            this.addTransaction(transaction);

            System.out.println("Penarikan berhasil.");
            return true;
        } else {
            System.out.println("Jumlah penarikan tidak valid.");
        }
        return false;
    }

}
