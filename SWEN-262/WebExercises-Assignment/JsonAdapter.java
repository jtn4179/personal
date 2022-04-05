import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonAdapter extends Object {

    public JsonAdapter() {}

    public Song SongFromJson(JsonObject object) { return JsonToSong.createSong(object); }

    public ArrayList<Song> ListFromJson(JsonArray array) {
        return JsonToList.createList(array);
    }


}
