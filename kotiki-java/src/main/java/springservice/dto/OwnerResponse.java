package springservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import springservice.entity.CatsEntity;
import springservice.entity.OwnersEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OwnerResponse {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("datebirth")
    private Timestamp dataBirth;
    @JsonProperty("catsid")
    private List<Integer> catsId=new ArrayList<>();


    public OwnerResponse() {
    }

    public OwnerResponse(OwnersEntity owner) {
        this.id=owner.getId();
        this.name=owner.getName();
        this.dataBirth=owner.getDatebirth();
        for(CatsEntity i:owner.getOwnersCats()){
            catsId.add(i.getId());
        }
    }

    public String getName() {
        return name;
    }

    public Timestamp getDataBirth() {
        return dataBirth;
    }
}
