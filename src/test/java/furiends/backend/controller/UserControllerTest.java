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

    private User mockUser = new User();

    private ArrayList<User> mockUserList = new ArrayList<User>() {{
        add(mockUser);
    }};

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        mockUser.setId("test_user_1");
        when(userService.findUserById("test_user_1")).thenReturn(Optional.ofNullable(mockUser));
        ResponseEntity<User> response = userController.getUserById("test_user_1");
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(response.getBody().getId(), mockUser.getId());
    }

    @Test
    void testGetAllUsers() {
        when(userService.findAllUsers()).thenReturn(mockUserList);
        ResponseEntity<ArrayList<User>> response = userController.getAllUsers();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(response.getBody().get(0).getId(), mockUserList.get(0).getId());
    }
}
