import POJO.Artist;
import POJO.Song;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonToSong {

    public static Song createSong(JsonObject object) {

        String title = object.get("title").getAsString(); // gets the title

        // getting the artist from the artist-credit list
        JsonArray artistCredit = object.get("artist-credit").getAsJsonArray();
        JsonObject artist = artistCredit.get(0).getAsJsonObject();
        String artistName = artist.get("name").getAsString();

        return new Song(title, new Artist(artistName));
    }
}
