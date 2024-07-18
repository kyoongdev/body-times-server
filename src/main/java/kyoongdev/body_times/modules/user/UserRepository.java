package kyoongdev.body_times.modules.user;


import java.util.UUID;
import kyoongdev.body_times.modules.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
