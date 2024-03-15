package transaction;
import account.UserAccount;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private String accountNumber;

    private UserAccount userAccount;
    private TransactionType type;
    private double amount;
    private Date timestamp;

    public Transaction(String transactionId, String accountNumber, TransactionType type, double amount, Date timestamp) {
        this.userAccount = userAccount;
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public String getType() {
        return type.toString();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTimestamp() {
        return timestamp.toString();
    }

    public String getAmount() {
        return String.valueOf(amount);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

}
