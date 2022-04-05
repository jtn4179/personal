import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class JsonToList {

    /**
     * creates an array list from the JSON array
     * @param array the raw data JSON array
     * @return the SongList class
     */
    public static ArrayList<Song> createList(JsonArray array) {
        ArrayList<Song> songs = new ArrayList<>();

        // take the array and make a song from the JSON object at each index
        for (int i = 0; i < array.size(); i++) {
            JsonObject current = array.get(i).getAsJsonObject();

            Song newSong = JsonToSong.createSong(current);

            songs.add(newSong);
        }


        return songs;
    }
}
