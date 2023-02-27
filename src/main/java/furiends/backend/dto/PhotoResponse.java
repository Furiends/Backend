package furiends.backend.dto;

import java.net.URL;
import java.util.List;

public class PhotoResponse {
    String id;
    List<URL> urlList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<URL> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<URL> urlList) {
        this.urlList = urlList;
    }
}
