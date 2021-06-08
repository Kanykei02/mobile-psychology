package kg.ItAcademy.mobilepsychology.repository;

import kg.ItAcademy.mobilepsychology.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
}
