import POJO.SongList;
import com.google.gson.JsonElement;

public class JsonToList {

    public SongList createList(JsonElement element) {

        return new SongList(element);
    }
}
