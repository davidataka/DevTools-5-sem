package springservice.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cats", schema = "public", catalog = "spring")
public class CatsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "datebirth", nullable =true)
    private Timestamp datebirth;
    @Basic
    @Column(name = "breed", nullable = true, length = -1)
    private String breed;

    @ManyToMany(mappedBy = "ownersCats")
    private Collection<OwnersEntity> catOwners=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "colorid", referencedColumnName = "id")
    private ColorsEntity colorsByColorid;

    @Basic
    @Column(insertable = false, updatable = false, nullable = true)
    private Integer colorid;

    @ManyToMany
    @JoinTable(name = "friendshipofcats",
            joinColumns = @JoinColumn(name = "firstcatid", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "secondcatid", referencedColumnName = "id", nullable = false))
    private Collection<CatsEntity> friends=new ArrayList<>();

    @ManyToMany(mappedBy = "friends")
    private Collection<CatsEntity> friendsOf=new ArrayList<>();

    public CatsEntity(){}
    public CatsEntity(String name, Timestamp datebirth, String breed, Integer colorid){
        this.name=name;
        this.datebirth=datebirth;
        this.breed=breed;
        this.colorid=colorid;
    }

   // public void addFriend(CatsEntity cat) {
   //     this.friends.add(cat);
   //     cat.getFriendsOf().add(this);
   // }

    public Collection<CatsEntity> getFriendsOf() {
        return friendsOf;
    }

    public void setFriendsOf(Collection<CatsEntity> friendsOf) {
        this.friendsOf = friendsOf;
    }

    public Collection<CatsEntity> getFriends() {
        return friends;
    }

    public void setFriends(Collection<CatsEntity> friends) {
        this.friends = friends;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(Timestamp datebirth) {
        this.datebirth = datebirth;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getColorid() {
        return colorid;
    }

    public void setColorid(Integer colorid) {
        this.colorid = colorid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatsEntity cats = (CatsEntity) o;
        return Objects.equals(id, cats.id) && Objects.equals(name, cats.name) && Objects.equals(datebirth, cats.datebirth) && Objects.equals(breed, cats.breed) && Objects.equals(colorid, cats.colorid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, datebirth, breed, colorid);
    }

    public Collection<OwnersEntity> getCatOwners() {
        return catOwners;
    }

    public void setCatOwners(Collection<OwnersEntity> catOwners) {
        this.catOwners = catOwners;
    }

    public ColorsEntity getColorsByColorid() {
        return colorsByColorid;
    }

    public void setColorsByColorid(ColorsEntity colorsByColorid) {
        this.colorsByColorid = colorsByColorid;
    }


}
