import account.AdminAccount;
import account.UserAccount;
import bank.Bank;
import transaction.Transaction;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Bank bank = new Bank();

        // Menambahkan beberapa akun admin ke dalam sistem
        AdminAccount admin1 = new AdminAccount("111", "admin1pin", 5000.0);
        AdminAccount admin2 = new AdminAccount("222", "admin2pin", 7000.0);
        bank.setAdminAccounts(admin1);
        bank.setAdminAccounts(admin2);


        // Menambahkan beberapa akun pengguna ke dalam sistem
        UserAccount user1 = new UserAccount("001", "user1pin", 2000.0);
        UserAccount user2 = new UserAccount("002", "user2pin", 3000.0);
        bank.setUserAccounts(user1);
        bank.setUserAccounts(user2);

        // Menampilkan menu interaktif
        displayMenu(bank, scanner);
    }

    static void displayMenu(Bank bank, Scanner scanner) {
        int choice;
        boolean isRunning = true;
        String accountNumber;
        String pin;


        while(isRunning) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Create User Account");
            System.out.println("4. Exit");
            System.out.print("Masukkan pilihan Anda: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Masukkan nomor akun admin: ");
                        accountNumber = scanner.next();
                        System.out.print("Masukkan PIN: ");
                        pin = scanner.next();

                        adminMenu(bank.adminLogin(accountNumber,pin), bank);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        userMenu(bank.userLogin(), bank);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        bank.createUserAccount();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }


    static void adminMenu(AdminAccount adminAccount, Bank bank) {
        try {
            Scanner scanner = new Scanner(System.in);
            int choice;
            boolean isRunning = true;

            while(isRunning) {
                System.out.println("\n=== Admin Menu ===");
                System.out.println("1. Lihat semua akun");
                System.out.println("2. Lihat riwayat transaksi pengguna");
                System.out.println("3. Lihat semua transaksi");
                System.out.println("4. Logout");
                System.out.print("Masukkan pilihan Anda: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        bank.displayAllAccounts();
                        break;
                    case 2:
                        try {
                            String accountNumber;
                            System.out.print("Masukkan nomor akun pengguna: ");
                            accountNumber = scanner.nextLine();
                            bank.viewTransaction(accountNumber);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            bank.viewAllTransactions();
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        isRunning = false;
                        System.out.println("Logout berhasil.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }


    private static void userMenu(UserAccount userAccount, Bank bank) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. Lihat Saldo");
            System.out.println("2. Lihat Riwayat Transaksi");
            System.out.println("3. Transfer Uang");
            System.out.println("4. Tarik Uang");
            System.out.println("5. Logout");
            System.out.print("Masukkan pilihan Anda: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.println("Saldo Anda saat ini: " + userAccount.getBalance());
                    break;
                case 2:
                    List<Transaction> transactions = userAccount.getTransactions();
                    System.out.println("\n=== Riwayat Transaksi ===");
                    for (Transaction transaction : transactions) {
                        System.out.println("Transaction ID: " + transaction.getTransactionId() +
                                ", Type: " + transaction.getType() +
                                ", Amount: " + transaction.getAmount() +
                                ", Timestamp: " + transaction.getTimestamp());
                    }
                    break;
                case 3:
                    bank.transferMoney(userAccount);
                    break;
                case 4:
                    bank.withdrawMoney(userAccount);
                    break;
                case 5:
                    System.out.println("Logout berhasil.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (choice != 5);
    }






}
