package furiends.backend.service;

import furiends.backend.model.User;
import furiends.backend.repository.UserRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User mockUser = new User();

    private ArrayList<User> mockUserList = new ArrayList<User>() {{
        add(mockUser);
    }};

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllUsers() {
        when(userRepository.findAll()).thenReturn(mockUserList);
        ArrayList<User> userList = userService.findAllUsers();
        Assert.assertEquals(userList.size(), mockUserList.size());
        Assert.assertEquals(userList.get(0).getId(), mockUserList.get(0).getId());
    }

    @Test
    void testFindUserById() {
        mockUser.setId("test_user_1");
        when(userRepository.findById("test_user_1")).thenReturn(java.util.Optional.ofNullable(mockUser));
        Optional<User> user = userService.findUserById("test_user_1");
        Assert.assertEquals(user.get().getId(), mockUser.getId());
    }
}
