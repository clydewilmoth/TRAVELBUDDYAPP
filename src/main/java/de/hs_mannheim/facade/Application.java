package de.hs_mannheim.facade;

import java.util.ArrayList;

import de.hs_mannheim.domain.System;

public class Application {
    
    private System running_system;

    public Application(String api_key){
        this.running_system = new System(api_key);
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
        return new ArrayList<String>();
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
        return "";
    }

}
