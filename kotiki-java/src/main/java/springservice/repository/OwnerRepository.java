package springservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springservice.entity.CatsEntity;
import springservice.entity.OwnersEntity;

@Repository
public interface OwnerRepository extends JpaRepository<OwnersEntity,Integer> {

    OwnersEntity findByUsername(String username);

    //TODO JPA AUTO
    public default void addOwner(CatsEntity cat, OwnersEntity owner) {
        owner.getOwnersCats().add(cat);
        cat.getCatOwners().add(owner);
    }
}
