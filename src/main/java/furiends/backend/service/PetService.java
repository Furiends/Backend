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
    public List<Pet> findAllByPublishStatus(Boolean isPublished){
        return petRepository.findAllByPublishStatus(isPublished);
    }

    // (by organization) filter pets by post publish status
    public List<Pet> findAllByPublishStatusOrg(String organizationId, Boolean isPosted){
        return petRepository.findAllByPublishStatusOrg(organizationId, isPosted);
    }

    // filter pets by adoption status
    public List<Pet> findAllByAdoptionStatus(Boolean isAdopted){
        return petRepository.findAllByAdoptionStatus(isAdopted);
    }

    // (by organization) filter pets by adoption status
    public List<Pet> findAllByAdoptionStatusOrg(String organizationId, Boolean isAdopted){
        return petRepository.findAllByAdoptionStatusOrg(organizationId, isAdopted);
    }

    // find all dogs
    public List<Pet> findAllDogs(){
        return petRepository.findAllDogs();
    }

    // find all cats
    public List<Pet> findAllCats(){
        return petRepository.findAllCats();
    }

    // find a pet with pet ID
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
