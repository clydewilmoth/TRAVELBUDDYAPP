package de.hs_mannheim.domain;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashSet;
import java.util.TreeSet;

import org.json.JSONObject;

public class System {
    
    private User current_user;
    private String api_key;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public HashSet<User> get_all_user(){
        return new HashSet<User>();
    }

    public boolean sign_in_user(String username, String password){
        return true;
    }

    public boolean sign_up_user(String username, String password, String hometown, int zip, 
                                String car_name, double car_co2_km, double car_avg_kmh, double bike_avg_kmh){
        return true;
    }

    public void sign_out_user(){}

    public boolean change_user_details(String username, String password, String hometown, int zip, 
                                String car_name, double car_co2_km, double car_avg_kmh, double bike_avg_kmh){
        return true;
    }

    public TreeSet<String> search(String hometown_or_zip){
        return new TreeSet<String>();
    }

    public TreeSet<String> random_destinations(){
        return new TreeSet<String>();
    }

    public String[] destination_details(String destination_zip){
        return new String[1];
    }

    public String current_weather() throws URISyntaxException, IOException, InterruptedException{
        
        String weather = "Fehler";
        double temperature = 1; 
        
        HttpClient http_client = HttpClient.newHttpClient();
        
        HttpRequest get_request = HttpRequest.newBuilder()
                                            .uri(new URI("https://api.openweathermap.org/data/2.5/weather?zip="+current_user.getZip()+",de&appid="+api_key+"&units=metric&lang=de)"))
                                            .GET()
                                            .build();
        
        HttpResponse get_response = http_client.send(get_request, BodyHandlers.ofString());

        JSONObject json = new JSONObject(get_response.body());
        weather = json.getJSONArray("weather").getJSONObject(0).getString("description");
        temperature = json.getJSONObject("main").getDouble("temp");
        
        if(weather.equals("Fehler"))
            return "Es ist ein Fehler aufgetreten!";
        else
            return weather + ": " + temperature + " °C";

    }

    public String weather_forecast(String destination_zip){
        return "";
    }

    public String distance(String destination_zip){
        return "";
    }

    public String[] travel_time(String destination_zip){
        return new String[1];
    }

    public String calc_co2(String destination_zip){
        return "";
    }
}

