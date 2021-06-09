package kg.ItAcademy.mobilepsychology.repository;

import kg.ItAcademy.mobilepsychology.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
