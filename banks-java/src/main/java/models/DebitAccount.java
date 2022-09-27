package models;

class DebitAccount extends BankAccount {

    private StatusOfClient Status;

    private int MaxMoneyForTransaction;

    public DebitAccount(StatusOfClient status, int maxMoneyForTransaction) {
        id = idAccount;
        idAccount++;
        Status = status;
        MaxMoneyForTransaction = maxMoneyForTransaction;
    }

    {
        money = 0;
        type = TypeOfAccount.Debit;
        payment = 0;
    }

    public StatusOfClient getStatus() {
        return Status;
    }

    public int getMaxMoneyForTransaction() {
        return MaxMoneyForTransaction;
    }

    public BankTransaction transfer(int money, int idReceiver, CentralBank centralBank, Bank bankI, Bank bankR) throws Exception {
       int noLimit=0;
        if (money > MaxMoneyForTransaction && MaxMoneyForTransaction > noLimit) {
            throw new Exception();
        }
        if (money > this.money) {
            throw new Exception();
        }
        return centralBank.transfer(id, money, idReceiver, bankI, bankR);
    }

    public BankTransaction withdrawal(int money, Bank bank) throws Exception {
        int noLimit=0;
        if (money > MaxMoneyForTransaction && MaxMoneyForTransaction > noLimit) {
            throw new Exception();
        }
        if (money > this.money) {
            throw new Exception();
        }
        return bank.withdrawal(id, money);
    }

    public BankTransaction replenishment(int money, Bank bank) throws Exception {
        return bank.replenishment(id, money);
    }
}