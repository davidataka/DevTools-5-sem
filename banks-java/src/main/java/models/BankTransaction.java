package models;

public class BankTransaction {

    private int idInvoker;
    private double money;
    private TypeOfTransaction type;
    private int idReceiver = 0;

    public BankTransaction(int idInvoker, double money, TypeOfTransaction type) {
        this.idInvoker = idInvoker;
        this.money = money;
        this.type = type;
    }

    public BankTransaction(int idInvoker, int money, int idReceiver, TypeOfTransaction type) {
        this.idInvoker = idInvoker;
        this.money = money;
        this.idReceiver = idReceiver;
        this.type = type;
    }

    public int getIdInvoker() {
        return idInvoker;
    }

    public double getMoney() {
        return money;
    }

    public TypeOfTransaction getTypeOfTransaction() {
        return type;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

}
