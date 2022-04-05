import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonToSong {

    /**
     * creates a song object from the JSON input
     * @param object the json object to be turned into a song
     * @return a Java song object with a title and artist name
     */
    public static Song createSong(JsonObject object) {

        String title = object.get("title").getAsString(); // gets the title

        // getting the artist from the artist-credit list
        JsonArray artistCredit = object.get("artist-credit").getAsJsonArray();
        JsonObject artist = artistCredit.get(0).getAsJsonObject();
        String artistName = artist.get("name").getAsString();

        return new Song(title, new Artist(artistName));
    }
}
