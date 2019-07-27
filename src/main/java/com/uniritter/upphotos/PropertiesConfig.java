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
    private final String _message_dont_load_config = "Unable to find properties ";

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
                System.out.println(_message_dont_load_config + filePropertyName);
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
