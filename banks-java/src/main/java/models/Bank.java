package models;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String name;
    protected List<BankTransaction> transactions;
    private List<BankAccount> bankAccounts;
    private double debitInterest;
    private int maxTransaction;
    private List<double[]> depositInterests;
    private int commission;
    private int creditLimit;
    private List<Client> clients;

    private List<Client> subscribed;

    public Bank(String name, double debitInterest, List<double[]> depositInterests,
                int commission, int maxTransaction, int creditLimit) {
        this.name = name;
        this.debitInterest = debitInterest;
        this.maxTransaction = maxTransaction;
        this.depositInterests = depositInterests;
        this.commission = commission;
        this.creditLimit = creditLimit;
    }

    {
        clients = new ArrayList<>();
        transactions = new ArrayList<>();
        bankAccounts = new ArrayList<>();
        subscribed = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<BankTransaction> getTransactions() {
        return transactions;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    private double getDebitInterest() {
        return debitInterest;
    }

    private int getMaxTransaction() {
        return maxTransaction;
    }

    private List<double[]> getDepositInterests() {
        return depositInterests;
    }

    private int getCommission() {
        return commission;
    }

    private void setCommission(int commission) {
        this.commission = commission;
    }

    private int getCreditLimit() {
        return creditLimit;
    }

    private void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    private List<Client> getClients() {
        return clients;
    }

    private List<Client> getSubscribed() {
        return subscribed;
    }


    public Client addClient(String name, String surname, String address, String passportNumber) throws Exception {
        var clientBuilder = new ClientBuilder();
        clientBuilder.buildNameAndSurname(name, surname);
        clientBuilder.buildAddress(address);
        clientBuilder.buildPassportNumber(passportNumber);
        clientBuilder.buildStatus();
        Client client = clientBuilder.getClient();
        clients.add(client);
        return client;
    }

    public BankAccount openAccount(Client client, TypeOfAccount typeOfAccount, double money) throws Exception {
        if (client.bankAccounts.stream().anyMatch(current -> current.type == typeOfAccount)) {
            throw new Exception();
        }

        BankAccount bankAccount = createAccount(typeOfAccount, client, money);

        bankAccounts.add(bankAccount);
        client.bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public BankAccount createAccount(TypeOfAccount typeOfAccount, Client client, double money) throws Exception {
        BankAccount bankAccount;
        if (typeOfAccount == TypeOfAccount.Credit) {
            if (client.getStatus() == StatusOfClient.Suspicious) {
                throw new Exception();
            }
            bankAccount = new CreditAccount(creditLimit);
            bankAccounts.add(bankAccount);
            client.bankAccounts.add(bankAccount);
            return bankAccount;
        } else if (typeOfAccount == TypeOfAccount.Debit) {
            if (client.getStatus() == StatusOfClient.Suspicious) {
                bankAccount = new DebitAccount(client.getStatus(), maxTransaction);
            } else {
                bankAccount = new DebitAccount(StatusOfClient.FullFledged, 0);
            }
        } else {
            if (client.getStatus() == StatusOfClient.Suspicious) {
                bankAccount = new DepositAccount(money, client.getStatus(), maxTransaction, new Date());
            } else {
                bankAccount = new DepositAccount(money, StatusOfClient.FullFledged, 0, new java.util.Date());
            }
        }
        return bankAccount;
    }

    public List<Client> subscribeClient(Client client) {
        if (subscribed.stream().anyMatch(currentClient -> currentClient == client)) {
            return subscribed;
        }

        client.setNotificationsPermission(true);
        subscribed.add(client);
        return subscribed;
    }

    public int changeCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
        for (Client client : subscribed) {
            for (BankAccount variableAccount : client.bankAccounts) {
                if (variableAccount.type == TypeOfAccount.Credit) {
                    client.setNotifications(client.getNotifications() + "Credit Limit");
                }
            }
        }

        return this.creditLimit;
    }

    public int changeCommission(int commission) {
        this.commission = commission;
        for (Client client : subscribed) {
            for (BankAccount variableAccount : client.bankAccounts) {
                if (variableAccount.type == TypeOfAccount.Credit) {
                    client.setNotifications(client.getNotifications() + "Commission");
                }
            }
        }

        return this.commission;
    }

    public List<BankAccount> commissionDeduction() {
        int valueForCommission = 0;
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.getType() == TypeOfAccount.Credit && bankAccount.getMoney() < valueForCommission) {
                bankAccount.setMoney(bankAccount.getMoney() - commission);
            }
        }
        return bankAccounts;
    }

    public List<BankAccount> accumulationOfInterest() {
        int valueForInterest = 0;
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.type != TypeOfAccount.Credit && bankAccount.getMoney() > valueForInterest) {
                bankAccount.setPayment(bankAccount.getPayment() + debitInterest * bankAccount.getMoney());
            }
        }
        return bankAccounts;
    }

    public List<BankAccount> interestPayment() {
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.type != TypeOfAccount.Credit) {
                bankAccount.setMoney(bankAccount.getMoney() + bankAccount.getPayment());
                bankAccount.setPayment(0);
            }
        }
        return bankAccounts;
    }

    public BankTransaction replenishment(int idInvoker, double money) throws Exception {
        var bankTransaction = new BankTransaction(idInvoker, money, TypeOfTransaction.Replenishment);
        transactions.add(bankTransaction);
        int outOfList = -1;
        int number = outOfList;
        for (int i = 0; i < bankAccounts.size(); i++) {
            if (bankAccounts.get(i).getId() == idInvoker) {
                number = i;
                break;
            }
        }
        if (number != outOfList) {
            bankAccounts.get(number).setMoney(bankAccounts.get(number).getMoney() + money);
        } else {
            throw new Exception();
        }

        return bankTransaction;
    }

    public BankTransaction withdrawal(int idInvoker, int money) throws Exception {
        var bankTransaction = new BankTransaction(idInvoker, money, TypeOfTransaction.Withdrawal);
        transactions.add(bankTransaction);
        int outOfList = -1;

        int number = outOfList;
        for (int i = 0; i < bankAccounts.size(); i++) {
            if (bankAccounts.get(i).getId() == idInvoker) {
                number = i;
                break;
            }
        }
        if (number != outOfList) {
            bankAccounts.get(number).setMoney(bankAccounts.get(number).getMoney() - money);
        } else {
            throw new Exception();
        }

        return bankTransaction;
    }

    public void cancellationOfTheTransaction(BankTransaction bankTransaction, CentralBank centralBank) throws Exception {
        if (transactions.stream().anyMatch(acc -> acc == bankTransaction)) {
            transactions.remove(bankTransaction);
        } else {
            throw new Exception();
        }
        int outOfList=-1;
        if (bankTransaction.getTypeOfTransaction() == TypeOfTransaction.Transfer ||
                bankTransaction.getTypeOfTransaction() == TypeOfTransaction.Replenishment) {
            int number = outOfList;
            for (int i = 0; i < bankAccounts.size(); i++) {
                if (bankAccounts.get(i).getId() == bankTransaction.getIdInvoker()) {
                    number = i;
                    break;
                }
            }
            bankAccounts.get(number).setMoney(bankAccounts.get(number).getMoney() + bankTransaction.getMoney());
            if (bankTransaction.getTypeOfTransaction() == TypeOfTransaction.Transfer) {
                for (Bank bank : centralBank.banks) {
                    number = outOfList;
                    for (int i = 0; i < bankAccounts.size(); i++) {
                        if (bankAccounts.get(i).getId() == bankTransaction.getIdInvoker()) {
                            number = i;
                            break;
                        }
                    }
                    if (number != outOfList) {
                        bankAccounts.get(number).setMoney(bankAccounts.get(number).getMoney() - bankTransaction.getMoney());
                    }
                }
            }
        } else {
            int number = outOfList;
            for (int i = 0; i < bankAccounts.size(); i++) {
                if (bankAccounts.get(i).getId() == bankTransaction.getIdInvoker()) {
                    number = i;
                    break;
                }
            }
            bankAccounts.get(number).setMoney(bankAccounts.get(number).getMoney() + bankTransaction.getMoney());
        }
    }
}