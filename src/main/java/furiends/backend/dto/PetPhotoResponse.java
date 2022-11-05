package furiends.backend.dto;

import java.util.List;

public class PetPhotoResponse {

    private String petId;

    private List<String> petPhotoUrlList;

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public void setPetPhotoUrlList(List<String> petPhotoUrlList) {
        this.petPhotoUrlList = petPhotoUrlList;
    }

}
