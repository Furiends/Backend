package furiends.backend.utils;

import com.fasterxml.uuid.Generators;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.exception.MultiObjectDeleteException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.DeleteObjectsRequest;
import com.qcloud.cos.model.DeleteObjectsResult;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.JavaBean;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

// TODO: which tag to use?
@Service
public class CloudAPI {
    COSClient cosClient = null;
    String bucket = null;
    String regionString = "ap-guangzhou";

    // build connection to the Tencent Cloud
    public void createCosClient(String bucketName, String secretId, String secretKey) {
        bucket = bucketName;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(regionString);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        cosClient =  new COSClient(cred, clientConfig);
    }


    // after actions of uploading/downloading are completed, connection to the cloud should be shut down
    public void shutDownCosClient() {
        cosClient.shutdown();
    }



    // upload a file to the cloud
    public Map<String, String> uploadToCloud(MultipartFile file, String entityId, String category) throws IOException {
        try {
            Map<String, String> result = new HashMap<>();
            // generate an object key to locate the file in the cloud
            String key = entityId + "_" + category + "_"
                    + Generators.timeBasedGenerator().generate().toString();
            result.put("key", key);
            // record file name
            String fileName = FilenameUtils.getBaseName(file.getOriginalFilename());
            result.put("fileName", fileName);
            // record upload date
            LocalDate dateObj = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String uploadDate = dateObj.format(formatter);
            result.put("uploadDate", uploadDate);
            // upload to cloud
            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, inputStream, objectMetadata);
            cosClient.putObject(putObjectRequest);
            return result;
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        // TODO: return null?
        return null;
    }

    // read a file from the cloud
    public URL readFromCloud(String objectKey){
        try {
            Date expirationDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
            return cosClient.generatePresignedUrl(bucket, objectKey, expirationDate, HttpMethodName.GET);
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        // TODO: return null?
        return null;
    }

    // delete a file by objectKey
    public void deleteFile(List<String> objectKeyList){
        try {
            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucket);
            List<DeleteObjectsRequest.KeyVersion> keyList = new ArrayList<>();
            for (String key : objectKeyList) {
                keyList.add(new DeleteObjectsRequest.KeyVersion(key));
            }
            deleteObjectsRequest.setKeys(keyList);
            cosClient.deleteObjects(deleteObjectsRequest);
        } catch (MultiObjectDeleteException mde) {
            // 如果部分删除成功部分失败, 返回 MultiObjectDeleteException
            List<DeleteObjectsResult.DeletedObject> deleteObjects = mde.getDeletedObjects();
            List<MultiObjectDeleteException.DeleteError> deleteErrors = mde.getErrors();
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }

    }



}
