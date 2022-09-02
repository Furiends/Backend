package furiends.backend.dto;


import java.util.Date;

public class PetRequest {
    private String organizationId;
    private String name;
    private String sex;
    private Integer age;
    private String species;
    private String breed;
    private Integer weight;
    private String isVaccinated;
    private String isSpayedOrNeutered;
    private String isDewormed;
    private String description;
    private Integer postalCode;
    private String province;
    private String city;
    private String currentAddress;
    private Date rescuedTime;
    private Boolean isPublished;
    private Boolean isAdopted;
    private Date adoptedTime;
    private String adoptionApplicationId;
    private Integer returnCount;
    private Integer applicationCount;
    private String lastPostPlacementVisitFormId;
    private Date lastPostPlacementVisitDate;
    private Integer postPlacementVisitCount;



    public String getOrganizationId() {
        return organizationId;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getIsVaccinated() {
        return isVaccinated;
    }

    public String getIsSpayedOrNeutered() {
        return isSpayedOrNeutered;
    }

    public String getIsDewormed() {
        return isDewormed;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public Date getRescuedTime() {
        return rescuedTime;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public Boolean getIsAdopted() {
        return isAdopted;
    }

    public Date getAdoptedTime() {
        return adoptedTime;
    }

    public String getAdoptionApplicationId() {
        return adoptionApplicationId;
    }

    public Integer getReturnCount() {
        return returnCount;
    }

    public Integer getApplicationCount() {
        return applicationCount;
    }

    public String getLastPostPlacementVisitFormId() {
        return lastPostPlacementVisitFormId;
    }

    public Date getLastPostPlacementVisitDate() {
        return lastPostPlacementVisitDate;
    }

    public Integer getPostPlacementVisitCount() {
        return postPlacementVisitCount;
    }


}
