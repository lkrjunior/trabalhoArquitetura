package com.uniritter.upphotos;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Properties;

@Component
public class DropboxConfig
{
    private static final String PROPERTIES = "cloudstorage.properties";
    private static final String PROPERTY_ACCESS_TOKEN = "dropbox.accesstoken";
    private static final String CLIENT_IDENTIFIER = "dropbox/upphotos";
    private static final String MESSAGE_ERROR = "Unable to instance DbxClientV2";

    private static final Logger logger = LoggerFactory.getLogger(DropboxConfig.class);

    private DbxClientV2 dropboxClient;

    public DropboxConfig()
    {
        try
        {
            PropertiesConfig config = new PropertiesConfig(PROPERTIES);
            Properties prop = config.getProperties();
            String accessToken = prop.getProperty(PROPERTY_ACCESS_TOKEN);

            DbxRequestConfig dropboxRequestConfig = DbxRequestConfig.newBuilder(CLIENT_IDENTIFIER).build();
            dropboxClient = new DbxClientV2(dropboxRequestConfig, accessToken);
        }
        catch (Exception ex)
        {
            String messageLog = String.format("%s %s", MESSAGE_ERROR, ex.getMessage());
            logger.error(messageLog);
        }
    }

    public DbxClientV2 getDropboxClient()
    {
        return dropboxClient;
    }
}
