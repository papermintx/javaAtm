package controller;

import account.AdminAccount;
import account.UserAccount;
import models.NasabahModel;

import java.util.List;

public class BankController {
    List<NasabahModel> nasabahList;

    private NasabahModel findUserAccount(String accountNumber) {
        for (NasabahModel nasabahModel : nasabahList) {
            if (nasabahModel.getAccountNumber().equals(accountNumber)) {
                return nasabahModel;
            }
        }
        return null;
    }

    public boolean validateNasabahPin( String pin) {
        for (NasabahModel nasabah : nasabahList) {
            if (nasabah.validatePin(pin)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateNasabahAccountNumber(String accountNumber) {
        for (NasabahModel nasabah : nasabahList) {
            if (nasabah.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }


}
