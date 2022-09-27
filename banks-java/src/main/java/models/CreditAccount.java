package models;


class CreditAccount extends BankAccount {

    public CreditAccount(int money) {
        this.money = money;
        id = idAccount;
        idAccount++;
    }

    {
        type = TypeOfAccount.Credit;
        payment = 0;
    }


    public BankTransaction transfer(int money, int idReceiver, CentralBank centralBank, Bank bankI, Bank bankR) throws Exception {
        return centralBank.transfer(id, money, idReceiver, bankI, bankR);
    }

    public BankTransaction withdrawal(int money, Bank bank) throws Exception {
        return bank.withdrawal(id, money);
    }


    public BankTransaction replenishment(int money, Bank bank) throws Exception {
        return bank.replenishment(id, money);
    }

}