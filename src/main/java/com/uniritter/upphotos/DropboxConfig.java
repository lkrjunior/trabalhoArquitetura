package com.uniritter.upphotos;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.stereotype.Component;
import java.util.Properties;

@Component
public class DropboxConfig
{
    private final String _properties = "cloudstorage.properties";
    private final String _property_access_token = "dropbox.accesstoken";
    private final String _clientidentifier = "dropbox/java-tutorial";

    private String accessToken;
    private DbxClientV2 dropboxClient;
    private DbxRequestConfig dropboxRequestConfig;

    public DropboxConfig()
    {
        PropertiesConfig config = new PropertiesConfig(_properties);
        Properties prop = config.GetProperties();
        accessToken = prop.getProperty(_property_access_token);
    }

    public void SetConfigurationForDropbox()
    {
        dropboxRequestConfig = DbxRequestConfig.newBuilder(_clientidentifier).build();
        dropboxClient = new DbxClientV2(dropboxRequestConfig, accessToken);
    }

    public DbxClientV2 GetDropboxClient()
    {
        return dropboxClient;
    }
}
