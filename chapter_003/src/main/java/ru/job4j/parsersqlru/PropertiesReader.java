package ru.job4j.parsersqlru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesReader.class);

    public static Properties getProperties(String propFile) {
        Properties prop = new Properties();
        try (InputStream inputStream =  new FileInputStream(propFile)) {
            prop.load(inputStream);
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return prop;
    }
}
