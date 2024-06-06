package de.hs_mannheim.domain;

public class User {

    private String username = "";
    private String password = "";
    private String hometown = "";
    private int zip;
    private String car_name = "";
    private String car_co2_kmh = "";
    private String car_avg_kmh = "";
    private String bike_avg_kmh = "";

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

    public String getCar_co2_kmh() {
        return car_co2_kmh;
    }

    public void setCar_co2_kmh(String car_co2_kmh) {
        this.car_co2_kmh = car_co2_kmh;
    }

    public String getCar_avg_kmh() {
        return car_avg_kmh;
    }

    public void setCar_avg_kmh(String car_avg_kmh) {
        this.car_avg_kmh = car_avg_kmh;
    }

    public String getBike_avg_kmh() {
        return bike_avg_kmh;
    }

    public void setBike_avg_kmh(String bike_avg_kmh) {
        this.bike_avg_kmh = bike_avg_kmh;
    }
}
