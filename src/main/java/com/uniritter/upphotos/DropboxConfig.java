package com.uniritter.upphotos;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.stereotype.Component;
import java.util.Properties;

@Component
public class DropboxConfig
{
    private static final String PROPERTIES = "cloudstorage.properties";
    private static final String PROPERTY_ACCESS_TOKEN = "dropbox.accesstoken";
    private static final String CLIENT_IDENTIFIER = "dropbox/java-tutorial";

    private String accessToken;
    private DbxClientV2 dropboxClient;

    public DropboxConfig()
    {
        PropertiesConfig config = new PropertiesConfig(PROPERTIES);
        Properties prop = config.getProperties();
        accessToken = prop.getProperty(PROPERTY_ACCESS_TOKEN);
    }

    public void setConfigurationForDropbox()
    {
        DbxRequestConfig dropboxRequestConfig = DbxRequestConfig.newBuilder(CLIENT_IDENTIFIER).build();
        dropboxClient = new DbxClientV2(dropboxRequestConfig, accessToken);
    }

    public DbxClientV2 getDropboxClient()
    {
        return dropboxClient;
    }
}
