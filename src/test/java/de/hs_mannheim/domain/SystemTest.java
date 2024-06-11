package de.hs_mannheim.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class SystemTest {
    
    System current_system = new System("35a75437476f12302f72e55d368485db");
    
    @Test
    public void weather_forecast(){

        assertNotEquals("Es ist ein Fehler aufgetreten!",current_system.weather_forecast("68161"));
    }

    @Test
    public void current_weather(){

        current_system.set_current_user_zip("68161");

        assertNotEquals("Es ist ein Fehler aufgetreten!",current_system.current_weather());
    }

    @Test
    public void search(){

        ArrayList<String> list = current_system.search("Mannheim");
        
        assertEquals("68159", list.get(0).split(";")[0]);
        assertEquals("68167", list.get(4).split(";")[0]);

    }

    @Test
    public void distance(){

        current_system.set_current_user_zip("68161");
        
        assertEquals("88.46 km", current_system.distance("60306")); // Frankfurt 
        assertEquals("581.109 km", current_system.distance("20095")); // Hamburg
        assertEquals("603.608 km", current_system.distance("10115")); // Berlin

    }

    @Test
    public void travel_time(){

        current_system.set_current_user_zip("68161");
        current_system.set_current_user_car_avg_kmh(100);
        current_system.set_current_user_bike_avg_kmh(20);
        
        assertEquals("0.885 h", current_system.travel_time("60306")[0]); // Frankfurt mit Auto
        assertEquals("4.423 h", current_system.travel_time("60306")[1]); // Frankfurt mit Fahrrad

        assertEquals("6.036 h", current_system.travel_time("10115")[0]); // Berlin mit Auto
        assertEquals("30.18 h", current_system.travel_time("10115")[1]); // Berlin mit Fahrrad
    }

    @Test
    public void calc_l_consumption(){

        current_system.set_current_user_zip("68161");
        current_system.set_current_user_car_avg_kmh(100);
        current_system.set_current_user_car_l_100km(10);
        
        assertEquals("8.846 l", current_system.calc_l_consumption("60306")); // Kraftstoffverbrauch nach Frankfurt
        assertEquals("58.111 l", current_system.calc_l_consumption("20095")); // Kraftstoffverbrauch nach Hamburg
        assertEquals("60.361 l", current_system.calc_l_consumption("10115")); // Kraftstoffverbrauch nach Berlin
    }

    /*@Test
    public void random_destinations(){

        current_system.set_current_user_zip("68161");
     
        assertEquals(3, current_system.random_destinations_car().size()); // random_destinations_car gibt genau 3 destinations zurück
        assertEquals(3, current_system.random_destinations_bike().size()); // random destinations_bike gibt genau 3 destinations zurück

        // random_destinations_car gibt nur destinations mit mindestens 150 km Entfernung zurück
        assertEquals(true, Double.parseDouble(current_system.distance(current_system.random_destinations_car().get(0).split(";")[0]).replace(" km", "")) > 150);
        // random_destinations_bike gibt nur destinations mit maximal 100 km Entfernung zurück
        assertEquals(true, Double.parseDouble(current_system.distance(current_system.random_destinations_bike().get(0).split(";")[0]).replace(" km", "")) < 100);
    }*/

    @Test
    public void encoding(){

        String test_password = "123Esel";

        assertEquals("MTIzRXNlbA==",current_system.encoding(test_password));

    }

    @Test
    public void decoding(){

        String test_password = "MTIzRXNlbA==";

        assertEquals("123Esel",current_system.decoding(test_password));

    }

    @Test
    public void get_all_user() {

        assertEquals(2, current_system.get_all_user().size());
        assertEquals(true, current_system.get_all_user().get(0).getPassword().equals("MTQwMURhbmllbA=="));
        assertEquals(true, current_system.get_all_user().get(1).getPassword().equals("MTIzRXNlbA=="));
    }

    @Test
    public void sign_in_user() {

        assertEquals(true, current_system.sign_in_user("Daniel", "1401Daniel"));
        assertEquals("Daniel",current_system.getDetails()[0]);

    }

    @Test
    public void sign_out_user() {

        current_system.sign_in_user("Daniel", "1401Daniel");
        current_system.sign_out_user();
        assertEquals("",current_system.getDetails()[0]);

    }

}