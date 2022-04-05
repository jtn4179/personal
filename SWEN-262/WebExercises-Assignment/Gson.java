import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Gson extends Object {

    public Gson() {}

    public Song SongFromJson(JsonObject object) { return JsonToSong.createSong(object); }

    public ArrayList<Song> ListFromJson(JsonArray array) {
        return JsonToList.createList(array);
    }


}
