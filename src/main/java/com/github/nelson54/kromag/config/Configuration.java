package com.github.nelson54.kromag.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Configuration {

    @JsonProperty
    List<String> directories;

    public List<String> getDirectories() {
        return directories;
    }

    public void setDirectories(List<String> directories) {
        this.directories = directories;
    }
}
