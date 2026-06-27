package com.ide.automation.setting;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigProperties {

    Properties properties = new Properties();
    InputStream file;

    public ConfigProperties(){
        try {
            file = Files.newInputStream(Paths.get("application.properties"));
            properties.load(file);
        }catch (IOException e){
            System.out.println("No fue posible acceder al archivo aplication.properties");
        }
    }

    public String getHost(){
        return properties.getProperty("host");
    }

    public String getPort(){
    return properties.getProperty("port");
    }
    public String getHeadless(){
        return properties.getProperty("headless");
    }

    public String getPathFormat(){
        return properties.getProperty("path.format");
    }

    public String getScreenSize(){
        return properties.getProperty("screenSize");
    }

    public long getScreenTimeout(){
        return Long.parseLong(properties.getProperty("screenTimeout"));
    }





}
