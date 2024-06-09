package de.hs_mannheim.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

}
