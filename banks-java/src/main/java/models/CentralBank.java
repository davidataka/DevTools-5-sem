package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CentralBank {
    public List<Bank> banks;

    public CentralBank() {
        banks = new ArrayList<>();
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public Bank bankRegistration(String name, double debitInterest, int maxTransaction, ArrayList<double[]> depositInterests, int commission, int creditLimit) throws Exception {
        if (banks.stream().anyMatch(currentBank -> Objects.equals(currentBank.getName(), name))) {
            throw new Exception();
        }

        var bank = new Bank(name, debitInterest, depositInterests, maxTransaction, commission, creditLimit);
        banks.add(bank);
        return bank;
    }

    public void commissionDeduction(Bank bank) {
        bank.commissionDeduction();
    }

    public void accumulationOfInterest(Bank bank) {
        bank.accumulationOfInterest();
    }

    public void interestPayment(Bank bank) {
        bank.interestPayment();
    }

    public BankTransaction transfer(int idInvoker, int money, int idReceiver, Bank bankI, Bank bankR) throws Exception {
        var bankTransaction = new BankTransaction(idInvoker, money, idReceiver, TypeOfTransaction.Transfer);
        bankI.transactions.add(bankTransaction);
        int outOfList = -1;
        int number = outOfList;
        for (int i = 0; i < bankI.getBankAccounts().size(); i++) {
            if (bankI.getBankAccounts().get(i).getId() == idInvoker) {
                number = i;
                break;
            }
        }
        if (number != outOfList) {
            bankI.getBankAccounts().get(number).setMoney(bankI.getBankAccounts().get(number).getMoney() - money);
        } else {
            throw new Exception();
        }

        int numberReceieve = outOfList;
        for (int i = 0; i < bankR.getBankAccounts().size(); i++) {
            if (bankR.getBankAccounts().get(i).getId() == idReceiver) {
                numberReceieve = i;
                break;
            }
        }
        if (numberReceieve != outOfList) {
            bankR.getBankAccounts().get(number).setMoney(bankR.getBankAccounts().get(number).getMoney() + money);
        } else {
            throw new Exception();
        }

        return bankTransaction;
    }
}