package furiends.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity(name="pet_photo")
public class PetPhoto {
    @Id
    private String petId;
    @ElementCollection
    private List<String> petPhotoKeyList;

    public PetPhoto () {
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public List<String> getPetPhotoKeyList() {
        return petPhotoKeyList;
    }

    public void setPetPhotoKeyList(List<String> petPhotoKeyList) {
        this.petPhotoKeyList = petPhotoKeyList;
    }
}
