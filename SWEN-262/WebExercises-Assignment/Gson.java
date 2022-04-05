import POJO.Song;
import POJO.SongList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Gson extends Object {

    public Gson() {}

    public Song SongFromJson(JsonObject object) { return new JsonToSong().createSong(object); }

    public SongList ListFromJson(JsonArray array) {
        return new JsonToList().createList(array);
    }


}
