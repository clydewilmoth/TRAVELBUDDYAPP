package de.hs_mannheim.domain;

public class User {

    private String username = "";
    private String password = "";
    private String hometown = "";
    private int zip;
    private String car_name = "";
    private double car_l_100km;
    private double car_avg_kmh;
    private double bike_avg_kmh;

    public User(){}

    public User(String username, String password, String hometown, int zip
                , String car_name, double car_l_100km, double car_avg_kmh, double bike_avg_kmh){
        this.username = username;
        this.password = password;
        this.hometown = hometown;
        this.zip = zip;
        this.car_name = car_name;
        this.car_l_100km = car_l_100km;
        this.car_avg_kmh = car_avg_kmh;
        this.bike_avg_kmh = bike_avg_kmh;
    }

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

    public double getCar_l_100km() {
        return car_l_100km;
    }

    public void setCar_l_100km(double car_l_100km) {
        this.car_l_100km = car_l_100km;
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
