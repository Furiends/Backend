package furiends.backend.dto;

public class PetPhotoRequest {
    private String id;
    private String petId;
    private String photoUrl;
    private Boolean isCover;

    public String getId() {
        return id;
    }

    public String getPetId() {
        return petId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Boolean getCover() {
        return isCover;
    }
}
