package com.github.nelson54.kromag;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

public class LibraryService {

    PathToAudioFileConverter pathToAudioFileConverter;
    Set<AudioFile> files;

    @Inject
    public LibraryService(PathToAudioFileConverter pathToAudioFileConverter) {
        this.files = new HashSet<>();
        this.pathToAudioFileConverter = pathToAudioFileConverter;
    }

    void loadDirectory(String directory) throws IOException {
        Path path = Paths.get(directory);

        Files.find(path, 999, getMatcher())
                .map(pathToAudioFileConverter::getAudioFileFromPath)
                .forEach(file -> file.ifPresent(files::add));
    }

    private BiPredicate<Path, BasicFileAttributes> getMatcher(){
        return (path, basicFileAttributes) ->
                basicFileAttributes.isRegularFile()
                        && path.toString().toLowerCase().endsWith(".mp3");
    }

    public Set<AudioFile> getFiles() {
        return files;
    }

}
