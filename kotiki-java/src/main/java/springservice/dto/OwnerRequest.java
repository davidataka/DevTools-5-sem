package springservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class OwnerRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("datebirth")
    private Timestamp dateBirth;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public Timestamp getDateBirth() {
        return dateBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public OwnerRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateBirth(Timestamp dateBirth) {
        this.dateBirth = dateBirth;
    }
}
