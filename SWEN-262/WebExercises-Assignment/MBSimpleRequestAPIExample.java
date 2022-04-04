import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MBSimpleRequestAPIExample {
    private static final String SEARCH_URL =
            "https://musicbrainz.org/ws/2/recording?query=";

    private static final String XML_FORMAT = "&fmt=xml";

    private static final String JSON_FORMAT = "&fmt=json";

    public static void main(String[] args) throws IOException {
        // %20 used to encode spaces in URL
        String query = "\"Carry%20on%20Wayward%20Son\"";

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
                Gson gson = new Gson();
                JsonElement element = new JsonParser().parse(line);
                JsonElement recordings = element.getAsJsonObject().get("recordings");

                // need to work on making the song list from the recordings json element
                // then from there iterate through the song list and print the songs

                // then work on doing the URL shit

                //System.out.println(song);

                System.out.println(line); // there is actually only 1 line that is printed (all the output is one line)

            }
        } else {
            System.out.println("Server responded with " +  responseCode +
                    ", you may be being throttled. Try again shortly.");
        }

        input.close();
    }
}