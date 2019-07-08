package bo;

import model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import repository.PhotoRepository;

import java.io.IOException;

@Component
public class PhotoBO {
    @Autowired
    private PhotoRepository photoRepository;

    public void Save(Photo photo, MultipartFile file) throws IOException {
        photo.setPhoto(file.getBytes());
        photoRepository.save(photo);
    }
}
