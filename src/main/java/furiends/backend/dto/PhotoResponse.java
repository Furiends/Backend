package furiends.backend.dto;

import java.util.List;

public class PhotoResponse {

    private String id;
    private List<String> photoUrlList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<String> getPhotoUrlList() {
        return photoUrlList;
    }

    public void setPhotoUrlList(List<String> photoUrlList) {
        this.photoUrlList = photoUrlList;
    }
}
