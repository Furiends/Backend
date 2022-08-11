package furiends.backend.controller;

import furiends.backend.model.User;
import furiends.backend.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private User mockUser = new User("test_user_1",1,true,
            "12345",false);

    private ArrayList<User> mockUserList = new ArrayList<User>() {{
        add(mockUser);
    }};

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet() {
        when(userService.findUserById("test_user_1")).thenReturn(Optional.ofNullable(mockUser));
        ResponseEntity<Optional<User>> response = userController.get("test_user_1");
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(response.getBody().get().getWechatId(), mockUser.getWechatId());
    }

    @Test
    void testGetAll() {
        when(userService.findAllUsers()).thenReturn(mockUserList);
        ResponseEntity<ArrayList<User>> response = userController.getAll();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(response.getBody().get(0).getWechatId(), mockUserList.get(0).getWechatId());
    }
}