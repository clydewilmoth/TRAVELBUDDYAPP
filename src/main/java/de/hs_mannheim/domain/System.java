package de.hs_mannheim.domain;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

public class System {

    private User current_user = new User();
    private String api_key;
    private ArrayList<String> distances = new ArrayList<>();

    public System(String api_key) {
        this.api_key = api_key;
    }

    public void set_current_user_zip(String zip){
        this.current_user.setZip(Integer.parseInt(zip));
    }

    public void set_current_user_car_l_100km(double car_l_100km){
        this.current_user.setCar_l_100km(car_l_100km);
    }

    public void set_current_user_car_avg_kmh(double car_avg_kmh){
        this.current_user.setCar_avg_kmh(car_avg_kmh);
    }

    public void set_current_user_bike_avg_kmh(double bike_avg_kmh){
        this.current_user.setBike_avg_kmh(bike_avg_kmh);
    }

    public void decoding(String string){
        byte[] binary_data = Base64.decodeBase64(string);
        string = new String(binary_data);
    }

    public void encoding(String string){
        byte[] binary_data = new byte[string.length()];
        for(int i = 0; i < string.length(); i++){
            binary_data[i] = (byte) string.charAt(i);
        }
        string = Base64.encodeBase64String(binary_data);
    }

    public HashSet<User> get_all_user() throws FileNotFoundException, IOException{
        HashSet<User> all_users = new HashSet<>();
        File file = new File("/user_data.csv");
        String[] fileString = new String[8];

        if(file.exists() && file.isFile()){
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String path;

            while((path = bufferedReader.readLine()) != null){
                fileString = path.split(";");
                fileString = (String[]) Arrays.stream(fileString)
                                .map(string -> string.substring(0, string.length()-1))
                                .toArray();
                encoding(fileString[1]);
                all_users.add(new User(fileString[0], fileString[1], fileString[2],
                        Integer.parseInt(fileString[3]), fileString[4],
                        Double.parseDouble(fileString[5]),
                        Double.parseDouble(fileString[6]),
                        Double.parseDouble(fileString[7])));
            }
        }
        return all_users;
    }

    public boolean sign_in_user(String username, String password) throws IOException {
        ArrayList<User> mem = new ArrayList<>(get_all_user());

        for(User user : mem){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                current_user = new User(user.getUsername(), user.getPassword(),
                        user.getHometown(), user.getZip(), user.getCar_name(),
                        user.getCar_l_100km(), user.getCar_avg_kmh(), user.getBike_avg_kmh());
                return true;
            }
        }
        return false;
    }

    public boolean sign_up_user(String username, String password, String hometown, int zip, 
                                String car_name, double car_l_100km, double car_avg_kmh, double bike_avg_kmh) throws IOException {
        ArrayList<User> mem = new ArrayList<>(get_all_user());
        ArrayList<String> user_names = new ArrayList<>();

        for(User user : mem){
            user_names.add(user.getUsername());
        }
        if(!user_names.contains(username)){
                current_user = new User(username, password,
                        hometown, zip, car_name,
                        car_l_100km, car_avg_kmh, bike_avg_kmh);

                return true;
        }
        return false;
    }

    public void sign_out_user(){
        this.distances = new ArrayList<>();

        current_user = new User();
    }

    public boolean change_user_details(String username, String password, String hometown, int zip, 
                                String car_name, double car_l_100km, double car_avg_kmh, double bike_avg_kmh){
        return true;
    }

    public ArrayList<String> search(String hometown_or_zip){
        
        TreeSet<String> zip_set = new TreeSet<>();

        InputStream inputStream = System.class.getResourceAsStream("/zip.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null && zip_set.size()<200) {
                if(line.contains(hometown_or_zip)){
                    line = line.replace("\"", "");
                    zip_set.add(line);
                }
            }
        } catch (Exception e) {}
        
        return new ArrayList<>(zip_set);
    }

    public ArrayList<String> random_destinations_car(){
        
        calc_all_distances();

        ArrayList<String> mem = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        for(int i = 0; i<this.distances.size(); i++){
            if(Double.parseDouble(this.distances.get(i).split(";")[2])>150)
                mem.add(this.distances.get(i));
        }
        
        for(int i = 0; i<3; i++)
            result.add(mem.get((int) (Math.random()*mem.size())));

        return result;
    }

    public ArrayList<String> random_destinations_bike(){
        
        calc_all_distances();

        ArrayList<String> mem = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        for(int i = 0; i<this.distances.size(); i++){
            if(Double.parseDouble(this.distances.get(i).split(";")[2])<100)
                mem.add(this.distances.get(i));
        }
        
        for(int i = 0; i<3; i++)
            result.add(mem.get((int) (Math.random()*mem.size())));

        return result;
    }

    public String[] destination_details(String destination_zip){
        String[] result = new String[5];
        
        result[0] = distance(destination_zip); // Entfernung
        result[1] = travel_time(destination_zip)[0]; // Reisedauer Auto
        result[2] = travel_time(destination_zip)[1]; // Reisedauer Fahrrad
        result[3] = calc_l_consumption(destination_zip); // Kraftstoffverbrauch Auto
        result[4] = weather_forecast(destination_zip); // Wettervorhersage für die nächsten 3 Tage

        return result;
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
        
        HttpResponse<String> get_response = http_client.send(get_request, BodyHandlers.ofString());

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
            
            HttpResponse<String> get_response = http_client.send(get_request, BodyHandlers.ofString());

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
        
        double lon1 = -1;
        double lon2 = -1;
        double lat1 = -1;
        double lat2 = -1;

        InputStream inputStream = System.class.getResourceAsStream("/zip.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace("\"", "");
                
                if(line.split(";")[0].equals(destination_zip)){
                    lon2 = Double.parseDouble(line.split(";")[2]);
                    lat2 = Double.parseDouble(line.split(";")[3]);
                }

                if(line.split(";")[0].equals(""+current_user.getZip())){
                    lon1 = Double.parseDouble(line.split(";")[2]);
                    lat1 = Double.parseDouble(line.split(";")[3]);
                }
            }
        } catch (Exception e) {}

        if(lon1==-1||lon2==-1||lat1==-1||lat2==-1)
            return "Es ist ein Fehler aufgetreten!";

        double dLat = lat2-lat1;
        double dLon = lon2-lon1;

        double a = Math.pow(Math.sin(Math.toRadians(dLat/2.0)), 2) + Math.pow(Math.sin(Math.toRadians(dLon/2.0)), 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double distance = 6378.388 * 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0-a));

        return "" + (Math.round((distance * 1.25)*1000)/1000.0) + " km";
    
    }

    public void calc_all_distances(){

        if(!this.distances.isEmpty())
            return;

        InputStream inputStream = System.class.getResourceAsStream("/zip.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                line = line.replace("\"", "");
                
                this.distances.add(line.split(";")[0] + ";" + line.split(";")[1] + ";" + Double.parseDouble(distance(line.split(";")[0]).replace(" km", "")));
                
            }
        } catch (Exception e) {}
    }
    
    public String[] travel_time(String destination_zip){
        
        String[] result = new String[2];
        
        if(distance(destination_zip).equals("Es ist ein Fehler aufgetreten!")){
            result[0] = "Es ist ein Fehler aufgetreten!";
            result[1] = "Es ist ein Fehler aufgetreten!";
            return result;
        }

        result[0] = "" + (Math.round(((Double.parseDouble(distance(destination_zip).replace(" km", "")) / current_user.getCar_avg_kmh()))*1000) / 1000.0) + " h";
        result[1] = "" + (Math.round(((Double.parseDouble(distance(destination_zip).replace(" km", "")) / current_user.getBike_avg_kmh()))*1000) / 1000.0) + " h";
        
        return result;
    }

    public String calc_l_consumption(String destination_zip){
        
        if(distance(destination_zip).equals("Es ist ein Fehler aufgetreten!"))
            return "Es ist ein Fehler aufgetreten!";

        return "" + (Math.round((Double.parseDouble(distance(destination_zip).replace(" km", "")) * (current_user.getCar_l_100km() / 100.0))*1000)/1000.0) + " l";
    
    }
}

