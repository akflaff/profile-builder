package cloudcode.profilebuilder.iservice;

import cloudcode.profilebuilder.interfaces.FilestoreService;
import cloudcode.profilebuilder.model.Document;
import com.google.cloud.storage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.gax.paging.Page;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class FileService implements FilestoreService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private SecretKeeperService secretKeeperService;
    private static Storage storage;
    private static String PROJECT_ID;
    private static String BUCKET;

    public FileService(SecretKeeperService secretKeeperService){
        storage = StorageOptions.getDefaultInstance().getService();
        PROJECT_ID = secretKeeperService.getProjectSecret();
        BUCKET = secretKeeperService.getBucketSecret();
    }

    public List<Document> listObjects(String filePath) {
        Page<Blob> blobs = storage.list(BUCKET, Storage.BlobListOption.prefix(filePath));
        List<Document> documentList = new ArrayList<>();
        for (Blob blob : blobs.iterateAll()) {
            if(blob.getSize() > 0){
                Document doc = new Document();
                doc.blob = blob;
                byte[] tempBlob = downloadObjectIntoMemory(blob.getName());
                byte[] encoded = Base64.getEncoder().encode(tempBlob);
                doc.base64String = new String(encoded);
                documentList.add(doc);
            }
        }
        return documentList;
    }

    public void uploadObject(String objectName, String filePath) throws IOException {
        BlobId blobId = BlobId.of(BUCKET, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();


        Storage.BlobTargetOption precondition;
        if (storage.get(BUCKET, objectName) == null) {
            // Setting the DoesNotExist precondition will cause the request to fail if the
            // object is created before the request runs
            precondition = Storage.BlobTargetOption.doesNotExist();
        } else {
            // Destination already exists in bucket so set a generation-match precondition.
            // This will cause the request to fail if the existing object's generation
            // changes before the request runs.
            precondition = Storage.BlobTargetOption.generationMatch();

        }
        storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)), precondition);
    }

    public void downloadObject(String objectName, String destFilePath) {
        Blob blob = storage.get(BlobId.of(BUCKET, objectName));
        blob.downloadTo(Paths.get(destFilePath));
    }

    public byte[] downloadObjectIntoMemory(String objectName) {
        return storage.readAllBytes(BUCKET, objectName);
    }
}
