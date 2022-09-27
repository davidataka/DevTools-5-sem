package springservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springservice.entity.CatsEntity;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<CatsEntity, Integer> {

    public List<CatsEntity> findByColorid(Integer colorId);

    //TODO
    public default void addFriendcat(CatsEntity cat, CatsEntity cat1) {

        cat.getFriends().add(cat1);
        //cat1.getFriendsOf().add(cat);
    }
}
