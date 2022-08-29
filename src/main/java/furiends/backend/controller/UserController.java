package furiends.backend.controller;

import furiends.backend.model.User;
import furiends.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@RestController
@Api(value = "用户", tags = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getUser/{userId}")
    @ApiOperation(value="根据id获取用户")
    public ResponseEntity<User> get(@PathVariable("userId")String userId) throws NoSuchElementException{
        return ResponseEntity.ok(userService.findUserById(userId).get());

    }

    @GetMapping("getAllUsers")
    @ApiOperation(value="获取所有用户列表")
    public ResponseEntity<ArrayList<User>> getAll() throws RuntimeException {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
