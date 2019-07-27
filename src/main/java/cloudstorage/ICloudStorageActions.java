package cloudstorage;

import java.io.InputStream;

public interface ICloudStorageActions<T extends InputStream, U> {
    boolean uploadFile(U client, T file, String fileName);
    T downloadFile(U client, String fileName);
}
