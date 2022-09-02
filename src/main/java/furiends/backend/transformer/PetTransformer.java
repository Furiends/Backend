package furiends.backend.transformer;

import furiends.backend.dto.PetRequest;
import furiends.backend.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetTransformer {

    public void fromPetRequestToPet(PetRequest petRequest, Pet newPet){
        newPet.setOrganizationId(petRequest.getOrganizationId());
        newPet.setName(petRequest.getName());
        newPet.setSex(petRequest.getSex());
        newPet.setAge(petRequest.getAge());
        newPet.setSpecies(petRequest.getSpecies());
        newPet.setBreed(petRequest.getBreed());
        newPet.setWeight(petRequest.getWeight());
        newPet.setIsVaccinated(petRequest.getIsVaccinated());
        newPet.setIsSpayedOrNeutered(petRequest.getIsSpayedOrNeutered());
        newPet.setIsDewormed(petRequest.getIsDewormed());
        newPet.setDescription(petRequest.getDescription());
        newPet.setPostalCode(petRequest.getPostalCode());
        newPet.setProvince(petRequest.getProvince());
        newPet.setCity(petRequest.getCity());
        newPet.setCurrentAddress(petRequest.getCurrentAddress());
        newPet.setRescuedTime(petRequest.getRescuedTime());
        newPet.setIsPublished(petRequest.getIsPublished());
        newPet.setIsAdopted(petRequest.getIsAdopted());
        newPet.setAdoptedTime(petRequest.getAdoptedTime());
        newPet.setAdoptionApplicationId(petRequest.getAdoptionApplicationId());
        newPet.setReturnCount(petRequest.getReturnCount());
        newPet.setApplicationCount(petRequest.getApplicationCount());
        newPet.setLastPostPlacementVisitFormId(petRequest.getLastPostPlacementVisitFormId());
        newPet.setLastPostPlacementVisitDate(petRequest.getLastPostPlacementVisitDate());
        newPet.setPostPlacementVisitCount(petRequest.getPostPlacementVisitCount());



    }

}
