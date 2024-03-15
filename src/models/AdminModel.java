package models;

public class AdminModel {
    private final String name;
    private final String accountNumber;
    private final String pin;

    public AdminModel(String name, String accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.name = name;
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
}
