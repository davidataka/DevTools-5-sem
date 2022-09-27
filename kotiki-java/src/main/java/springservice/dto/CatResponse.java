package springservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import springservice.entity.CatsEntity;
import springservice.entity.OwnersEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CatResponse {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("datebirth")
    private Timestamp dataBirth;
    @JsonProperty("breed")
    private String breed;
    @JsonProperty("colorid")
    private Integer colorId;
    @JsonProperty("ownersid")
    private List<Integer> owners=new ArrayList<>();
    @JsonProperty("friendsid")
    private List<Integer> friends=new ArrayList<>();


    public CatResponse() {
    }

    public CatResponse(CatsEntity cat) {
        this.id=cat.getId();
        this.name=cat.getName();
        this.dataBirth=cat.getDatebirth();
        this.breed=cat.getBreed();
        this.colorId=cat.getColorid();
        for(OwnersEntity i:cat.getCatOwners()){
            owners.add(i.getId());
        }
        for(CatsEntity i:cat.getFriends()){
            friends.add(i.getId());
        }
        for(CatsEntity i:cat.getFriendsOf()){
            friends.add(i.getId());
        }
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

