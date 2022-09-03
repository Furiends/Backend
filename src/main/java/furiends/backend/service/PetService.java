package furiends.backend.service;

import furiends.backend.dto.PetRequest;
import furiends.backend.model.Pet;
import furiends.backend.repository.PetRepository;
import furiends.backend.transformer.PetTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Resource
    private PetRepository petRepository;

    @Autowired
    private PetTransformer petTransformer;

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    // list all pets within the organization
    public List<Pet> findAllPetsWithinOrganization(String organizationId) {
        return petRepository.findAllByOrganization(organizationId);
    }

    // filter pets by post publish status
    public List<Pet> findAllByPublishStatus(Boolean isPosted){
        return petRepository.findAllByPublishStatus(isPosted);
    }

    // (by organization) filter pets by post publish status
    public List<Pet> findAllByPublishStatusOrg(Boolean isPosted, String organizationId){
        return petRepository.findAllByPublishStatusOrg(isPosted, organizationId);
    }

    // filter pets by adoption status
    public List<Pet> findAllByAdoptionStatus(Boolean isAdopted){
        return petRepository.findAllByAdoptionStatus(isAdopted);
    }

    // (by organization) filter pets by adoption status
    public List<Pet> findAllByAdoptionStatusOrg(Boolean isAdopted,  String organizationId){
        return petRepository.findAllByAdoptionStatusOrg(isAdopted, organizationId);
    }

    public Optional<Pet> findPetById(String id){
        return petRepository.findById(id);
    }

    public Pet createPet(PetRequest petRequest){
        Pet newPet = new Pet();
        newPet.setId();
        newPet.setPostCreatedTime();
        newPet.setPostUpdateTime();
        petTransformer.fromPetRequestToPet(petRequest, newPet);
        return petRepository.save(newPet);
    }

    public Pet updatePet(PetRequest petRequest, String id){
        Pet pet = findPetById(id).get();
        pet.setPostUpdateTime();
        petTransformer.fromPetRequestToPet(petRequest, pet);
        return petRepository.save(pet);
    }

    public void deletePet(String id) {
        if (petRepository.existsById(id)){
            petRepository.deleteById(id);
        }
    }





}
