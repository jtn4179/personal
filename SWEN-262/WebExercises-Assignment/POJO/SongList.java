package POJO;

import POJO.Song;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class SongList {

    private JsonElement element;
    private ArrayList<Song> recordings;

    public SongList(JsonElement element) {
        this.element = element;
    }

    /**
     * Creates and updates the internal list of recordings from the JSON string
     */
    private void makeRecordingsList() {

    }

    public ArrayList<Song> getRecordings() {
        return this.recordings;
    }



}
