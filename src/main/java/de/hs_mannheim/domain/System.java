package de.hs_mannheim.domain;

import java.util.HashSet;
import java.util.TreeSet;

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

    public String current_weather(){
        return "";
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

