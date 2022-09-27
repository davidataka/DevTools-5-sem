package springservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class CatRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("datebirth")
    private Timestamp dataBirth;
    @JsonProperty("breed")
    private String breed;
    @JsonProperty("colorid")
    private Integer colorId;

    public CatRequest() {
    }

    public String getName() {
        return name;
    }

    public Timestamp getDataBirth() {
        return dataBirth;
    }

    public String getBreed() {
        return breed;
    }

    public Integer getColorId() {
        return colorId;
    }
}
