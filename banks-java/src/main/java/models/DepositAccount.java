package models;

import java.util.Date;

public class DepositAccount extends BankAccount {

    private Date EndDepositTime;

    private StatusOfClient Status;

    private int MaxMoneyForTransaction;

    public DepositAccount(double money, StatusOfClient status, int maxMoneyForTransaction, Date endDepositTime) {
        this.money = money;
        id = idAccount;
        idAccount++;
        EndDepositTime = endDepositTime;
        Status = status;
        MaxMoneyForTransaction = maxMoneyForTransaction;
    }


    {
        type = TypeOfAccount.Deposit;
        payment = 0;
    }

    public Date getEndDepositTime() {
        return EndDepositTime;
    }


    public StatusOfClient getStatus() {
        return Status;
    }

    public int getMaxMoneyForTransaction() {
        return MaxMoneyForTransaction;
    }

    public BankTransaction transfer(int money, int idReceiver, CentralBank centralBank, Bank bankI, Bank bankR) throws Exception {
        if (money > MaxMoneyForTransaction) {
            throw new Exception();
        }
        if (money > this.money) {
            throw new Exception();
        }
        if (new Date().before(EndDepositTime)) {
            throw new Exception();
        }
        return centralBank.transfer(id, money, idReceiver, bankI, bankR);
    }

    public BankTransaction withdrawal(int money, Bank bank) throws Exception {
        if (money > MaxMoneyForTransaction) {
            throw new Exception();
        }
        if (money > this.money) {
            throw new Exception();
        }
        if (new Date().after(EndDepositTime)) {
            throw new Exception();
        }
        return bank.withdrawal(id, money);
    }

    public BankTransaction replenishment(int money, Bank bank) throws Exception {
        return bank.replenishment(id, money);
    }
}