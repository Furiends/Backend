package furiends.backend.service;

import furiends.backend.dto.PetPhotoResponse;
import furiends.backend.dto.PetRequest;
import furiends.backend.model.Pet;
import furiends.backend.repository.PetRepository;
import furiends.backend.transformer.PetTransformer;
import furiends.backend.utils.CloudAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class PetService {

    @Resource
    private PetRepository petRepository;

    @Autowired
    private PetTransformer petTransformer;


    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public List<String> findAllPetIds() {
        return petRepository.findAllIds();
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

    public PetPhotoResponse findPetPhotosByPetId(String petId, CloudAPI cloudAPI) {
        List<String> keyList = petRepository.findById(petId).get().getPetPhotoKeyList();
        List<String> newUrlList = new ArrayList<>();
        // get urls for each photo of the pet from cos
        for (int i = 0; i < keyList.size(); i++) {
            URL url = cloudAPI.readFromCloud(keyList.get(i));
            newUrlList.add(String.valueOf(url));
        }
        PetPhotoResponse newPetPhotoResponse = new PetPhotoResponse();
        newPetPhotoResponse.setPetId(petId);
        newPetPhotoResponse.setPetPhotoUrlList(newUrlList);
        return newPetPhotoResponse;
}

    public List<PetPhotoResponse> findAllCoverForPetList(List<String> petIdList, CloudAPI cloudAPI) {
        List<PetPhotoResponse> petPhotoResponsesList =  new ArrayList<>();
        // get url for each cover of the pet from cos
        for (String petId : petIdList) {
            List<String> coverList = petRepository.findById(petId).get().getPetPhotoKeyList().subList(0,1);
            List<String> newUrlList = new ArrayList<>();
            URL url = cloudAPI.readFromCloud(coverList.get(0));
            newUrlList.add(String.valueOf(url));
            PetPhotoResponse newPetPhotoResponse = new PetPhotoResponse();
            newPetPhotoResponse.setPetId(petId);
            newPetPhotoResponse.setPetPhotoUrlList(newUrlList);
            petPhotoResponsesList.add(newPetPhotoResponse);
        }
        return petPhotoResponsesList;
    }

    public void createPetPhotos(String petId, List<MultipartFile> petPhotoList, CloudAPI cloudAPI) throws IOException {
        String category = "PetPhoto";
        Pet pet = petRepository.findById(petId).get();
        List<String> newKeyList = new ArrayList<>();
        // upload pet photos to cos and save the key of photos
        for (int i = 0; i < petPhotoList.size(); i++) {
            String key = pet.getId() + "_" + category + "_"+ i + ".jpg";
            cloudAPI.uploadPhotoToCloud(petPhotoList.get(i), key);
            newKeyList.add(key);
        }
        pet.setPetPhotoKeyList(newKeyList);
        petRepository.save(pet);
    }

    public void updatePetPhotos(String petId, List<MultipartFile> petPhotoList, CloudAPI cloudAPI) throws IOException {
        String category = "PetPhoto";
        Pet pet = petRepository.findById(petId).get();
        //delete all photos of pet
        cloudAPI.deleteFile(pet.getPetPhotoKeyList());
        List<String> newKeyList = new ArrayList<>();
        // upload pet photos to cos and save the key of photos
        for (int i = 0; i < petPhotoList.size(); i++) {
            String key = pet.getId() + "_" + category + "_"+ i + ".jpg";
            cloudAPI.uploadPhotoToCloud(petPhotoList.get(i), key);
            newKeyList.add(key);
        }
        pet.setPetPhotoKeyList(newKeyList);
        petRepository.save(pet);
    }

    public void deletePetPhotos(String petId, CloudAPI cloudAPI) {
        Pet pet = petRepository.findById(petId).get();
        List<String> keyStringList = pet.getPetPhotoKeyList();
        //delete photos of pet
        cloudAPI.deleteFile(keyStringList);
        //delete petPhotoKeyList in database
        keyStringList.clear();
        pet.setPetPhotoKeyList(keyStringList);
        petRepository.save(pet);
    }
}
