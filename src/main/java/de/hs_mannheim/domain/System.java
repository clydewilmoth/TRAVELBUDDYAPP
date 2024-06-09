package de.hs_mannheim.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import org.json.JSONObject;

import de.hs_mannheim.ui.Main;

public class System {
    
    private User current_user = new User();
    private String api_key;

    public System(String api_key) {
        this.api_key = api_key;
    }

    public void set_current_user_zip(String zip){
        this.current_user.setZip(Integer.parseInt(zip));
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

    public ArrayList<String> search(String hometown_or_zip){
        
        TreeSet<String> zip_set = new TreeSet<>();

        InputStream inputStream = Main.class.getResourceAsStream("/zip.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains(hometown_or_zip)&&zip_set.size()<200){
                    line = line.replace("\"", "");
                    zip_set.add(line);
                }
            }
        } catch (IOException e) {}
        
        return new ArrayList<>(zip_set);
    }

    public ArrayList<String> random_destinations_car(){
        return new ArrayList<String>();
    }

    public ArrayList<String> random_destinations_bike(){
        return new ArrayList<String>();
    }

    public String[] destination_details(String destination_zip){
        return new String[1];
    }

    public String current_weather(){
        
        String weather = "";
        double temperature = 1;

        try{
        HttpClient http_client = HttpClient.newHttpClient();
        
        HttpRequest get_request = HttpRequest.newBuilder()
                                            .uri(new URI("https://api.openweathermap.org/data/2.5/weather?zip="+current_user.getZip()+",de&appid="+api_key+"&units=metric&lang=de"))
                                            .GET()
                                            .build();
        
        HttpResponse get_response = http_client.send(get_request, BodyHandlers.ofString());

        JSONObject json = new JSONObject(((String)get_response.body()).substring(0,((String) get_response.body()).length()));
        weather = json.getJSONArray("weather").getJSONObject(0).getString("description");
        temperature = json.getJSONObject("main").getDouble("temp");
        } catch (Exception e){}
        
        if(weather.equals(""))
            return "Es ist ein Fehler aufgetreten!";
        else
            return weather + ": " + temperature + " °C";

    }

    public String weather_forecast(String destination_zip){
        
        String weather_day_1 = "";
        String weather_day_2 = "";
        String weather_day_3 = "";

        double temperature_day_1_1 = 1;
        double temperature_day_1_2 = 1;
        double temperature_day_1_3 = 1;
        double temperature_day_1_4 = 1;

        TreeSet<Double> temperature_day_1 = new TreeSet<>();
        double temperature_day_1_high = 1;
        double temperature_day_1_low = 1;

        double temperature_day_2_1 = 1;
        double temperature_day_2_2 = 1;
        double temperature_day_2_3 = 1;
        double temperature_day_2_4 = 1;

        TreeSet<Double> temperature_day_2 = new TreeSet<>();
        double temperature_day_2_high = 1;
        double temperature_day_2_low = 1;

        double temperature_day_3_1 = 1;
        double temperature_day_3_2 = 1;
        double temperature_day_3_3 = 1;
        double temperature_day_3_4 = 1;

        TreeSet<Double> temperature_day_3 = new TreeSet<>();
        double temperature_day_3_high = 1;
        double temperature_day_3_low = 1;


        try{
            HttpClient http_client = HttpClient.newHttpClient();
            
            HttpRequest get_request = HttpRequest.newBuilder()
                                                .uri(new URI("https://api.openweathermap.org/data/2.5/forecast?zip="+destination_zip+",de&appid="+api_key+"&units=metric&lang=de"))
                                                .GET()
                                                .build();
            
            HttpResponse get_response = http_client.send(get_request, BodyHandlers.ofString());

            JSONObject json = new JSONObject(((String)get_response.body()).substring(0,((String) get_response.body()).length()));
            weather_day_1 = json.getJSONArray("list").getJSONObject(11).getJSONArray("weather").getJSONObject(0).getString("description");
            weather_day_2 = json.getJSONArray("list").getJSONObject(19).getJSONArray("weather").getJSONObject(0).getString("description");
            weather_day_3 = json.getJSONArray("list").getJSONObject(27).getJSONArray("weather").getJSONObject(0).getString("description");
            
            temperature_day_1_1 = json.getJSONArray("list").getJSONObject(8).getJSONObject("main").getDouble("temp");
            temperature_day_1_2 = json.getJSONArray("list").getJSONObject(10).getJSONObject("main").getDouble("temp");
            temperature_day_1_3 = json.getJSONArray("list").getJSONObject(12).getJSONObject("main").getDouble("temp");
            temperature_day_1_4 = json.getJSONArray("list").getJSONObject(14).getJSONObject("main").getDouble("temp");
        
            temperature_day_2_1 = json.getJSONArray("list").getJSONObject(16).getJSONObject("main").getDouble("temp");
            temperature_day_2_2 = json.getJSONArray("list").getJSONObject(18).getJSONObject("main").getDouble("temp");
            temperature_day_2_3 = json.getJSONArray("list").getJSONObject(20).getJSONObject("main").getDouble("temp");
            temperature_day_2_4 = json.getJSONArray("list").getJSONObject(22).getJSONObject("main").getDouble("temp");
        
            temperature_day_3_1 = json.getJSONArray("list").getJSONObject(24).getJSONObject("main").getDouble("temp");
            temperature_day_3_2 = json.getJSONArray("list").getJSONObject(26).getJSONObject("main").getDouble("temp");
            temperature_day_3_3 = json.getJSONArray("list").getJSONObject(28).getJSONObject("main").getDouble("temp");
            temperature_day_3_4 = json.getJSONArray("list").getJSONObject(30).getJSONObject("main").getDouble("temp");
        
        } catch (Exception e){}
        
        temperature_day_1.add(temperature_day_1_1);
        temperature_day_1.add(temperature_day_1_2);
        temperature_day_1.add(temperature_day_1_3);
        temperature_day_1.add(temperature_day_1_4);

        temperature_day_1_high = (double) temperature_day_1.toArray()[temperature_day_1.size()-1];
        temperature_day_1_low = (double) temperature_day_1.toArray()[0];

        temperature_day_2.add(temperature_day_2_1);
        temperature_day_2.add(temperature_day_2_2);
        temperature_day_2.add(temperature_day_2_3);
        temperature_day_2.add(temperature_day_2_4);

        temperature_day_2_high = (double) temperature_day_2.toArray()[temperature_day_1.size()-1];
        temperature_day_2_low = (double) temperature_day_2.toArray()[0];

        temperature_day_3.add(temperature_day_3_1);
        temperature_day_3.add(temperature_day_3_2);
        temperature_day_3.add(temperature_day_3_3);
        temperature_day_3.add(temperature_day_3_4);

        temperature_day_3_high = (double) temperature_day_3.toArray()[temperature_day_1.size()-1];
        temperature_day_3_low = (double) temperature_day_3.toArray()[0];

        if(weather_day_1.equals("")||weather_day_2.equals("")||weather_day_3.equals(""))
            return "Es ist ein Fehler aufgetreten!";
        else
            return "Morgen: " + weather_day_1 + ": Minimum: " + temperature_day_1_low + " °C" + "; Maximum: " + temperature_day_1_high + " °C\n"
            + "Übermorgen: " + weather_day_2 + ": Minimum: " + temperature_day_2_low + " °C" + "; Maximum: " + temperature_day_2_high + " °C\n"
            + "Überübermorgen: " + weather_day_3 + ": Minimum: " + temperature_day_3_low + " °C" + "; Maximum: " + temperature_day_3_high + " °C";
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

