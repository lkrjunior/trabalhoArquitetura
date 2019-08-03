package com.uniritter.upphotos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class PropertiesConfig
{
    private static final String MESSAGE_DONT_LOAD_CONFIG = "Unable to find properties ";

    private static final Logger logger = LoggerFactory.getLogger(PropertiesConfig.class);

    @Autowired
    private Environment env;

    private String filePropertyName;

    public PropertiesConfig(String filePropertyName)
    {
        this.filePropertyName = filePropertyName;
    }

    public Properties getProperties()
    {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePropertyName))
        {
            Properties prop = new Properties();
            if (input == null)
            {
                String messageLog = String.format("%s%s", MESSAGE_DONT_LOAD_CONFIG, filePropertyName);
                logger.error(messageLog);
                return null;
            }
            prop.load(input);
            return prop;
        }
        catch (IOException ex)
        {
            logger.error(ex.getMessage());
        }

        return null;
    }
}
