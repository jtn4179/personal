import POJO.Artist;
import POJO.Song;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonToSong {

    public Song createSong(JsonElement element) {
        JsonObject song = element.getAsJsonObject();


        return new Song(1, song.get("title").toString(), new Artist("artist"));
    }
}
