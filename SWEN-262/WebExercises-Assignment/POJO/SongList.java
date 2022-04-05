package POJO;

import java.util.ArrayList;

public class SongList {

    private ArrayList<Song> songs;

    public SongList(ArrayList<Song> array) {
        this.songs = array;
    }

    /**
     * Takes the arrayList of objects and turns it into an array list of songs
     */

    public ArrayList<Song> getRecordings() {
        return this.songs;
    }



}
