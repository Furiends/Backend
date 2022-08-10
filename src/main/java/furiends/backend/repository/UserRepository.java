package furiends.backend.repository;

import furiends.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User,String> {

    @Override
    ArrayList<User> findAll();
}
