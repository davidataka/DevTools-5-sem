package models;

import java.util.Objects;

public class ClientBuilder {

    private Client client;

    public ClientBuilder() {
        Reset();
    }

    public Client buildNameAndSurname(String name, String surname) throws Exception {
        if (Objects.equals(name, "")) {
            throw new Exception();
        }
        client.setName(name);
        if (Objects.equals(surname, "")) {
            throw new Exception();
        }
        client.setSurname(surname);
        client.setAddress("");
        client.setPassportNumber("");
        return client;

    }

    public Client buildAddress(String address) {
        if (!Objects.equals(address, "")) {
            client.setAddress(address);
        }
        return client;
    }

    public Client buildPassportNumber(String passportNumber) {
        if (passportNumber.matches("\\d{10}")) {
            client.setPassportNumber(passportNumber);
        }
        return client;
    }

    public Client buildStatus() {
        if (!Objects.equals(client.getAddress(), "") && !Objects.equals(client.getPassportNumber(), "")) {
            client.setStatus(StatusOfClient.FullFledged);
        } else {
            client.setStatus(StatusOfClient.Suspicious);
        }
        return client;
    }

    public Client getClient() {
        Client result = client;
        Reset();
        return result;
    }

    private void Reset() {
        client = new Client();
    }
}