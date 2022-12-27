package furiends.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name="application")
public class Application {
    @Id
    private String applicationId;
    private String userId;
    private String petId;
    // 0: Single; 1: Married; 2: Divorced
    private Integer maritalStatus;
    private String occupation;
    private String livingCondition;
    private boolean adoptionExperienceFlag;
    private boolean abandonFlag;
    // 0: Invalid; 1: Pending; 2: Complete; 3: Rejected;
    private Integer applicationStatus;
    private Date applicationStatusUpdateTime;
    private Integer adoptionTimeCurrentDays;
    private String rejectionReason;
    private Date createdTime;
    private Date updatedTime;
    private String postPlacementVisitFormId;
    private Integer adoptionPostPlacementVisitCount;
    private boolean hasObtainedRoommatesConsent;
    private Integer budgetForPet;
    private boolean agreedOnPostPlacementVisit;
    private String dogCatPreference;
    private String color;
    private String incomeSource;
    private String breedPreference;
    // 0: Small; 1: Medium; 2: Large;
    private Integer dogSize;
    private String organizationId;

    public Application() {
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getLivingCondition() {
        return livingCondition;
    }

    public void setLivingCondition(String livingCondition) {
        this.livingCondition = livingCondition;
    }

    public boolean isAdoptionExperienceFlag() {
        return adoptionExperienceFlag;
    }

    public void setAdoptionExperienceFlag(boolean adoptionExperienceFlag) {
        this.adoptionExperienceFlag = adoptionExperienceFlag;
    }

    public boolean isAbandonFlag() {
        return abandonFlag;
    }

    public void setAbandonFlag(boolean abandonFlag) {
        this.abandonFlag = abandonFlag;
    }

    public Integer getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Integer applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Date getApplicationStatusUpdateTime() {
        return applicationStatusUpdateTime;
    }

    public void setApplicationStatusUpdateTime(Date applicationStatusUpdateTime) {
        this.applicationStatusUpdateTime = applicationStatusUpdateTime;
    }

    public Integer getAdoptionTimeCurrentDays() {
        return adoptionTimeCurrentDays;
    }

    public void setAdoptionTimeCurrentDays(Integer adoptionTimeCurrentDays) {
        this.adoptionTimeCurrentDays = adoptionTimeCurrentDays;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
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

    public String getPostPlacementVisitFormId() {
        return postPlacementVisitFormId;
    }

    public void setPostPlacementVisitFormId(String postPlacementVisitFormId) {
        this.postPlacementVisitFormId = postPlacementVisitFormId;
    }

    public Integer getAdoptionPostPlacementVisitCount() {
        return adoptionPostPlacementVisitCount;
    }

    public void setAdoptionPostPlacementVisitCount(Integer adoptionPostPlacementVisitCount) {
        this.adoptionPostPlacementVisitCount = adoptionPostPlacementVisitCount;
    }

    public boolean isHasObtainedRoommatesConsent() {
        return hasObtainedRoommatesConsent;
    }

    public void setHasObtainedRoommatesConsent(boolean hasObtainedRoommatesConsent) {
        this.hasObtainedRoommatesConsent = hasObtainedRoommatesConsent;
    }

    public Integer getBudgetForPet() {
        return budgetForPet;
    }

    public void setBudgetForPet(Integer budgetForPet) {
        this.budgetForPet = budgetForPet;
    }

    public boolean isAgreedOnPostPlacementVisit() {
        return agreedOnPostPlacementVisit;
    }

    public void setAgreedOnPostPlacementVisit(boolean agreedOnPostPlacementVisit) {
        this.agreedOnPostPlacementVisit = agreedOnPostPlacementVisit;
    }

    public String getDogCatPreference() {
        return dogCatPreference;
    }

    public void setDogCatPreference(String dogCatPreference) {
        this.dogCatPreference = dogCatPreference;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public String getBreedPreference() {
        return breedPreference;
    }

    public void setBreedPreference(String breedPreference) {
        this.breedPreference = breedPreference;
    }

    public Integer getDogSize() {
        return dogSize;
    }

    public void setDogSize(Integer dogSize) {
        this.dogSize = dogSize;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}
