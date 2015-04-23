package com.github.nelson54.kromag;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class PathToAudioFileConverter {

    Optional<AudioFile> getAudioFileFromPath(Path path) {
        Optional<AudioFile> track = Optional.empty();
        AudioFile audioFile = new AudioFile();
        Mp3File mp3File = null;

        try {
            mp3File = new Mp3File(path.toString());


            ID3v2 tag =  mp3File.getId3v2Tag();

            audioFile.setTrack(tag.getTrack());
            audioFile.setTitle(tag.getTitle());
            audioFile.setArtist(tag.getArtist());

            track = Optional.of(audioFile);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }

        return track;
    }
}
