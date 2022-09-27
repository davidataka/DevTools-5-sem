package springservice.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "colors", schema = "public", catalog = "spring")
public class ColorsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    //@Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    //@Column(name = "name", nullable = false, length = -1)
    private String name;

    @OneToMany(mappedBy = "colorsByColorid")
    private Collection<CatsEntity> catsById=new ArrayList<>();

    public ColorsEntity(){
    }

    public ColorsEntity(String name){
        this.name=name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorsEntity that = (ColorsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<CatsEntity> getCatsById() {
        return catsById;
    }

    public void setCatsById(Collection<CatsEntity> catsById) {
        this.catsById = catsById;
    }
}
