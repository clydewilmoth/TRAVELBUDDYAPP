package de.hs_mannheim.domain;

public class Destination {
    
    private String town;
    private String zip;
    private double distance_from_user;
    
    public Destination(String town,String zip, double distance_from_user) {
        this.town = town;
        this.zip = zip;
        this.distance_from_user = distance_from_user;
    }
    
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
    
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public double getDistance_from_user() {
        return distance_from_user;
    }
    public void setDistance_from_user(double distance_from_user) {
        this.distance_from_user = distance_from_user;
    }

}
