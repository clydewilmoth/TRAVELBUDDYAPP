package de.hs_mannheim.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class SystemTest {
    
    @Test
    public void weather_forecast(){

        System current_system = new System("35a75437476f12302f72e55d368485db");

        assertNotEquals("Es ist ein Fehler aufgetreten!",current_system.weather_forecast("68161"));
    }

    @Test
    public void current_weather(){

        System current_system = new System("35a75437476f12302f72e55d368485db");

        current_system.set_current_user_zip("68161");

        assertNotEquals("Es ist ein Fehler aufgetreten!",current_system.current_weather());
    }

    @Test
    public void search(){

        System current_system = new System("35a75437476f12302f72e55d368485db");

        ArrayList<String> list = current_system.search("Mannheim");
        
        assertEquals("68159", list.get(0).split(";")[0]);
        assertEquals("68167", list.get(4).split(";")[0]);

    }

    @Test
    public void distance(){

        System current_system = new System("35a75437476f12302f72e55d368485db");

        current_system.set_current_user_zip("68161");
        
        assertEquals("88.46 km", current_system.distance("60306")); // Frankfurt 
        assertEquals("581.109 km", current_system.distance("20095")); // Hamburg
        assertEquals("603.608 km", current_system.distance("10115")); // Berlin

    }

    @Test
    public void travel_time(){

        System current_system = new System("35a75437476f12302f72e55d368485db");

        current_system.set_current_user_zip("68161");
        current_system.set_current_user_car_avg_kmh(100);
        current_system.set_current_user_bike_avg_kmh(20);
        
        assertEquals("0.885 h", current_system.travel_time("60306")[0]); // Frankfurt mit Auto
        assertEquals("4.423 h", current_system.travel_time("60306")[1]); // Frankfurt mit Fahrrad

        assertEquals("6.036 h", current_system.travel_time("10115")[0]); // Berlin mit Auto
        assertEquals("30.18 h", current_system.travel_time("10115")[1]); // Berlin mit Fahrrad
    }

}
