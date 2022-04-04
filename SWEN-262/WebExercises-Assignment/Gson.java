import POJO.Song;
import POJO.SongList;
import com.google.gson.JsonElement;

public class Gson extends Object {

    public Gson() {}

    public Song SongFromJson(JsonElement jsonElement) {
        return new JsonToSong().createSong(jsonElement);
    }

    public SongList ListFromJson(JsonElement element) {
        return new JsonToList().createList(element);
    }


}
