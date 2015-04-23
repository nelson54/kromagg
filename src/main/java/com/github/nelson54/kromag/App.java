package com.github.nelson54.kromag;

import com.github.nelson54.kromag.config.Configuration;
import com.github.nelson54.kromag.config.ConfigurationService;
import com.github.nelson54.kromag.ui.BookListTableModel;
import com.github.nelson54.kromag.ui.FrontEndFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.IOException;

public class App extends AbstractModule {

    @Inject FrontEndFactory frontEndFactory;
    @Inject LibraryService libraryService;
    @Inject ConfigurationService configurationService;

    public App(){}

    public static void main(String[] args) throws IOException {
        App app = new App();

        Injector injector = Guice.createInjector(app);
        injector.injectMembers(app);

        app.run();
    }

    void run() throws IOException {

        Configuration configuration = configurationService.getConfiguration();

        for (String directory : configuration.getDirectories()){
            libraryService.loadDirectory(directory);
        }

        TableModel tableModel = new BookListTableModel(libraryService);
        frontEndFactory.setTableModel(tableModel);

        JFrame window = frontEndFactory.getFrontEnd();
        window.pack();
        window.setVisible(true);
    }

    @Provides ConfigurationService getConfigurationService(){
        String appData = System.getenv("APPDATA");
        String directory = "kromag";
        String fileName = "settings.json";

        if(configurationService == null){
            configurationService = new ConfigurationService(appData, directory, fileName);
        }

        return configurationService;
    }

    @Override protected void configure() {}
}
