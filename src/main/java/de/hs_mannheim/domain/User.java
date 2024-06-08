package de.hs_mannheim.domain;

public class User {

    private String username = "";
    private String password = "";
    private String hometown = "";
    private int zip;
    private String car_name = "";
    private double car_co2_kmh;
    private double car_avg_kmh;
    private double bike_avg_kmh;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public double getCar_co2_kmh() {
        return car_co2_kmh;
    }

    public void setCar_co2_kmh(double car_co2_kmh) {
        this.car_co2_kmh = car_co2_kmh;
    }

    public double getCar_avg_kmh() {
        return car_avg_kmh;
    }

    public void setCar_avg_kmh(double car_avg_kmh) {
        this.car_avg_kmh = car_avg_kmh;
    }

    public double getBike_avg_kmh() {
        return bike_avg_kmh;
    }

    public void setBike_avg_kmh(double bike_avg_kmh) {
        this.bike_avg_kmh = bike_avg_kmh;
    }
}