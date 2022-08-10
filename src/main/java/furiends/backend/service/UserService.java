package furiends.backend.service;

import furiends.backend.model.User;
import furiends.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    public ArrayList<User> findAllUsers () {
        return userRepository.findAll();
    }

    public Optional<User> findUserById (String userId) {
        return userRepository.findById(userId);
    }
}

