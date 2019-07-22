package com.uniritter.upphotos;

import cloudstorage.DropboxCloudStorage;
import com.dropbox.core.v2.DbxClientV2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.FileInputStream;
import java.io.InputStream;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CloudStorageTests
{
    private String fileNameToTest = "filenamemock";

    @Mock
    private DbxClientV2 client;
    @Mock
    private FileInputStream photo;
    @Mock
    private DropboxCloudStorage dropboxCloudStorage;

    @Test
    public void DropboxUploadPhoto()
    {
        when(dropboxCloudStorage.UploadFile(client, photo, fileNameToTest)).thenReturn(true);

        boolean uploadPhotoSuccess = dropboxCloudStorage.UploadFile(client, photo, fileNameToTest);

        verify(dropboxCloudStorage, times(1)).UploadFile(client, photo, fileNameToTest);

        assertTrue(uploadPhotoSuccess);
    }

    @Test
    public void DropboxDownloadPhoto()
    {
        InputStream photoCloud;

        when(dropboxCloudStorage.DownloadFile(client, fileNameToTest)).thenReturn(photo);

        photoCloud = dropboxCloudStorage.DownloadFile(client, fileNameToTest);

        verify(dropboxCloudStorage, times(1)).DownloadFile(client, fileNameToTest);

        assertTrue(photoCloud != null);
    }
}
