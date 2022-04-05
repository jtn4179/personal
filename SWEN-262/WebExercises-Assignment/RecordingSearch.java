import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class RecordingSearch {
    private static final String SEARCH_URL = "https://musicbrainz.org/ws/2/recording?query=";

    private static final String JSON_FORMAT = "&fmt=json";

    public static void main(String[] args) throws IOException {
        // %20 used to encode spaces in URL
        String query = "\"";

         for (int i = 0; i < args.length; i++) {
             query += args[i];
             if (i + 1 <= args.length) {
                 query += "%";
             }
         }
         query += "\"";

        URL queryUrl = new URL(SEARCH_URL + query + JSON_FORMAT);
        HttpsURLConnection connection =
                (HttpsURLConnection)queryUrl.openConnection();

        // a meaningful user agent is required to avoid throttling
        // DO NOT exceed more than 1 request per second
        connection.setRequestProperty("User-Agent",
                "SWEN-262 Semester Project (https://www.se.rit.edu)");

        connection.connect();
        InputStream input = connection.getInputStream();
        int responseCode = connection.getResponseCode();
        if(responseCode == 200) {
            InputStreamReader iReader = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(iReader);
            String line;
            while((line = reader.readLine()) != null) {
                String jsonString = line; // renmaing for understanding purposes

                // create an adapter and parser
                JsonAdapter adapter = new JsonAdapter();
                JsonParser parser = new JsonParser();
                JsonElement response = parser.parse(jsonString);
                // turn the response into a jsonArray of the recordings
                JsonObject obj = response.getAsJsonObject();
                JsonArray recordings = obj.getAsJsonArray("recordings");

                // use the adapter to turn the json array into an array of songs
                ArrayList<Song> songs = adapter.ListFromJson(recordings);

                // nice printing
                for (int i = 0; i < songs.size(); i++) {
                    System.out.println((i+1) + ") " + songs.get(i));
                }

            }
        } else {
            System.out.println("Server responded with " +  responseCode +
                    ", you may be being throttled. Try again shortly.");
        }

        input.close();
    }

}