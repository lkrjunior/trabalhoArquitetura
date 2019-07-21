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
    private final String _photo_url_show_html = "data:image/jpg;base64,";
    private final String _message_fail_upload = "Unable to upload a file to CloudStorage ";
    private final String _name_cloud_dropbox = "Dropbox";

    private static final Logger logger = LoggerFactory.getLogger(PhotoBO.class);

    @Autowired
    private PhotoRepository photoRepository;

    private DbxClientV2 GetDropboxClient()
    {
        DropboxConfig configDropbox = new DropboxConfig();
        configDropbox.SetConfigurationForDropbox();

        return configDropbox.GetDropboxClient();
    }

    public List<Photo> FixPhotos(List<Photo> photos)
    {
        for (Photo item : photos) {
            //item.setPhotoString(_photo_url_show_html + Base64.getEncoder().encodeToString(item.getPhoto()));
            item.setPhotoString(_photo_url_show_html + Base64.getEncoder().encodeToString(DownloadFileToCloudStorage(item.getLink())));
        }
        return photos;
    }

    public Photo CreatePhotoWithPerson(Person person)
    {
        Photo photo = new Photo();
        photo.setPerson(person);

        return photo;
    }

    public void Save(Photo photo, MultipartFile file) throws IOException {
        byte[] fileToSave = file.getBytes();

        //photo.setPhoto(fileToSave);
        String link = UploadFileToCloudStorage(fileToSave, file.getOriginalFilename());
        photo.setLink(link);

        photoRepository.save(photo);
    }

    public String UploadFileToCloudStorage(byte[] fileBytes, String fileName)
    {
        String nameFileToUpload = FileHelper.GenerateNameFile() + "_" + fileName;

        try
        {
            ICloudStorageActions dropboxCloudStorage = new DropboxCloudStorage();
            boolean response = dropboxCloudStorage.UploadFile(GetDropboxClient(), FileHelper.ConvertByteArrayFileToFileInputStream(fileBytes), nameFileToUpload);
            if (!response)
            {
                throw new Exception(_message_fail_upload + _name_cloud_dropbox);
            }
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }

        return nameFileToUpload;
    }

    public byte[] DownloadFileToCloudStorage(String fileName)
    {
        try
        {
            ICloudStorageActions dropboxCloudStorage = new DropboxCloudStorage();
            InputStream input = dropboxCloudStorage.DownloadFile(GetDropboxClient(), fileName);
            return FileHelper.ConvertInputStreamtoByteArray(input);

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
