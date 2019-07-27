package bo;

import cloudstorage.DropboxCloudStorage;
import cloudstorage.ICloudStorageActions;
import com.dropbox.core.v2.DbxClientV2;
import com.uniritter.upphotos.DropboxConfig;
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
    private static final String photoUrlShowHtml = "data:image/jpg;base64,";
    private static final String messageFailUpload = "Unable to upload a file to CloudStorage ";
    private static final String nameCloudDropbox = "Dropbox";

    private static final Logger logger = LoggerFactory.getLogger(PhotoBO.class);

    @Autowired
    private PhotoRepository photoRepository;

    private DbxClientV2 getDropboxClient()
    {
        DropboxConfig configDropbox = new DropboxConfig();
        configDropbox.setConfigurationForDropbox();

        return configDropbox.getDropboxClient();
    }

    public List<Photo> fixPhotos(List<Photo> photos)
    {
        for (Photo item : photos) {
            //item.setPhotoString(photoUrlShowHtml + Base64.getEncoder().encodeToString(item.getPhoto()));
            item.setPhotoString(photoUrlShowHtml + Base64.getEncoder().encodeToString(downloadFileToCloudStorage(item.getLink())));
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

        //photo.setPhoto(fileToSave);
        String link = uploadFileToCloudStorage(fileToSave, file.getOriginalFilename());
        photo.setLink(link);

        photoRepository.save(photo);
    }

    public String uploadFileToCloudStorage(byte[] fileBytes, String fileName)
    {
        String nameFileToUpload = FileHelper.generateNameFile() + "_" + fileName;

        try
        {
            ICloudStorageActions dropboxCloudStorage = new DropboxCloudStorage();
            boolean response = dropboxCloudStorage.uploadFile(getDropboxClient(), FileHelper.convertByteArrayFileToFileInputStream(fileBytes), nameFileToUpload);
            if (!response)
            {
                throw new Exception(messageFailUpload + nameCloudDropbox);
            }
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }

        return nameFileToUpload;
    }

    public byte[] downloadFileToCloudStorage(String fileName)
    {
        try
        {
            ICloudStorageActions dropboxCloudStorage = new DropboxCloudStorage();
            InputStream input = dropboxCloudStorage.downloadFile(getDropboxClient(), fileName);
            return FileHelper.convertInputStreamtoByteArray(input);

        }
        catch (IOException ex)
        {
            logger.error(ex.getMessage());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }

        return null;
    }
}
