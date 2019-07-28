package com.uniritter.upphotos;

import bo.PhotoBO;
import cloudstorage.DropboxCloudStorage;
import com.dropbox.core.v2.DbxClientV2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import repository.*;
import model.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhotoBOTests
{
    @MockBean(name="photoRepository")
    private PhotoRepository photoRepository;

    @Mock
    private DropboxCloudStorage dropboxCloudStorage;
    @Mock
    DbxClientV2 dbxClientV2;
    @Mock
    private Photo photo;
    @Mock
    private MultipartFile file;
    @Mock
    private InputStream inputStream;

    @Autowired
    private PhotoBO photoBO;

    private List<Photo> photos;

    @Before
    public void setup()
    {
        photos = new ArrayList<>();
        Photo photo = new Photo();
        photo.setLink("link");
        photos.add(photo);
    }

    @Test
    public void save()
    {
        photoBO.setConfiguration(dropboxCloudStorage, dbxClientV2);
        try
        {
            when(dropboxCloudStorage.uploadFile(any(DbxClientV2.class), any(InputStream.class), any(String.class))).thenReturn(true);
            when(file.getBytes()).thenReturn(new byte[0]);
            photoBO.save(photo, file);
            verify(dropboxCloudStorage, times(1)).uploadFile(any(DbxClientV2.class), any(InputStream.class), any(String.class));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void saveException()
    {
        photoBO.setConfiguration(dropboxCloudStorage, dbxClientV2);
        try
        {
            when(dropboxCloudStorage.uploadFile(any(DbxClientV2.class), any(InputStream.class), any(String.class))).thenReturn(false);
            when(file.getBytes()).thenReturn(new byte[0]);
            photoBO.save(photo, file);
            verify(dropboxCloudStorage, times(1)).uploadFile(any(DbxClientV2.class), any(InputStream.class), any(String.class));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void fixPhotos()
    {
        photoBO.setConfiguration(dropboxCloudStorage, dbxClientV2);

        when(dropboxCloudStorage.downloadFile(any(DbxClientV2.class), any(String.class))).thenReturn(inputStream);

        try
        {
            when(inputStream.read(any())).thenReturn(-1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        List<Photo> listPhotosExpected = photoBO.fixPhotos(photos);

        verify(dropboxCloudStorage, times(1)).downloadFile(any(DbxClientV2.class), any(String.class));
        assertTrue(listPhotosExpected.size() > 0);
        assertTrue(listPhotosExpected.get(0).getPhotoString() != null);
    }

    @Test
    public void fixPhotosIoException()
    {
        photoBO.setConfiguration(dropboxCloudStorage, dbxClientV2);

        when(dropboxCloudStorage.downloadFile(any(DbxClientV2.class), any(String.class))).thenReturn(null);

        try
        {
            when(inputStream.read(any())).thenReturn(-1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        List<Photo> listPhotosExpected = photoBO.fixPhotos(photos);

        verify(dropboxCloudStorage, times(1)).downloadFile(any(DbxClientV2.class), any(String.class));
        assertTrue(listPhotosExpected.size() > 0);
        assertTrue(listPhotosExpected.get(0).getPhotoString() != null);
    }

    @Test
    public void createPhotoWithPerson()
    {
        Person person = new Person();
        Photo photo = photoBO.createPhotoWithPerson(person);
        assertTrue(photo != null);
    }
}
