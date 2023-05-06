package furiends.backend.controller;

import furiends.backend.model.User;
import furiends.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/users")
@Api(value = "用户", tags = "用户")
public class UserController {
    private static final Logger logger = LogManager.getLogger(OrganizationController.class);

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    @ApiOperation(value="根据id获取用户")
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
    @ApiOperation(value="获取所有用户列表")
    public ResponseEntity<ArrayList<User>> getAllUsers() throws RuntimeException {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
