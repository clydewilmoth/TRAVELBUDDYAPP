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

    public boolean sign_up_user(String username, String password, String hometown, String zip,
                                String car_name, String car_co2_km, String car_avg_kmh, String bike_avg_kmh){
        return true;
    }

    public void sign_out_user(){}

    public boolean change_user_details(String username, String password, String hometown, String zip,
                                String car_name, String car_co2_km, String car_avg_kmh, String bike_avg_kmh){
        return true;
    }

    public ArrayList<String> search(String hometown_or_zip){
        return running_system.search(hometown_or_zip);
    }

    public ArrayList<String> random_destinations_car(){
        return running_system.random_destinations_car();
    }

    public ArrayList<String> random_destinations_bike(){
        return running_system.random_destinations_bike();
    }

    public String[] destination_details(String destination_zip){
        return running_system.destination_details(destination_zip);
    }

    public String current_weather(){
        return running_system.current_weather();
    }

    public String[] getDetails(){
        return System.getDetails();
    }

    public String distnace(String zip){
        return running_system.distance(zip);
    }
}
