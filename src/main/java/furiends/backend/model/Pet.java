package furiends.backend.model;

import com.fasterxml.uuid.Generators;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "pet")
public class Pet {

    @Id
    private String id;
    private String organizationId;
    private String name;
    // {0: unknown, 1: female, 2: male}
    private Integer sex;
    private Integer age;
    // {1: dog, 2: cat}
    private Integer species;
    private String breed;
    // {1: small; 2: medium; 3: large}
    private Integer size;
    private Integer weight;
    // {0: unknown, 1: yes, 2: no}
    private Integer isVaccinated;
    private Integer isSpayedOrNeutered;
    private Integer isDewormed;
    private String description;
    private String postalCode;
    private String province;
    private String city;
    private String currentAddress;
    private Date rescuedTime;
    private Boolean isPublished;
    private Date postCreatedTime;
    private Date postUpdateTime;
    private Boolean isAdopted;
    private Date adoptedTime;
    private String adoptionApplicationId;
    private Integer returnCount;
    private Integer applicationCount;
    private String lastPostPlacementVisitFormId;
    private Date lastPostPlacementVisitDate;
    private Integer postPlacementVisitCount;

    public Pet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setId() {
        this.id = Generators.timeBasedGenerator().generate().toString();
    }


    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSpecies() {
        return species;
    }

    public void setSpecies(Integer species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getSize() { return size; }

    public void setSize(Integer size) { this.size = size; }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(Integer isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public Integer getIsSpayedOrNeutered() {
        return isSpayedOrNeutered;
    }

    public void setIsSpayedOrNeutered(Integer isSpayedOrNeutered) {
        this.isSpayedOrNeutered = isSpayedOrNeutered;
    }

    public Integer getIsDewormed() {
        return isDewormed;
    }

    public void setIsDewormed(Integer isDewormed) {
        this.isDewormed = isDewormed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Date getRescuedTime() {
        return rescuedTime;
    }

    public void setRescuedTime(Date rescuedTime) {
        this.rescuedTime = rescuedTime;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Boolean getIsAdopted() {
        return isAdopted;
    }

    public void setIsAdopted(Boolean adopted) {
        this.isAdopted = adopted;
    }

    public Date getAdoptedTime() {
        return adoptedTime;
    }

    public void setAdoptedTime(Date adoptedTime) {
        this.adoptedTime = adoptedTime;
    }

    public String getAdoptionApplicationId() {
        return adoptionApplicationId;
    }

    public void setAdoptionApplicationId(String adoptionApplicationId) {
        this.adoptionApplicationId = adoptionApplicationId;
    }

    public Integer getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(Integer returnCount) {
        this.returnCount = returnCount;
    }

    public Integer getApplicationCount() {
        return applicationCount;
    }

    public void setApplicationCount(Integer applicationCount) {
        this.applicationCount = applicationCount;
    }

    public String getLastPostPlacementVisitFormId() {
        return lastPostPlacementVisitFormId;
    }

    public void setLastPostPlacementVisitFormId(String lastPostPlacementVisitFormId) {
        this.lastPostPlacementVisitFormId = lastPostPlacementVisitFormId;
    }

    public Date getLastPostPlacementVisitDate() {
        return lastPostPlacementVisitDate;
    }

    public void setLastPostPlacementVisitDate(Date lastPostPlacementVisitDate) {
        this.lastPostPlacementVisitDate = lastPostPlacementVisitDate;
    }

    public Integer getPostPlacementVisitCount() {
        return postPlacementVisitCount;
    }

    public void setPostPlacementVisitCount(Integer postPlacementVisitCount) {
        this.postPlacementVisitCount = postPlacementVisitCount;
    }

    public Date getPostCreatedTime() {
        return postCreatedTime;
    }

    public void setPostCreatedTime(Date postCreatedTime) {
        this.postCreatedTime = postCreatedTime;
    }

    public void setPostCreatedTime(){
        this.postCreatedTime = new Date();
    }

    public Date getPostUpdateTime() {
        return postUpdateTime;
    }

    public void setPostUpdateTime(){
        this.postUpdateTime = new Date();
    }

    public void setPostUpdateTime(Date postUpdateTime) {
        this.postUpdateTime = postUpdateTime;
    }



}
