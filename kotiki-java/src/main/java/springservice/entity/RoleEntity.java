package springservice.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles", schema = "public", catalog = "spring")
public class RoleEntity implements GrantedAuthority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;


    @ManyToMany(mappedBy = "rolesOwner")
    private List<OwnersEntity> owners=new ArrayList<>();
    public RoleEntity() {
    }

    public RoleEntity(Long id) {
        this.id = id;
    }

    public RoleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OwnersEntity> getOwners() {
        return owners;
    }

    public void setOwners(List<OwnersEntity> owners) {
        this.owners = owners;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
