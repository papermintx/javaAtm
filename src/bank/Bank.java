package bank;
import account.AdminAccount;
import account.UserAccount;
import transaction.Transaction;
import transaction.TransactionHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private List<AdminAccount> adminAccounts = new ArrayList<>();
    private List<UserAccount> userAccounts = new ArrayList<>();

    TransactionHistory transactionHistory = new TransactionHistory();

    public void setAdminAccounts(AdminAccount adminAcount) {
        this.adminAccounts.add(adminAcount);
    }
    public void setUserAccounts(UserAccount userAccount) {
        this.userAccounts.add(userAccount);
    }
    public void createUserAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Membuat Akun Pengguna ===");
        System.out.print("Masukkan nama pengguna: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan nomor akun pengguna: ");
        String accountNumber = scanner.nextLine();
        System.out.println("Validasi sedang berlangsung...");
        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getAccountNumber().equals(accountNumber)) {
                System.out.println("Nomor akun pengguna sudah digunakan. Silakan coba lagi.");
                return;
            }
        }
        System.out.print("Masukkan PIN: ");
        String pin = scanner.nextLine();
        System.out.println("Validasi sedang berlangsung...");

        if (pin.length() != 6) {
            throw new IllegalArgumentException("PIN harus terdiri dari 6 digit. Silakan coba lagi.");
        }

        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getPin().equals(pin)) {
                throw new IllegalArgumentException("PIN sudah digunakan. Silakan coba lagi.");
            }
        }

        System.out.print("Masukkan saldo awal: ");
        double balance = scanner.nextDouble();
        scanner.close();


        UserAccount newAccount = new UserAccount(name,accountNumber, balance);
        userAccounts.add(newAccount);
        System.out.println("Akun pengguna berhasil dibuat.");
    }

    public void displayAllAccounts() {
        System.out.println("\n=== Semua Akun ===");
        for (UserAccount userAccount : userAccounts) {
            System.out.println("User Account: " + userAccount.getAccountNumber());
        }
    }

    public void viewTransaction(String accountNumber) {
        UserAccount userAccount = findUserAccount(accountNumber);
        if (userAccount != null) {
            List<Transaction> transactions = userAccount.getTransactions();
            for (Transaction transaction : transactions){
                System.out.println("Account Number: " + userAccount.getAccountNumber() +
                        ", Transaction ID: " + transaction.getTransactionId() +
                        ", Type: " + transaction.getType() +
                        ", Amount: " + transaction.getAmount() +
                        ", Timestamp: " + transaction.getTimestamp());
            }
        } else {
            throw new IllegalArgumentException("Nomor akun tidak valid.");
        }

    }

     public void viewAllTransactions() {
        System.out.println("\n=== Semua Transaksi ===");
        if (adminAccounts.isEmpty()) {
            throw new IllegalArgumentException("Tidak ada transaksi yang tersimpan.");
        }
        for (UserAccount userAccount : userAccounts) {
            List<Transaction> transactions = userAccount.getTransactions();
            for (Transaction transaction : transactions) {
                System.out.println("Account Number: " + userAccount.getAccountNumber() +
                        ", Transaction ID: " + transaction.getTransactionId() +
                        ", Type: " + transaction.getType() +
                        ", Amount: " + transaction.getAmount() +
                        ", Timestamp: " + transaction.getTimestamp());
            }
        }
    }

    private AdminAccount findAdminAccount(String accountNumber) {
        for (AdminAccount adminAccount : adminAccounts) {
            if (adminAccount.getAccountNumber().equals(accountNumber)) {
                return adminAccount;
            }
        }
        return null;
    }

    private UserAccount findUserAccount(String accountNumber) {
        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getAccountNumber().equals(accountNumber)) {
                return userAccount;
            }
        }
        return null;
    }

    public void withdrawMoney(UserAccount userAccount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan jumlah yang ingin ditarik: ");
        double amount = scanner.nextDouble();

        if (userAccount.withdraw(amount)) {
            System.out.println("Penarikan berhasil.");
        } else {
            System.out.println("Penarikan gagal.");
        }
    }

    public void transferMoney(UserAccount userAccount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nomor akun penerima: ");
        String recipientAccountNumber = scanner.nextLine();
        System.out.print("Masukkan jumlah yang ingin ditransfer: ");
        double amount = scanner.nextDouble();

        if (userAccount.transfer(recipientAccountNumber, amount, userAccounts)) {
            System.out.println("Transfer berhasil.");
        } else {
            System.out.println("Transfer gagal.");
        }
    }

    public UserAccount userLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nomor akun pengguna: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Masukkan PIN: ");
        String pin = scanner.nextLine();

        UserAccount userAccount = findUserAccount(accountNumber);
        if (userAccount != null && userAccount.validatePin(pin)) {
            System.out.println("Login berhasil sebagai pengguna.");
            return userAccount;
        } else {
            throw new IllegalArgumentException("Login gagal. Nomor akun atau PIN salah.");
        }


    }

    public AdminAccount adminLogin(String accountNumber, String pin) {
        try{
            AdminAccount adminAccount = findAdminAccount(accountNumber);
            if (adminAccount != null && adminAccount.validatePin(pin)) {
                System.out.println("Login berhasil sebagai admin.");
                return adminAccount;
            } else {
                throw new IllegalArgumentException("Login gagal. Nomor akun atau PIN salah.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


}
