package furiends.backend.model;

import com.fasterxml.uuid.Generators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "organization")
public class Organization {
    @Id
    private String id;

    private String name;
    private String description;
    private String icon;
    private String representativeUserId;
    private Date createdTime;
    private Date updatedTime;
    private String province;
    private String city;
    private String district;
    private String address;
    private String phoneNumber;
    private Boolean statusFlag;
    private Date establishDate;
    private Integer rescuePetCounts;
    private Integer volunteerCounts;
    private String lastPostPlacementVisitPetId;
    private Date lastPostPlacementVisitDate;
    private Integer postPlacementVisitCounts;
    private String organizationFromAddress;
    private Integer adoptionPetCounts;
    private String benefits;
    private String wechatOfficialAccountId;
    private String adoptionProcedure;
    @Column(columnDefinition = "TEXT")
    private String adoptionAgreements;

    public Organization() {
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = Generators.timeBasedGenerator().generate().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRepresentativeUserId() {
        return representativeUserId;
    }

    public void setRepresentativeUserId(String representativeUserId) {
        this.representativeUserId = representativeUserId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setCreatedTime() {
        this.createdTime = new Date();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime() {
        new Date();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber(String phoneNumber) {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Boolean statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Integer getRescuePetCounts() {
        return rescuePetCounts;
    }

    public void setRescuePetCounts(Integer rescuePetCounts) {
        this.rescuePetCounts = rescuePetCounts;
    }

    public Integer getVolunteerCounts() {
        return volunteerCounts;
    }

    public void setVolunteerCounts(Integer volunteerCounts) {
        this.volunteerCounts = volunteerCounts;
    }

    public String getLastPostPlacementVisitPetId() {
        return lastPostPlacementVisitPetId;
    }

    public void setLastPostPlacementVisitPetId(String lastPostPlacementVisitPetId) {
        this.lastPostPlacementVisitPetId = lastPostPlacementVisitPetId;
    }

    public Date getLastPostPlacementVisitDate() {
        return lastPostPlacementVisitDate;
    }

    public void setLastPostPlacementVisitDate(Date lastPostPlacementVisitDate) {
        this.lastPostPlacementVisitDate = lastPostPlacementVisitDate;
    }

    public Integer getPostPlacementVisitCounts() {
        return postPlacementVisitCounts;
    }

    public void setPostPlacementVisitCounts(Integer postPlacementVisitCounts) {
        this.postPlacementVisitCounts = postPlacementVisitCounts;
    }

    public String getOrganizationFromAddress() {
        return organizationFromAddress;
    }

    public void setOrganizationFromAddress(String organizationFromAddress) {
        this.organizationFromAddress = organizationFromAddress;
    }

    public Integer getAdoptionPetCounts() {
        return adoptionPetCounts;
    }

    public void setAdoptionPetCounts(Integer adoptionPetCounts) {
        this.adoptionPetCounts = adoptionPetCounts;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getWechatOfficialAccountId() {
        return wechatOfficialAccountId;
    }

    public void setWechatOfficialAccountId(String wechatOfficialAccountId) {
        this.wechatOfficialAccountId = wechatOfficialAccountId;
    }

    public String getAdoptionProcedure() {
        return adoptionProcedure;
    }

    public void setAdoptionProcedure(String adoptionProcedure) {
        this.adoptionProcedure = adoptionProcedure;
    }

    public String getAdoptionAgreements() {
        return adoptionAgreements;
    }

    public void setAdoptionAgreements(String adoptionAgreements) {
        this.adoptionAgreements = adoptionAgreements;
    }


}

