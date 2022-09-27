package springservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springservice.entity.ColorsEntity;

@Repository
public interface ColorRepository extends JpaRepository<ColorsEntity, Integer> {
}

