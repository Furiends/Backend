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
//        Pet newPet = new Pet();
//        newPet.setId();
//        newPet.setPostCreatedTime();
//        newPet.setPostUpdateTime();
//        // does newPet have the timestamps?
//        System.out.println("1 " + newPet.getPostCreatedTime());
//        petTransformer.fromPetRequestToPet(petRequest, newPet);
        Pet pet = petTransformer.createPetFromRequest(petRequest);
        // does newPet have the timestamps?
        System.out.println("2 " + pet.getPostCreatedTime());
        return petRepository.save(pet);
    }

    public Optional<Pet> updatePet(PetRequest petRequest, String id){
        Optional<Pet> optionalPet = findPetById(id);
        if (optionalPet.isEmpty()) {
            return Optional.empty();
        } else {
            Pet pet = optionalPet.get();
            pet.setPostUpdateTime();
            petTransformer.fromPetRequestToPet(petRequest, pet);
            Pet pet2 = petTransformer.createPetFromRequest(petRequest);
            pet2.setId(id);
            petRepository.save(pet2);
            return Optional.of(petRepository.save(pet));
        }


    }

    public void deletePet(String id) {
        if (petRepository.existsById(id)){
            petRepository.deleteById(id);
        }
    }





}
