package models;

public abstract class BankAccount {
    protected int id;
    protected double money;
    protected TypeOfAccount type;

    protected double payment;
    protected static int idAccount = 1000000;

    public abstract BankTransaction transfer(int money, int idReceiver, CentralBank centralBank, Bank bankI, Bank bankR) throws Exception;

    public abstract BankTransaction withdrawal(int money, Bank bank) throws Exception;

    public abstract BankTransaction replenishment(int money, Bank bank) throws Exception;

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    protected void setMoney(double money) {
        this.money = money;
    }

    public TypeOfAccount getType() {
        return type;
    }


    public double getPayment() {
        return payment;
    }

    protected void setPayment(double payment) {
        this.payment = payment;
    }

    protected static int getIdAccount() {
        return idAccount;
    }

    protected static void setIdAccount(int idAccount) {
        BankAccount.idAccount = idAccount;
    }
}
