package services;


import models.*;

import java.util.Scanner;

public class UserInterface
{
    public UserInterface(Bank bank, CentralBank centralBank)
    {
        BankUser = bank;
        CentralBankUser = centralBank;
    }

    public Client User;

    public Bank BankUser;

    public CentralBank CentralBankUser;

    public Client getUser() {
        return User;
    }

    public void setUser(Client user) {
        User = user;
    }

    public Bank getBankUser() {
        return BankUser;
    }

    public void setBankUser(Bank bankUser) {
        BankUser = bankUser;
    }

    public CentralBank getCentralBankUser() {
        return CentralBankUser;
    }

    public void setCentralBankUser(CentralBank centralBankUser) {
        CentralBankUser = centralBankUser;
    }

    public void UserWork() throws Exception {
        Scanner console = new Scanner(System.in);
        var clientBuilder = new ClientBuilder();
        System.out.println("Enter a name");
        String name = console.nextLine();;
        System.out.println("Enter a surname");
        String surname = console.nextLine();;
        System.out.println("Enter an address");
        String address =console.nextLine();;
        System.out.println("Enter a passport number");
        String passport = console.nextLine();;
        BankUser.addClient(name, surname, address, passport);
        System.out.println("you are registered in the " + BankUser.getName() + "system");
        while (true)
        {
            boolean q = false;
            System.out.println("Select an action");
            System.out.println("1 - Create a bank account");
            System.out.println("2 - transfer money");
            System.out.println("3 - withdrawal money");
            System.out.println("4 - replenishment money");
            System.out.println("q - end work");
            String action = console.nextLine();;
            switch (action)
            {
                case "1":
                    System.out.println("Choose a type");
                    System.out.println("1 - Debit");
                    System.out.println("2 - Deposit");
                    System.out.println("3 - Credit");
                    switch (console.nextLine())
                    {
                        case "1":
                            BankUser.openAccount(User, TypeOfAccount.Debit, 0);
                            break;
                        case "2":
                            System.out.println("How much money");
                            int moneyDeposit=Integer.parseInt(console.nextLine());
                            BankUser.openAccount(User, TypeOfAccount.Deposit, moneyDeposit);
                            break;
                        case "3":
                            BankUser.openAccount(User, TypeOfAccount.Credit, 0);
                            break;
                        default:
                            System.out.println("try again");
                            break;
                    }

                    break;
                case "2":
                    System.out.println("Write an Id");
                    int id=Integer.parseInt(console.nextLine());
                    System.out.println("How much money");
                    int money=Integer.parseInt(console.nextLine());
                    System.out.println("Write an Id of receiver");
                    int id2 = 0;
                    id2=Integer.parseInt(console.nextLine());
                    for(BankAccount account : BankUser.getBankAccounts()) {
                        if(account.getId() == id)
                            account.transfer(money, id2, CentralBankUser, BankUser, BankUser);
                    }
                    break;
                case "3":
                    System.out.println("Write an Id");
                    id=Integer.parseInt(console.nextLine());
                    System.out.println("How much money");
                    money=Integer.parseInt(console.nextLine());
                    for(BankAccount account : BankUser.getBankAccounts()) {
                        if (account.getId() == id)
                            account.replenishment(money, BankUser);
                    }
                    break;
                case "4":
                    System.out.println("Write an Id");
                    id=Integer.parseInt(console.nextLine());
                    System.out.println("How much money");
                    money=Integer.parseInt(console.nextLine());
                    for(BankAccount account : BankUser.getBankAccounts()) {
                        if (account.getId() == id)
                            account.withdrawal(money, BankUser);
                    }
                    break;
                case "q":
                    q = true;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }

            if (q)
                break;
        }
    }
}