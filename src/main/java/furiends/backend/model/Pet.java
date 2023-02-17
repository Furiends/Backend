package furiends.backend.model;

import com.fasterxml.uuid.Generators;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "pet")
public class Pet {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "organization_id", nullable = false, length = 64)
    private String organizationId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sex", nullable = false)
    private Integer sex;  // {0: unknown, 1: female, 2: male}

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "species", nullable = false)
    private Integer species;  // {1: dog, 2: cat}

    @Column(name = "breed", nullable = false, length = 64)
    private String breed;

    @Column(name = "size", nullable = false)
    private Integer size; // {1: small; 2: medium; 3: large}

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "is_vaccinated", nullable = false)
    private Integer isVaccinated;  // {0: unknown, 1: yes, 2: no}

    @Column(name = "is_spayed_or_neutered", nullable = false)
    private Integer isSpayedOrNeutered;  // {0: unknown, 1: yes, 2: no}

    @Column(name = "is_dewormed", nullable = false)
    private Integer isDewormed;  // {0: unknown, 1: yes, 2: no}

    @Column(name = "description")
    private String description;

    @Column(name = "postal_code", nullable = false, length = 64)
    private String postalCode;

    @Column(name = "province", nullable = false, length = 64)
    private String province;

    @Column(name = "city", nullable = false, length = 64)
    private String city;

    @Column(name = "current_address", nullable = false)
    private String currentAddress;

    @Column(name = "rescued_time", nullable = false)
    private Date rescuedTime;

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished;

    @Column(name = "post_created_time")
    private Date postCreatedTime;

    @Column(name = "post_update_time")
    private Date postUpdateTime;

    @Column(name = "is_adopted")
    private Boolean isAdopted;

    @Column(name = "adopted_time")
    private Date adoptedTime;

    @Column(name = "adoption_application_id")
    private String adoptionApplicationId;

    @Column(name = "return_count")
    private Integer returnCount;

    @Column(name = "application_count")
    private Integer applicationCount;

    @Column(name = "last_post_placement_visit_form_id")
    private String lastPostPlacementVisitFormId;

    @Column(name = "last_post_placement_visit_date")
    private Date lastPostPlacementVisitDate;

    @Column(name = "post_placement_visit_count")
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
