package furiends.backend.transformer;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import furiends.backend.dto.PetPhotoResponse;
import furiends.backend.dto.PetRequest;
import furiends.backend.model.Pet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PetTransformer {

    public void fromPetRequestToPet(PetRequest petRequest, Pet newPet){
        newPet.setOrganizationId(petRequest.getOrganizationId());
        newPet.setName(petRequest.getName());
        newPet.setSex(petRequest.getSex());
        newPet.setAge(petRequest.getAge());
        newPet.setSpecies(petRequest.getSpecies());
        newPet.setBreed(petRequest.getBreed());
        newPet.setSize(petRequest.getSize());
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

    public void fromRequestToPetPhoto(List<MultipartFile> petPhotoList, Pet pet,
                                      COSClient cosClient, String bucketName) throws IOException {
        List<String> newKeyList = new ArrayList<>();
        // upload pet photos to cos and save the key of photos
        for (int i = 0; i < petPhotoList.size(); i++) {
            String key = pet.getId() + "-" + i + ".jpg";
            InputStream petPhotoStream = petPhotoList.get(i).getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(petPhotoStream.available());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, petPhotoStream, objectMetadata);
            cosClient.putObject(putObjectRequest);
            newKeyList.add(key);
        }
        pet.setPetPhotoKeyList(newKeyList);
    }

    public PetPhotoResponse fromPetPhotoToResponse(String petId, List<String> keyList,
                                                   COSClient cosClient, String bucketName) {
        List<String> newUrlList = new ArrayList<>();
        // get urls for each photo of the pet from cos
        for (int i = 0; i < keyList.size(); i++) {
            Date expirationDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
            URL url = cosClient.generatePresignedUrl(bucketName, keyList.get(i), expirationDate, HttpMethodName.GET);
            newUrlList.add(String.valueOf(url));
        }
        PetPhotoResponse newPetPhotoResponse = new PetPhotoResponse();
        newPetPhotoResponse.setPetId(petId);
        newPetPhotoResponse.setPetPhotoUrlList(newUrlList);
        return newPetPhotoResponse;
    }
}
