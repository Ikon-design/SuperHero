package classes;

import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Map;

public class GeoCoder {
    public String name;
    public Map<String, String> local_names;
    public double lat;
    public double lon;
    public String country;
    public String state;

    static String deleteChar(String val) {
        String stringToChange;
        stringToChange = val.replace("[", "");
        stringToChange = stringToChange.replace("]", "");
        return stringToChange;
    }

    static public GeoCoder cityToLatLon(String city) {
        GeoCoder geoCoder = new GeoCoder();
        try {
            var client = HttpClient.newHttpClient();
            var rt = HttpRequest.newBuilder(URI.create("http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=1&appid=" + UserInformation.apiKey))
                    .header("accept", "application/json")
                    .build();
            var reponse = client.send(rt, HttpResponse.BodyHandlers.ofString());
            var parsedResponse = deleteChar(reponse.body().toString());
            ObjectMapper objectMapper = new ObjectMapper();
            geoCoder = objectMapper.readValue(parsedResponse, GeoCoder.class);
        } catch (Exception err) {
            err.printStackTrace();
        }
        return geoCoder;
    }
}
