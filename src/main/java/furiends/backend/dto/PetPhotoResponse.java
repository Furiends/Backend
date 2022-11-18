package furiends.backend.dto;

import java.util.List;

public class PetPhotoResponse {

    private String petId;

    private List<String> petPhotoUrlList;

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public List<String> getPetPhotoUrlList() {
        return petPhotoUrlList;
    }

    public void setPetPhotoUrlList(List<String> petPhotoUrlList) {
        this.petPhotoUrlList = petPhotoUrlList;
    }

}
