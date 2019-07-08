package bo;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import repository.PhotoRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class PhotoBO {
    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> FixPhotos(List<Photo> photos)
    {
        for (Photo item : photos) {
            item.setPhotoString("data:image/jpg;base64," + Base64.getEncoder().encodeToString(item.getPhoto()));
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
        photo.setPhoto(file.getBytes());
        photoRepository.save(photo);
    }
}
