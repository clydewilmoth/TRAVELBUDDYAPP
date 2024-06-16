package de.hs_mannheim.domain;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

public class System {

    private User current_user = new User();
    private ArrayList<User> all_user = new ArrayList<>();
    private String api_key;
    private HashMap<String, Destination> all_destinations = new HashMap<String, Destination>();

    public System(String api_key) {
        this.api_key = api_key;
        get_all_user();
    }

    public void set_current_user_zip(String zip) {
        this.current_user.setZip(zip);
    }

    public void set_current_user_car_l_100km(double car_l_100km) {
        this.current_user.setCar_l_100km(car_l_100km);
    }

    public void set_current_user_car_avg_kmh(double car_avg_kmh) {
        this.current_user.setCar_avg_kmh(car_avg_kmh);
    }

    public void set_current_user_bike_avg_kmh(double bike_avg_kmh) {
        this.current_user.setBike_avg_kmh(bike_avg_kmh);
    }

    public static String decoding(String string) {
        byte[] binary_data = new byte[string.length()];
        for (int i = 0; i < string.length(); i++) {
            binary_data[i] = (byte) string.charAt(i);
        }
        return new String(Base64.decodeBase64(binary_data));
    }

    public static String encoding(String string) {
        byte[] binary_data = new byte[string.length()];
        for (int i = 0; i < string.length(); i++) {
            binary_data[i] = (byte) string.charAt(i);
        }
        return Base64.encodeBase64String(binary_data);
    }

    public static double parseDouble(String s) throws NumberFormatException {
        if (s.equals(""))
            return 0;
        else
            return Double.parseDouble(s);
    }

    public void get_all_user() {
        String[] fileString = new String[8];

        InputStream inputStream = System.class.getResourceAsStream("/user_data.csv");
        String path;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((path = reader.readLine()) != null) {
                fileString = path.split(";");
                fileString[1] = decoding(fileString[1]);
                this.all_user.add(new User(fileString[0], fileString[1], fileString[2],
                        fileString[3], fileString[4],
                        Double.parseDouble(fileString[5]),
                        Double.parseDouble(fileString[6]),
                        Double.parseDouble(fileString[7])));
            }
        } catch (Exception e) {
            //
        }
    }

    public ArrayList<String> all_user_toString() {

        ArrayList<String> result = new ArrayList<>();

        for (User user : this.all_user) {
            result.add(user.toString());
        }

        return result;
    }

    public boolean sign_in_user(String username, String password) {

        for (User user : this.all_user) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                current_user = new User(user.getUsername(), user.getPassword(),
                        user.getHometown(), user.getZip(), user.getCar_name(),
                        user.getCar_l_100km(), user.getCar_avg_kmh(), user.getBike_avg_kmh());

                all_distances();
                return true;
            }
        }
        return false;
    }

    public boolean sign_up_user(String username, String password, String password_authentication, String hometown,
            String zip,
            String car_name, String car_l_100kmS, String car_avg_kmhS, String bike_avg_kmhS) {

        double car_l_100km;
        double car_avg_kmh;
        double bike_avg_kmh;

        try {
            car_l_100km = parseDouble(car_l_100kmS);
            car_avg_kmh = parseDouble(car_avg_kmhS);
            bike_avg_kmh = parseDouble(bike_avg_kmhS);
        } catch (NumberFormatException n) {
            return false;
        }

        if (username.equals("") || password.equals("") || hometown.equals("") || zip.equals(""))
            return false;

        if (!password.equals(password_authentication))
            return false;

        for (User user : this.all_user)
            if (user.getUsername().equals(username))
                return false;

        ArrayList<String> mem = search(zip);
        boolean bool = false;

        for (String line : mem)
            if (line.split(";")[1].equals(hometown)) {
                bool = true;
                break;
            }

        if (!bool)
            return false;

        current_user = new User(username, password, hometown, zip, car_name, car_l_100km, car_avg_kmh, bike_avg_kmh);

        this.all_user.add(current_user);

        write_to_file(all_user_toString(), "src/main/resources/user_data.csv");
        write_to_file(all_user_toString(), "src/test/resources/user_data.csv");

        all_distances();
        return true;
    }

    public boolean change_user_details(String username, String password, String hometown, String zip,
            String car_name, String car_l_100kmS, String car_avg_kmhS, String bike_avg_kmhS) {

        double car_l_100km;
        double car_avg_kmh;
        double bike_avg_kmh;

        try {
            car_l_100km = parseDouble(car_l_100kmS);
            car_avg_kmh = parseDouble(car_avg_kmhS);
            bike_avg_kmh = parseDouble(bike_avg_kmhS);
        } catch (NumberFormatException n) {
            return false;
        }

        if (!current_user.getPassword().equals(password))
            return false;

        if (username.equals("") || hometown.equals("") || zip.equals(""))
            return false;

        if (!current_user.getUsername().equals(username)) {
            for (User user : this.all_user)
                if (user.getUsername().equals(username))
                    return false;
        }

        ArrayList<String> mem = search(zip);
        boolean bool = false;

        for (String line : mem)
            if (line.split(";")[1].equals(hometown)) {
                bool = true;
                break;
            }

        if (!bool)
            return false;

        for (int i = 0; i < this.all_user.size(); i++)
            if (this.all_user.get(i).getUsername().equals(current_user.getUsername()))
                this.all_user.remove(i);

        write_to_file(all_user_toString(), "src/main/resources/user_data.csv");
        write_to_file(all_user_toString(), "src/test/resources/user_data.csv");

        this.current_user = new User(username, password, hometown, zip, car_name, car_l_100km, car_avg_kmh,
                bike_avg_kmh);
        this.all_user.add(current_user);

        write_to_file(all_user_toString(), "src/main/resources/user_data.csv");
        write_to_file(all_user_toString(), "src/test/resources/user_data.csv");

        all_distances();
        return true;

    }

    public boolean change_user_password(String old_password, String new_password, String new_password_authentication) {
        if (!this.current_user.getPassword().equals(old_password))
            return false;

        if (!new_password.equals(new_password_authentication))
            return false;

        for (int i = 0; i < this.all_user.size(); i++)
            if (this.all_user.get(i).getUsername().equals(current_user.getUsername()))
                this.all_user.remove(i);

        write_to_file(all_user_toString(), "src/main/resources/user_data.csv");
        write_to_file(all_user_toString(), "src/test/resources/user_data.csv");

        this.current_user.setPassword(new_password);
        this.all_user.add(current_user);

        write_to_file(all_user_toString(), "src/main/resources/user_data.csv");
        write_to_file(all_user_toString(), "src/test/resources/user_data.csv");

        return true;

    }

    public void write_to_file(ArrayList<String> lines, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < lines.size() - 1; i++) {
                writer.write(lines.get(i));
                writer.newLine();
            }
            writer.write(lines.getLast());
        } catch (IOException e) {
            //
        }
    }

    public void sign_out_user() {
        current_user = new User();
    }

    public String[] getDetails() {
        return new String[] { current_user.getUsername(), current_user.getHometown(),
                current_user.getZip(), current_user.getCar_name(),
                current_user.getCar_l_100km() == 0 ? "" : String.valueOf(current_user.getCar_l_100km()),
                current_user.getCar_avg_kmh() == 0 ? "" : String.valueOf(current_user.getCar_avg_kmh()),
                current_user.getBike_avg_kmh() == 0 ? "" : String.valueOf(current_user.getBike_avg_kmh()) };
    }

    public ArrayList<String> search(String hometown_or_zip) {

        TreeSet<String> zip_set = new TreeSet<>();

        InputStream inputStream = System.class.getResourceAsStream("/zip.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null && zip_set.size() < 200) {
                line = line.replace("\"", "");
                if (line.split(";")[0].contains(hometown_or_zip) || line.split(";")[1].contains(hometown_or_zip))
                    zip_set.add(line);
            }
        } catch (Exception e) {
            //
        }

        return new ArrayList<>(zip_set);
    }

    public ArrayList<String> random_destinations_car() {

        ArrayList<Destination> mem = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        int random = 0;

        for (Destination destination : this.all_destinations.values()) {
            if (destination.getDistance_from_user() > 150)
                mem.add(destination);
        }

        for (int i = 0; i < 3; i++) {
            random = (int) (Math.random() * mem.size());
            result.add(mem.get(random).getZip() + ";" + mem.get(random).getTown());
        }
        return result;
    }

    public ArrayList<String> random_destinations_bike() {

        ArrayList<Destination> mem = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        int random = 0;

        for (Destination destination : this.all_destinations.values()) {
            if (destination.getDistance_from_user() < 100)
                mem.add(destination);
        }

        for (int i = 0; i < 3; i++) {
            random = (int) (Math.random() * mem.size());
            result.add(mem.get(random).getZip() + ";" + mem.get(random).getTown());
        }
        return result;
    }

    public String[] destination_details(String destination_zip) {
        String[] result = new String[7];

        result[1] = weather_forecast(destination_zip)[0]; // Wettervorhersage für die nächsten 3 Tage
        result[2] = weather_forecast(destination_zip)[1];
        result[3] = weather_forecast(destination_zip)[2];
        result[4] = distance(destination_zip); // Entfernung
        result[5] = travel_time(destination_zip)[0]; // Reisedauer Auto
        result[6] = calc_l_consumption(destination_zip); // Kraftstoffverbrauch Auto
        result[7] = travel_time(destination_zip)[1]; // Reisedauer Fahrrad

        return result;
    }

    public String current_weather() {

        String weather = "";
        double temperature = 1;

        try {
            HttpClient http_client = HttpClient.newHttpClient();

            HttpRequest get_request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.openweathermap.org/data/2.5/weather?zip=" + current_user.getZip()
                            + ",de&appid=" + api_key + "&units=metric&lang=de"))
                    .GET()
                    .build();

            HttpResponse<String> get_response = http_client.send(get_request, BodyHandlers.ofString());

            JSONObject json = new JSONObject(get_response.body());
            weather = json.getJSONArray("weather").getJSONObject(0).getString("description");
            temperature = json.getJSONObject("main").getDouble("temp");
        } catch (Exception e) {
            //
        }

        if (weather.equals(""))
            return "Es ist ein Fehler aufgetreten!";
        else
            return weather + " " + temperature + "°C";

    }

    public String[] weather_forecast(String destination_zip) {

        String weather_day_1 = "";
        String weather_day_2 = "";
        String weather_day_3 = "";
        String[] result = new String[3];

        double temperature_day_1_1 = 1;
        double temperature_day_1_2 = 1;
        double temperature_day_1_3 = 1;
        double temperature_day_1_4 = 1;
        double temperature_day_1_5 = 1;

        TreeSet<Double> temperature_day_1 = new TreeSet<>();
        double temperature_day_1_high = 1;
        double temperature_day_1_low = 1;

        double temperature_day_2_1 = 1;
        double temperature_day_2_2 = 1;
        double temperature_day_2_3 = 1;
        double temperature_day_2_4 = 1;
        double temperature_day_2_5 = 1;

        TreeSet<Double> temperature_day_2 = new TreeSet<>();
        double temperature_day_2_high = 1;
        double temperature_day_2_low = 1;

        double temperature_day_3_1 = 1;
        double temperature_day_3_2 = 1;
        double temperature_day_3_3 = 1;
        double temperature_day_3_4 = 1;
        double temperature_day_3_5 = 1;

        TreeSet<Double> temperature_day_3 = new TreeSet<>();
        double temperature_day_3_high = 1;
        double temperature_day_3_low = 1;

        String day1date = "";
        String day2date = "";
        String day3date = "";

        try {
            HttpClient http_client = HttpClient.newHttpClient();

            HttpRequest get_request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.openweathermap.org/data/2.5/forecast?zip=" + destination_zip
                            + ",de&appid=" + api_key + "&units=metric&lang=de"))
                    .GET()
                    .build();

            HttpResponse<String> get_response = http_client.send(get_request, BodyHandlers.ofString());

            JSONObject json = new JSONObject(get_response.body());

            int day1_1 = 0;
            int day1_2 = 0;
            int day1_3 = 0;
            int day1_4 = 0;
            int day1_5 = 0;
            int day2_1 = 0;
            int day2_2 = 0;
            int day2_3 = 0;
            int day2_4 = 0;
            int day2_5 = 0;
            int day3_1 = 0;
            int day3_2 = 0;
            int day3_3 = 0;
            int day3_4 = 0;
            int day3_5 = 0;

            for (int i = 0; i < 40; i++) {

                if (json.getJSONArray("list").getJSONObject(i).getString("dt_txt").split(" ")[1].startsWith("00")) {
                    day1_1 = i + 3; // 9 Uhr
                    day1_2 = day1_1 + 1; // 12 Uhr
                    day1_3 = day1_2 + 1; // 15 Uhr
                    day1_4 = day1_3 + 1; // 18 Uhr
                    day1_5 = day1_4 + 1; // 21 Uhr
                    break;
                }
            }

            for (int i = day1_5; i < 40; i++) {

                if (json.getJSONArray("list").getJSONObject(i).getString("dt_txt").split(" ")[1].startsWith("00")) {
                    day2_1 = i + 3; // 9 Uhr
                    day2_2 = day2_1 + 1; // 12 Uhr
                    day2_3 = day2_2 + 1; // 15 Uhr
                    day2_4 = day2_3 + 1; // 18 Uhr
                    day2_5 = day2_4 + 1; // 21 Uhr
                    break;
                }
            }

            for (int i = day2_5 + 1; i < 40; i++) {

                if (json.getJSONArray("list").getJSONObject(i).getString("dt_txt").split(" ")[1].startsWith("00")) {
                    day3_1 = i + 3; // 9 Uhr
                    day3_2 = day3_1 + 1; // 12 Uhr
                    day3_3 = day3_2 + 1; // 15 Uhr
                    day3_4 = day3_3 + 1; // 18 Uhr
                    day3_5 = day3_4 + 1; // 21 Uhr
                    break;
                }
            }

            day1date = json.getJSONArray("list").getJSONObject(day1_1).getString("dt_txt").split(" ")[0];
            day1date = day1date.split("-")[2] + "." + day1date.split("-")[1] + "." + day1date.split("-")[0];
            day2date = json.getJSONArray("list").getJSONObject(day2_1).getString("dt_txt").split(" ")[0];
            day2date = day2date.split("-")[2] + "." + day2date.split("-")[1] + "." + day2date.split("-")[0];
            day3date = json.getJSONArray("list").getJSONObject(day3_1).getString("dt_txt").split(" ")[0];
            day3date = day3date.split("-")[2] + "." + day3date.split("-")[1] + "." + day3date.split("-")[0];

            weather_day_1 = json.getJSONArray("list").getJSONObject(day1_3).getJSONArray("weather").getJSONObject(0)
                    .getString("description");
            weather_day_2 = json.getJSONArray("list").getJSONObject(day2_3).getJSONArray("weather").getJSONObject(0)
                    .getString("description");
            weather_day_3 = json.getJSONArray("list").getJSONObject(day3_3).getJSONArray("weather").getJSONObject(0)
                    .getString("description");

            temperature_day_1_1 = json.getJSONArray("list").getJSONObject(day1_1).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_1_2 = json.getJSONArray("list").getJSONObject(day1_2).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_1_3 = json.getJSONArray("list").getJSONObject(day1_3).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_1_4 = json.getJSONArray("list").getJSONObject(day1_4).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_1_5 = json.getJSONArray("list").getJSONObject(day1_5).getJSONObject("main")
                    .getDouble("temp");

            temperature_day_2_1 = json.getJSONArray("list").getJSONObject(day2_1).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_2_2 = json.getJSONArray("list").getJSONObject(day2_2).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_2_3 = json.getJSONArray("list").getJSONObject(day2_3).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_2_4 = json.getJSONArray("list").getJSONObject(day2_4).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_2_5 = json.getJSONArray("list").getJSONObject(day2_5).getJSONObject("main")
                    .getDouble("temp");

            temperature_day_3_1 = json.getJSONArray("list").getJSONObject(day3_1).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_3_2 = json.getJSONArray("list").getJSONObject(day3_2).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_3_3 = json.getJSONArray("list").getJSONObject(day3_3).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_3_4 = json.getJSONArray("list").getJSONObject(day3_4).getJSONObject("main")
                    .getDouble("temp");
            temperature_day_3_5 = json.getJSONArray("list").getJSONObject(day3_5).getJSONObject("main")
                    .getDouble("temp");

        } catch (Exception e) {
            //
        }

        temperature_day_1.add(temperature_day_1_1);
        temperature_day_1.add(temperature_day_1_2);
        temperature_day_1.add(temperature_day_1_3);
        temperature_day_1.add(temperature_day_1_4);
        temperature_day_1.add(temperature_day_1_5);

        temperature_day_1_high = (double) temperature_day_1.toArray()[temperature_day_1.size() - 1];
        temperature_day_1_low = (double) temperature_day_1.toArray()[0];

        temperature_day_2.add(temperature_day_2_1);
        temperature_day_2.add(temperature_day_2_2);
        temperature_day_2.add(temperature_day_2_3);
        temperature_day_2.add(temperature_day_2_4);
        temperature_day_2.add(temperature_day_2_5);

        temperature_day_2_high = (double) temperature_day_2.toArray()[temperature_day_2.size() - 1];
        temperature_day_2_low = (double) temperature_day_2.toArray()[0];

        temperature_day_3.add(temperature_day_3_1);
        temperature_day_3.add(temperature_day_3_2);
        temperature_day_3.add(temperature_day_3_3);
        temperature_day_3.add(temperature_day_3_4);
        temperature_day_3.add(temperature_day_3_5);

        temperature_day_3_high = (double) temperature_day_3.toArray()[temperature_day_2.size() - 1];
        temperature_day_3_low = (double) temperature_day_3.toArray()[0];

        if (weather_day_1.equals("") || weather_day_2.equals("") || weather_day_3.equals("")) {
            result[0] = "Es ist ein Fehler aufgetreten!";
            result[1] = "Es ist ein Fehler aufgetreten!";
            result[2] = "Es ist ein Fehler aufgetreten!";
        } else {
            result[0] = day1date + " " + weather_day_1 + " H: " + temperature_day_1_high + "°C" + " T: "
                    + temperature_day_1_low + "°C";
            result[1] = day2date + " " + weather_day_2 + " H: " + temperature_day_2_high + "°C" + " T: "
                    + temperature_day_2_low + "°C";
            result[2] = day3date + " " + weather_day_3 + " H: " + temperature_day_3_high + "°C" + " T: "
                    + temperature_day_3_low + "°C";
        }

        return result;
    }

    public boolean all_distances() {

        double lon1 = -1;
        double lon2 = -1;
        double lat1 = -1;
        double lat2 = -1;
        double dLat;
        double dLon;
        double a;
        double distance;
        double result;

        InputStream inputStream = System.class.getResourceAsStream("/zip.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace("\"", "");

                if (line.split(";")[0].equals(current_user.getZip())) {
                    lon1 = Double.parseDouble(line.split(";")[2]);
                    lat1 = Double.parseDouble(line.split(";")[3]);
                }
            }
        } catch (Exception e) {
            //
        }

        InputStream inputStream2 = System.class.getResourceAsStream("/zip.csv");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream2))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace("\"", "");

                lon2 = Double.parseDouble(line.split(";")[2]);
                lat2 = Double.parseDouble(line.split(";")[3]);

                if (lon1 == -1 || lon2 == -1 || lat1 == -1 || lat2 == -1)
                    return false;

                dLat = lat2 - lat1;
                dLon = lon2 - lon1;

                a = Math.pow(Math.sin(Math.toRadians(dLat / 2.0)), 2)
                        + Math.pow(Math.sin(Math.toRadians(dLon / 2.0)), 2) * Math.cos(Math.toRadians(lat1))
                                * Math.cos(Math.toRadians(lat2));

                distance = 6378.388 * 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));

                result = Math.round(distance * 1.25 * 100) / 100.0;

                this.all_destinations.put(line.split(";")[0],
                        new Destination(line.split(";")[1], line.split(";")[0], result));

            }
        } catch (Exception e) {
            //
        }

        return true;
    }

    public String distance(String destination_zip) {

        return "" + this.all_destinations.get(destination_zip).getDistance_from_user() + " km";

    }

    public String[] travel_time(String destination_zip) {

        String[] result = new String[2];

        if (current_user.getCar_avg_kmh() == 0)
            result[0] = "";
        else
            result[0] = "" + (Math.round(Double.parseDouble(distance(destination_zip).replace(" km", ""))
                    / current_user.getCar_avg_kmh() * 100) / 100.0) + " h";

        if (current_user.getBike_avg_kmh() == 0)
            result[1] = "";
        else
            result[1] = "" + (Math.round(Double.parseDouble(distance(destination_zip).replace(" km", ""))
                    / current_user.getBike_avg_kmh() * 100) / 100.0) + " h";

        return result;
    }

    public String calc_l_consumption(String destination_zip) {

        if (current_user.getCar_l_100km() == 0)
            return "";
        return "" + (Math.round(Double.parseDouble(distance(destination_zip).replace(" km", ""))
                * current_user.getCar_l_100km() / 100.0 * 100) / 100.0) + " l";

    }
}
