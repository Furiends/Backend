package furiends.backend.dto;

import java.net.URL;

public class AdoptionAgreement {
    private String key;
    private String fileName;
    private URL url;
    private String uploadDate;

    public AdoptionAgreement(){
    }

    public AdoptionAgreement(String key, String fileName, String uploadDate) {
        this.key = key;
        this.fileName = fileName;
        this.uploadDate = uploadDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}
