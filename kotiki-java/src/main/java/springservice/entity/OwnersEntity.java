package springservice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "owners", schema = "public", catalog = "spring")
public class OwnersEntity implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "datebirth", nullable = true)
    private Timestamp datebirth;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "catownership",
            joinColumns = {@JoinColumn(name = "ownerid")},
            inverseJoinColumns = {@JoinColumn(name = "catid")}
    )
    private List<CatsEntity> ownersCats = new ArrayList<>();
    @Basic
    @Column(name = "username", nullable = false, length = -1)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = -1)
    private String password;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "rolesowners",
            joinColumns = {@JoinColumn(name = "ownerid")},
            inverseJoinColumns = {@JoinColumn(name = "roleid")}
    )
    private List<RoleEntity> rolesOwner = new ArrayList<>();

    public OwnersEntity() {
    }

    public OwnersEntity(String name, Timestamp datebirth) {
        this.name = name;
        this.datebirth = datebirth;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEntity> getRolesOwner() {
        return rolesOwner;
    }

    public void setRolesOwner(List<RoleEntity> rolesOwner) {
        this.rolesOwner = rolesOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnersEntity that = (OwnersEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(datebirth, that.datebirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, datebirth);
    }

    public List<CatsEntity> getOwnersCats() {
        return ownersCats;
    }

    public void setOwnersCats(List<CatsEntity> ownersCats) {
        this.ownersCats = ownersCats;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRolesOwner();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
