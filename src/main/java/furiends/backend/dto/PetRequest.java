package furiends.backend.dto;


import java.util.Date;

public class PetRequest {
    private String organizationId;
    private String name;
    private Integer sex;
    private Integer age;
    private Integer species;
    private String breed;
    private Integer size;
    private Integer weight;
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

    public Integer getSex() {
        return sex;
    }
    public Integer getAge() {
        return age;
    }

    public Integer getSpecies() {
        return species;
    }
    public String getBreed() {
        return breed;
    }

    public Integer getSize() { return  size;}
    public Integer getWeight() {
        return weight;
    }

    public Integer getIsVaccinated() {
        return isVaccinated;
    }

    public Integer getIsSpayedOrNeutered() {
        return isSpayedOrNeutered;
    }

    public Integer getIsDewormed() {
        return isDewormed;
    }

    public String getDescription() {
        return description;
    }

    public String getPostalCode() {
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
