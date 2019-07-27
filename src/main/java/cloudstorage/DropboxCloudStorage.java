package cloudstorage;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DropboxCloudStorage implements ICloudStorageActions<InputStream, DbxClientV2> {

    private static final Logger logger = LoggerFactory.getLogger(DropboxCloudStorage.class);

    @Override
    public boolean UploadFile(DbxClientV2 client, InputStream file, String fileName)
    {
        try
        {
            client.files().uploadBuilder("/" + fileName).uploadAndFinish(file);
        }
        catch (IOException|DbxException ex)
        {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public InputStream DownloadFile(DbxClientV2 client, String fileName)
    {
        DbxDownloader<FileMetadata> file = null;
        try {
            file = client.files().download("/" + fileName);
        } catch (DbxException ex) {
            logger.error(ex.getMessage());
        }

        return file != null ? file.getInputStream() : null;
    }
}
