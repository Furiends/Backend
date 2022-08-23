package furiends.backend.controller;

import furiends.backend.model.User;
import furiends.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getUser/{userId}")
    public ResponseEntity<User> get(@PathVariable("userId")String userId) throws NoSuchElementException{
        return ResponseEntity.ok(userService.findUserById(userId).get());

    }

    @GetMapping("getAllUsers")
    public ResponseEntity<ArrayList<User>> getAll() throws RuntimeException {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
