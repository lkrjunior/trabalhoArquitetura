package cloudstorage;

import java.io.InputStream;

public interface ICloudStorageActions<T extends InputStream, U> {
    boolean UploadFile(U client, T file, String fileName);
    T DownloadFile(U client, String fileName);
}
