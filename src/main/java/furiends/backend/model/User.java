package furiends.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.uuid.Generators;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(value="用户",description="用户user")
@Entity(name="user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "open_id", nullable = false, length = 64)
    private String openId;

    @Column(name = "union_id", nullable = false, length = 64)
    private String unionId;

    @Column(name = "wechat_id", length = 32)
    private String wechatId;

    @Column(name = "user_role", nullable = false)
    private int userRole;

    @Column(name = "wechat_name", length = 32)
    private String wechatName;

    @Column(name = "application_flag", nullable = false)
    private boolean applicationFlag;

    @Column(name = "mobile_number", nullable = false, length = 13)
    private String mobileNumber;

    @Column(name = "email_address", length = 60)
    private String emailAddress;

    @Column(name = "current_province", length = 32)
    private String currentProvince;

    @Column(name = "current_city", length = 32)
    private String currentCity;

    @Column(name = "identification_number", length = 32)
    private String identificationNumber;

    @Column(name = "blacklist_flag", nullable = false)
    private boolean blacklistFlag;

    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

    public User () {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId() {
        this.id = Generators.timeBasedGenerator().generate().toString();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public boolean isApplicationFlag() {
        return applicationFlag;
    }

    public void setApplicationFlag(boolean applicationFlag) {
        this.applicationFlag = applicationFlag;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCurrentProvince() {
        return currentProvince;
    }

    public void setCurrentProvince(String currentProvince) {
        this.currentProvince = currentProvince;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public boolean isBlacklistFlag() {
        return blacklistFlag;
    }

    public void setBlacklistFlag(boolean blacklistFlag) {
        this.blacklistFlag = blacklistFlag;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
