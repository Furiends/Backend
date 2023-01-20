package furiends.backend.dto;

import java.util.Date;

public class OrganizationRequest {
    private String name;
    private String description;
    private String icon;
    private String representativeUserId;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getRepresentativeUserId() {
        return representativeUserId;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getStatusFlag() {
        return statusFlag;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public Integer getRescuePetCounts() {
        return rescuePetCounts;
    }

    public Integer getVolunteerCounts() {
        return volunteerCounts;
    }

    public String getLastPostPlacementVisitPetId() {
        return lastPostPlacementVisitPetId;
    }

    public Date getLastPostPlacementVisitDate() {
        return lastPostPlacementVisitDate;
    }

    public Integer getPostPlacementVisitCounts() {
        return postPlacementVisitCounts;
    }

    public String getOrganizationFromAddress() {
        return organizationFromAddress;
    }

    public Integer getAdoptionPetCounts() {
        return adoptionPetCounts;
    }

    public String getBenefits() {
        return benefits;
    }

    public String getWechatOfficialAccountId() {
        return wechatOfficialAccountId;
    }
}
