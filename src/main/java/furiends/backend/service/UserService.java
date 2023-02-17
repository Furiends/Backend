package furiends.backend.service;

import com.alibaba.fastjson.JSONObject;
import furiends.backend.dto.UserRequest;
import furiends.backend.model.Organization;
import furiends.backend.model.User;
import furiends.backend.repository.UserRepository;
import furiends.backend.transformer.UserTransformer;
import furiends.backend.utils.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;
    @Autowired
    private UserTransformer userTransformer;

    public ArrayList<User> findAllUsers () {
        return userRepository.findAll();
    }

    public Optional<User> findUserById (String userId) {
        return userRepository.findById(userId);
    }

    public User createUser(UserRequest userRequest){
        User newUser = new User();
        newUser.setId();
        newUser.setUserRole(1);//默认为普通用户
        newUser.setCreatedTime();
        userTransformer.fromUserRequestToUser(userRequest, newUser);
        return newUser;
    }


    public void registerUserByWeChat(UserRequest userRequest, String code){
        User user = createUser(userRequest);

        JSONObject jsonObject = WeChatUtil.getJSONObject(code);
        String unionId = WeChatUtil.getUnionId(jsonObject);
        String openId = WeChatUtil.getOpenId(jsonObject);

        if(unionId.isBlank() || openId.isBlank()){
            throw new RuntimeException();
        }
        user.setUnionId(unionId);
        user.setOpenId(openId);
        user.setUpdatedTime();
        userRepository.save(user);
    }

    public User loginUserByWeChat(String code){
        JSONObject jsonObject = WeChatUtil.getJSONObject(code);
        String openId = WeChatUtil.getOpenId(jsonObject);
        String unionId = WeChatUtil.getUnionId(jsonObject);

        if(openId.isBlank() || unionId.isBlank()){
            throw new RuntimeException();
        }
        User user = userRepository.findUserByOpenIdAndUnionId(openId, unionId);
        if(user == null){
            throw new RuntimeException();
        }

        user.setUpdatedTime();
        userRepository.save(user);
        return user;
    }
}

