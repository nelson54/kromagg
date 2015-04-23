package com.github.nelson54.kromag.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ConfigurationService {

    ObjectMapper objectMapper;
    String pathSeperator;
    String appData;
    String directory;
    String fileName;

    public ConfigurationService(String appData, String directory, String fileName) {
        this.pathSeperator = "/";
        this.appData = appData;
        this.directory = directory;
        this.fileName = fileName;
        objectMapper = new ObjectMapper();
    }

    public Configuration getConfiguration(){
        Configuration configuration = null;
        try {
            if(!Files.exists(getConfigPath())){
                    createNewConfiguration();
            }

            configuration = objectMapper.readValue(getConfigFile().toFile(), Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configuration;
    }

    public void createNewConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        byte[] json = objectMapper.writeValueAsBytes(configuration);

        Files.createDirectory(getConfigPath());
        Files.write(getConfigFile(), json, StandardOpenOption.CREATE_NEW);
    }



    public String getAppData() {
        return appData;
    }

    public void setAppData(String appData) {
        this.appData = appData;
    }

    private Path getConfigPath(){
        return Paths.get(appData + pathSeperator + directory);
    }

    private Path getConfigFile(){
        return Paths.get(appData + pathSeperator + directory + pathSeperator + fileName);
    }


}
