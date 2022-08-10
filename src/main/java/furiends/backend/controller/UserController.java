package furiends.backend.controller;

import furiends.backend.model.User;
import furiends.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getUser/{userId}")
    public Optional<User> get(@PathVariable("userId")String userId){
        return userService.findUserById(userId);
    }

    @GetMapping("getAllUsers")
    public ArrayList<User> getAll(){
        ArrayList<User> userList = userService.findAllUsers();
        return userList;
    }
}
