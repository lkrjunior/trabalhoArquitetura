package cloudstorage;

import java.io.InputStream;

public interface ICloudStorageActions {
    void UploadFile(InputStream file, String fileName);
    InputStream DownloadFile(String fileName);
}
