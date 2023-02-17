package furiends.backend.controller;

import furiends.backend.dto.OrganizationRequest;
import furiends.backend.dto.UserRequest;
import furiends.backend.model.Organization;
import furiends.backend.model.User;
import furiends.backend.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private static final Logger logger = LogManager.getLogger(OrganizationController.class);

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id")String userId) {
        User user = null;
        try {
            user = userService.findUserById(userId).get();
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(user);

    }

    // get all users
    @GetMapping("")
    public ResponseEntity<ArrayList<User>> getAllUsers() throws RuntimeException {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    // register a user by WeChat
    @PostMapping("userRegisterByWeChat")
    public ResponseEntity userRegisterByWeChat(@RequestBody UserRequest userRequest, @PathVariable String code){
        try {
            userService.registerUserByWeChat(userRequest, code);
        } catch (Exception e){
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
