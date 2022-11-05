package furiends.backend.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import furiends.backend.dto.PetPhotoResponse;
import furiends.backend.model.PetPhoto;
import furiends.backend.repository.PetPhotoRepository;
import furiends.backend.transformer.PetPhotoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetPhotoService {

    @Resource
    private PetPhotoRepository petPhotoRepository;

    @Autowired
    private PetPhotoTransformer petPhotoTransformer;

    private COSClient cosClient = null;

    private String bucketName = "furiends-photos-1312443161";

    public void createCosClient () {
        String secretId = "";
        String secretKey = "";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region("ap-guangzhou");
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        cosClient = new COSClient(cred, clientConfig);
    }

    public List<PetPhoto> findAllPetPhotos() {
        return petPhotoRepository.findAll();
    }

    public PetPhotoResponse findPetPhotosByPetId(String petId) {
        List<String> keyList = petPhotoRepository.findById(petId).get().getPetPhotoKeyList();
        PetPhotoResponse newPetPhotoResponse = petPhotoTransformer.fromPetPhotoToResponse(petId, keyList, cosClient, bucketName);
        return newPetPhotoResponse;
    }

    public List<PetPhotoResponse> findAllCoverForPetList(List<String> petIdList) {
        List<PetPhotoResponse> petPhotoResponsesList =  new ArrayList<PetPhotoResponse>();
        for (String petId : petIdList) {
            List<String> coverList = petPhotoRepository.findById(petId).get().getPetPhotoKeyList().subList(0,1);
            PetPhotoResponse newPetPhotoResponse = petPhotoTransformer.fromPetPhotoToResponse(petId, coverList, cosClient, bucketName);
            petPhotoResponsesList.add(newPetPhotoResponse);
        }
        return petPhotoResponsesList;
    }

    public void createPetPhotos(String petId, List<MultipartFile> petPhotoList) throws IOException {
        PetPhoto newPetPhoto = new PetPhoto();
        newPetPhoto.setPetId(petId);
        petPhotoTransformer.fromRequestToPetPhoto(petPhotoList, newPetPhoto, cosClient, bucketName);
        petPhotoRepository.save(newPetPhoto);
    }

    public void updatePetPhotos(String petId, List<MultipartFile> petPhotoList) throws IOException{
        PetPhoto petPhoto = petPhotoRepository.findById(petId).get();
        petPhotoTransformer.fromRequestToPetPhoto(petPhotoList, petPhoto, cosClient, bucketName);
        petPhotoRepository.save(petPhoto);
    }
}

