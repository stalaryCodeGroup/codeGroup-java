package com.stalary.codeGroup.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Peter on 2017-05-07.
 */
public class PropertyUtil {


    private static Properties loadPropertiesByFileName(String fileName) {
        Properties prop = new Properties();
        InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);

        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }

    /**
     * 是否是线上环境
     */
    public static boolean isProduction() {
        Properties properties = loadPropertiesByFileName("application.properties");
        return properties.get("spring.profiles.active").toString().equals("production");

    }

    public static boolean isDev() {
        return !isProduction();
    }

    public static String get(String propertyName) {
        Properties properties = loadPropertiesByFileName("application.properties");


        String config = properties.getProperty("spring.profiles.active");

        String fileName = "application-" + config + ".properties";


        return loadPropertiesByFileName(fileName).get(propertyName).toString();
    }

}