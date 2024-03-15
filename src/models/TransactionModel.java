package models;

public class TransactionModel {
    private NasabahModel nasabah;
    private final String transactionType;

    public TransactionModel(NasabahModel nasabah, String transactionType) {
        this.nasabah = nasabah;
        this.transactionType = transactionType;
    }

    void setNasabah(NasabahModel nasabah) {
        this.nasabah = nasabah;
    }

    public NasabahModel getNasabah() {
        return nasabah;
    }

    public String getTransactionType() {
        return transactionType;
    }

}
