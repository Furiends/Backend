package furiends.backend.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WeChatUtil {

    private static final String WeChat_APPID = "";
    private static final String WeChat_SECRET = "";
    private static final String WeChat_URL_SKEY = "https://api.weixin.qq.com/sns/jscode2session";

    //ask union_id and open_id by code
    public static JSONObject getJSONObject(String code) {
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", WeChat_APPID);
        requestUrlParam.put("secret", WeChat_SECRET);
        requestUrlParam.put("js_code", code);
        requestUrlParam.put("grant_type", "authorization_code");

        return JSON.parseObject(HttpClientUtil.doPost(WeChat_URL_SKEY, requestUrlParam));
    }

    public static String getOpenId(JSONObject jsonObject){
        return jsonObject.getString("open_id");
    }

    public static String getUnionId(JSONObject jsonObject){
        return jsonObject.getString("union_id");
    }
}
