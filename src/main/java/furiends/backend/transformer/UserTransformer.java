package furiends.backend.transformer;

import com.alibaba.fastjson.JSONObject;
import furiends.backend.dto.UserRequest;
import furiends.backend.model.User;
import furiends.backend.utils.WeChatUtil;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer {
    public void fromUserRequestToUser(UserRequest userRequest, User newUser){
        newUser.setWechatId(userRequest.getWechatId());
        newUser.setUser_name(userRequest.getUsername());
        newUser.setEmailAddress(userRequest.getEmailAddress());
        newUser.setMobileNumber(userRequest.getMobileNumber());
        newUser.setCurrentProvince(userRequest.getProvince());
        newUser.setCurrentCity(userRequest.getCity());
    }
}
