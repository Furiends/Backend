package furiends.backend.dto;

public class UserRequest {
    private String code;
    private String wechatId;
    private String username;
    private String mobileNumber;
    private String emailAddress;
    private String province;
    private String city;


    public String getCode() {
        return code;
    }

    public String getWechatId() {
        return wechatId;
    }

    public String getUsername() {
        return username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }


}
