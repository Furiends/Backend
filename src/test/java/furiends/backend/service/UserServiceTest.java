package furiends.backend.service;

import furiends.backend.model.User;
import furiends.backend.repository.UserRepository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User mockUser = new User("test_user",1,true,
            "12345",false);

    private ArrayList<User> mockUserList = new ArrayList<User>() {{
        add(mockUser);
    }};

    @Test
    void testFindAllUsers() {
        when(userRepository.findAll()).thenReturn(mockUserList);
        ArrayList<User> userList = userService.findAllUsers();
        Assert.assertEquals(userList.size(), mockUserList.size());
        Assert.assertEquals(userList.get(0).getWechatId(), mockUserList.get(0).getWechatId());
    }

    @Test
    void testFindUserById() {
        when(userRepository.findById("test_user")).thenReturn(java.util.Optional.ofNullable(mockUser));
        Optional<User> user = userService.findUserById("test_user");
        Assert.assertEquals(user.get().getWechatId(), mockUser.getWechatId());
    }
}