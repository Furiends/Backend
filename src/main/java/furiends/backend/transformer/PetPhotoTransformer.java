package furiends.backend.transformer;

import furiends.backend.dto.PetPhotoRequest;
import furiends.backend.model.PetPhoto;
import org.springframework.stereotype.Component;

@Component
public class PetPhotoTransformer {

    public void fromPetPhotoRequestToPetPhoto(PetPhotoRequest petPhotoRequest, PetPhoto newPetPhoto) {
        newPetPhoto.setId(petPhotoRequest.getId());
        newPetPhoto.setPetId(petPhotoRequest.getPetId());
        // TODO: Add COS api
        newPetPhoto.setPhotoUrl(petPhotoRequest.getPhotoUrl());
        newPetPhoto.setCover(petPhotoRequest.getCover());
    }
}
