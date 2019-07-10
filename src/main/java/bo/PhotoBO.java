package bo;

import cloudstorage.DropboxCloudStorage;
import cloudstorage.ICloudStorageActions;
import com.dropbox.core.DbxException;
import helpers.FileHelper;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import repository.PhotoRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@Component
public class PhotoBO {
    private final String _photo_url_show_html = "data:image/jpg;base64,";

    @Autowired
    private PhotoRepository photoRepository;

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
        try {
            ICloudStorageActions dropboxCloudStorage = new DropboxCloudStorage();
            dropboxCloudStorage.UploadFile(FileHelper.ConvertByteArrayFileToFileInputStream(fileBytes), nameFileToUpload);
        } catch (DbxException e) {
            e.printStackTrace();
        }
        return nameFileToUpload;
    }

    public byte[] DownloadFileToCloudStorage(String fileName)
    {
        try {
            ICloudStorageActions dropboxCloudStorage = new DropboxCloudStorage();
            InputStream input = dropboxCloudStorage.DownloadFile(fileName);
            return FileHelper.ConvertInputStreamtoByteArray(input);

        } catch (DbxException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
