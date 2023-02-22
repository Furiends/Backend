package furiends.backend.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.DeleteObjectsRequest;
import com.qcloud.cos.region.Region;
import furiends.backend.dto.PetPhotoResponse;
import furiends.backend.dto.PetRequest;
import furiends.backend.model.Pet;
import furiends.backend.repository.PetRepository;
import furiends.backend.transformer.PetTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Resource
    private PetRepository petRepository;

    @Autowired
    private PetTransformer petTransformer;

    private COSClient cosClient = null;

    private String bucketName = "furiends-photos-1312443161";


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

    public void createCosClient () {
        String secretId = "";
        String secretKey = "";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region("ap-guangzhou");
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        cosClient = new COSClient(cred, clientConfig);
    }

    public void shutDownCosClient () {
        cosClient.shutdown();
    }

    public PetPhotoResponse findPetPhotosByPetId(String petId) {
        List<String> keyList = petRepository.findById(petId).get().getPetPhotoKeyList();
        PetPhotoResponse newPetPhotoResponse = petTransformer.fromPetPhotoToResponse(petId, keyList, cosClient, bucketName);
        return newPetPhotoResponse;
    }

    public List<PetPhotoResponse> findAllCoverForPetList(List<String> petIdList) {
        List<PetPhotoResponse> petPhotoResponsesList =  new ArrayList<>();
        for (String petId : petIdList) {
            List<String> coverList = petRepository.findById(petId).get().getPetPhotoKeyList().subList(0,1);
            PetPhotoResponse newPetPhotoResponse = petTransformer.fromPetPhotoToResponse(petId, coverList, cosClient, bucketName);
            petPhotoResponsesList.add(newPetPhotoResponse);
        }
        return petPhotoResponsesList;
    }

    public void createPetPhotos(String petId, List<MultipartFile> petPhotoList) throws IOException {
        Pet pet = petRepository.findById(petId).get();
        petTransformer.fromRequestToPetPhoto(petPhotoList, pet, cosClient, bucketName);
        petRepository.save(pet);
    }

    public void updatePetPhotos(String petId, List<MultipartFile> petPhotoList) throws IOException {
        deletePetPhotos(petId);
        Pet pet = petRepository.findById(petId).get();
        petTransformer.fromRequestToPetPhoto(petPhotoList, pet, cosClient, bucketName);
        petRepository.save(pet);
    }

    public void deletePetPhotos(String petId) {
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
        Pet pet = petRepository.findById(petId).get();
        List<String> keyStringList = pet.getPetPhotoKeyList();
        List<DeleteObjectsRequest.KeyVersion> keyList = new ArrayList<>();
        for (String key : keyStringList) {
            keyList.add(new DeleteObjectsRequest.KeyVersion(key));
        }
        deleteObjectsRequest.setKeys(keyList);
        cosClient.deleteObjects(deleteObjectsRequest);
        keyStringList.clear();
        pet.setPetPhotoKeyList(keyStringList);
        petRepository.save(pet);
    }
}
