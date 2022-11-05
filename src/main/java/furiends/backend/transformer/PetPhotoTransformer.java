package furiends.backend.transformer;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import furiends.backend.dto.PetPhotoResponse;
import furiends.backend.model.PetPhoto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PetPhotoTransformer {

    public void fromRequestToPetPhoto(List<MultipartFile> petPhotoList, PetPhoto petPhoto,
                                      COSClient cosClient, String bucketName) throws IOException {
        List<String> newKeyList = new ArrayList<>();
        // upload pet photos to cos and save the key of photos
        for (int i = 0; i < petPhotoList.size(); i++) {
            String key = petPhoto.getPetId() + "-" + i + ".jpg";
            InputStream petPhotoStream = petPhotoList.get(i).getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(petPhotoStream.available());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, petPhotoStream, objectMetadata);
            cosClient.putObject(putObjectRequest);
            newKeyList .add(key);
        }
        petPhoto.setPetPhotoKeyList(newKeyList);
    }

    public PetPhotoResponse fromPetPhotoToResponse(String petId, List<String> keyList,
                                                   COSClient cosClient, String bucketName) {
        List<String> newUrlList = new ArrayList<>();
        // get urls for each photo of the pet from cos
        for (int i = 0; i < keyList.size(); i++) {
            Date expirationDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
            URL url = cosClient.generatePresignedUrl(bucketName, keyList.get(i), expirationDate, HttpMethodName.GET);
            newUrlList .add(String.valueOf(url));
        }
        PetPhotoResponse newPetPhotoResponse = new PetPhotoResponse();
        newPetPhotoResponse.setPetId(petId);
        newPetPhotoResponse.setPetPhotoUrlList(newUrlList);
        return newPetPhotoResponse;
    }
}
