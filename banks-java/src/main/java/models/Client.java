package models;

import java.util.ArrayList;
import java.util.List;

public class Client {

    {
        status = StatusOfClient.Suspicious;
        notificationsPermission = false;

        notifications = "";
    }

    private String name;

    private String surname;

    private String address;

    private String passportNumber;

    private boolean notificationsPermission;


    private String notifications;

    private StatusOfClient status;
    public List<BankAccount> bankAccounts = new ArrayList<>();

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    protected void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    protected void setAddress(String address) {
        this.address = address;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public boolean isNotificationsPermission() {
        return notificationsPermission;
    }

    protected void setNotificationsPermission(boolean notificationsPermission) {
        this.notificationsPermission = notificationsPermission;
    }

    public String getNotifications() {
        return notifications;
    }

    protected void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public StatusOfClient getStatus() {
        return status;
    }

    protected void setStatus(StatusOfClient status) {
        this.status = status;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
