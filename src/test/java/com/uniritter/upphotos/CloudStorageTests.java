package com.uniritter.upphotos;

import cloudstorage.DropboxCloudStorage;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DbxUserFilesRequests;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CloudStorageTests
{
    private String fileNameToTest = "filenamemock";
    private String messageError = "Error to download file";

    private DbxClientV2 client;
    private DbxUserFilesRequests files;
    private UploadBuilder uploadBuilder;
    private DbxDownloader<FileMetadata> fileDownload;
    private DbxException dbxException;

    private InputStream photoDownload;
    private FileInputStream photo;
    private FileMetadata fileMetadata;

    private DropboxCloudStorage dropboxCloudStorage;

    @Before
    public void Setup()
    {
        client = mock(DbxClientV2.class);
        files = mock(DbxUserFilesRequests.class);
        uploadBuilder = mock(UploadBuilder.class);
        fileDownload = mock(DbxDownloader.class);
        dbxException = mock(DbxException.class);

        photoDownload = mock(InputStream.class);
        photo = mock(FileInputStream.class);
        fileMetadata = mock(FileMetadata.class);

        dropboxCloudStorage = new DropboxCloudStorage();
    }

    @Test
    public void DropboxUploadPhoto()
    {
        ///region Arrange
        try
        {
            //void
            //doNothing().when(client.files().uploadBuilder("/" + fileNameToTest).uploadAndFinish(photo));
            when(client.files()).thenReturn(files);
            when(files.uploadBuilder(any(String.class))).thenReturn(uploadBuilder);
            when(uploadBuilder.uploadAndFinish(photo)).thenReturn(fileMetadata);
        }
        catch (DbxException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        ///endregion

        ///region Act
        boolean uploadPhotoSuccess = dropboxCloudStorage.UploadFile(client, photo, fileNameToTest);
        ///endregion

        ///region Assert
        assertTrue(uploadPhotoSuccess);
        ///endregion
    }

    @Test
    public void DropboxUploadPhotoDbxException()
    {
        ///region Arrange
        try
        {
            when(client.files()).thenReturn(files);
            when(files.uploadBuilder(any(String.class))).thenReturn(uploadBuilder);
            when(uploadBuilder.uploadAndFinish(photo)).thenThrow(dbxException);
        }
        catch (IOException|DbxException e)
        {
            e.printStackTrace();
        }
        ///endregion

        ///region Act
        boolean uploadPhotoSuccess = dropboxCloudStorage.UploadFile(client, photo, fileNameToTest);
        ///endregion

        ///region Assert
        assertFalse(uploadPhotoSuccess);
        ///endregion
    }

    @Test
    public void DropboxDownloadPhoto()
    {
        ///region Arrange
        InputStream photoCloud;

        try
        {
            when(client.files()).thenReturn(files);
            when(files.download(any(String.class))).thenReturn(fileDownload);
            when(fileDownload.getInputStream()).thenReturn(photoDownload);
        }
        catch (DbxException e)
        {
            e.printStackTrace();
        }
        ///endregion

        ///region Act
        photoCloud = dropboxCloudStorage.DownloadFile(client, fileNameToTest);
        ///endregion

        ///region Assert
        assertTrue(photoCloud != null);
        ///endregion
    }

    //@Test(expected = DbxException.class)
    @Test
    public void DropboxDownloadPhotoDbxException()
    {
        ///region Arrange
        InputStream photoCloud;

        try
        {
            when(client.files()).thenReturn(files);
            when(files.download(any(String.class))).thenThrow(new DbxException(messageError));
        }
        catch (DbxException e)
        {
            e.printStackTrace();
        }
        ///endregion

        ///region Act
        photoCloud = dropboxCloudStorage.DownloadFile(client, fileNameToTest);
        ///endregion

        ///region Assert
        assert(photoCloud == null);
        ///endregion
    }
}
