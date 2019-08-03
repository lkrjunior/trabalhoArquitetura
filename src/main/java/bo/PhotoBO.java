package bo;

import cloudstorage.DropboxCloudStorage;
import cloudstorage.ICloudStorageActions;
import com.dropbox.core.v2.DbxClientV2;
import com.uniritter.upphotos.DropboxConfig;
import exception.DropboxException;
import helpers.FileHelper;
import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import repository.PhotoRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@Component
public class PhotoBO
{
    private static final String PHOTO_URL_SHOW_HTML = "data:image/jpg;base64,";
    private static final String MESSAGE_FAIL_UPLOAD = "Unable to upload a file to CloudStorage ";
    private static final String NAME_CLOUD_DROPBOX = "Dropbox";

    private static final Logger logger = LoggerFactory.getLogger(PhotoBO.class);

    @Autowired
    private PhotoRepository photoRepository;

    private ICloudStorageActions dropboxCloudStorage;

    private DbxClientV2 dbxClientV2;

    private void setConfiguration()
    {
        if (dropboxCloudStorage == null)
        {
            dbxClientV2 = new DropboxConfig().getDropboxClient();
            dropboxCloudStorage = new DropboxCloudStorage();
        }
    }

    private String uploadFileToCloudStorage(byte[] fileBytes, String fileName)
    {
        String nameFileToUpload = String.format("%s_%s",FileHelper.generateNameFile(), fileName);

        try
        {
            setConfiguration();
            boolean response = dropboxCloudStorage.uploadFile(dbxClientV2, FileHelper.convertByteArrayFileToFileInputStream(fileBytes), nameFileToUpload);
            if (!response)
            {
                throw new DropboxException(MESSAGE_FAIL_UPLOAD + NAME_CLOUD_DROPBOX);
            }
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }

        return nameFileToUpload;
    }

    private byte[] downloadFileToCloudStorage(String fileName)
    {
        try
        {
            setConfiguration();
            InputStream input = dropboxCloudStorage.downloadFile(dbxClientV2, fileName);
            return FileHelper.convertInputStreamtoByteArray(input);

        }
        catch (IOException ex)
        {
            logger.error(ex.getMessage());
        }

        return new byte[0];
    }

    public void setConfiguration(ICloudStorageActions cloudStorageConfiguration, DbxClientV2 client)
    {
        dropboxCloudStorage = cloudStorageConfiguration;
        dbxClientV2 = client;
    }

    public List<Photo> fixPhotos(List<Photo> photos)
    {
        for (Photo item : photos) {
            item.setPhotoString(PHOTO_URL_SHOW_HTML + Base64.getEncoder().encodeToString(downloadFileToCloudStorage(item.getLink())));
        }
        return photos;
    }

    public Photo createPhotoWithPerson(Person person)
    {
        Photo photo = new Photo();
        photo.setPerson(person);

        return photo;
    }

    public void save(Photo photo, MultipartFile file) throws IOException {
        byte[] fileToSave = file.getBytes();

        String link = uploadFileToCloudStorage(fileToSave, file.getOriginalFilename());
        photo.setLink(link);

        photoRepository.save(photo);
    }
}
