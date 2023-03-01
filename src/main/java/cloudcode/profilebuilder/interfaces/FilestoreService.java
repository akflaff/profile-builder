package cloudcode.profilebuilder.interfaces;

import cloudcode.profilebuilder.model.Document;

import java.io.IOException;
import java.util.List;

public interface FilestoreService {

    List<Document> listObjects(String filePath);

    void uploadObject(String objectName, String filePath) throws IOException;

    void downloadObject(String objectName, String destFilePath);

    public byte[] downloadObjectIntoMemory(String objectName);

}