package cloudstorage;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class DropboxCloudStorage implements ICloudStorageActions {
    private final String _clientidentifier = "dropbox/java-tutorial";
    private final String _properties = "cloudstorage.properties";
    private final String _property_access_token = "dropbox.accesstoken";
    private final String _message_dont_load_config = "Unable to find properties to Dropbox";

    private static final Logger logger = LoggerFactory.getLogger(DropboxCloudStorage.class);

    @Autowired
    private Environment env;

    private String accesstoken;
    private DbxClientV2 client;
    private DbxRequestConfig config;

    public DropboxCloudStorage() throws DbxException {
        LoadAccessKey();
        config = DbxRequestConfig.newBuilder(_clientidentifier).build();
        client = new DbxClientV2(config, accesstoken);
    }

    private void LoadAccessKey()
    {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(_properties))
        {
            Properties prop = new Properties();
            if (input == null)
            {
                System.out.println(_message_dont_load_config);
                return;
            }
            prop.load(input);
            accesstoken = prop.getProperty(_property_access_token);
        }
        catch (IOException ex)
        {
            logger.error(ex.getMessage());
        }
    }

    public void UploadFile(InputStream file, String fileName) {
        try {
            client.files().uploadBuilder("/" + fileName).uploadAndFinish(file);
        } catch (FileNotFoundException ex) {
            logger.error(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } catch (UploadErrorException ex) {
            logger.error(ex.getMessage());
        } catch (DbxException ex) {
            logger.error(ex.getMessage());
        }
    }

    public InputStream DownloadFile(String fileName)
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
