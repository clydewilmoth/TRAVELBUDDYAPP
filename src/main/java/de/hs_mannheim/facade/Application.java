package de.hs_mannheim.facade;

import java.util.TreeSet;

import de.hs_mannheim.domain.System;

public class Application {
    
    private System running_system;

    public Application(){
        this.running_system = new System();
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

}
