package de.hs_mannheim.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class SystemTest {

    System current_system = new System("35a75437476f12302f72e55d368485db");

    @Test
    public void weather_forecast() {

        current_system.sign_in_user("David", "123Esel");

        assertNotEquals("Es ist ein Fehler aufgetreten!", current_system.weather_forecast("68161")[0]);
    }

    @Test
    public void current_weather() {

        current_system.sign_in_user("David", "123Esel");

        assertNotEquals("Es ist ein Fehler aufgetreten!", current_system.current_weather());
    }

    @Test
    public void search() {

        ArrayList<String> list = current_system.search("Mannheim");

        assertEquals("68159", list.get(0).split(";")[0]);
        assertEquals("68167", list.get(4).split(";")[0]);

    }

    @Test
    public void distance() {

        current_system.sign_in_user("David", "123Esel");

        assertEquals("88.46 km", current_system.distance("60306")); // Frankfurt
        assertEquals("581.11 km", current_system.distance("20095")); // Hamburg
        assertEquals("603.61 km", current_system.distance("10115")); // Berlin

    }

    @Test
    public void travel_time() {

        current_system.sign_in_user("David", "123Esel");

        assertEquals("0.88 h", current_system.travel_time("60306")[0]); // Frankfurt mit Auto
        assertEquals("4.42 h", current_system.travel_time("60306")[1]); // Frankfurt mit Fahrrad

        assertEquals("6.04 h", current_system.travel_time("10115")[0]); // Berlin mit Auto
        assertEquals("30.18 h", current_system.travel_time("10115")[1]); // Berlin mit Fahrrad
    }

    @Test
    public void calc_l_consumption() {

        current_system.sign_in_user("David", "123Esel");

        assertEquals("8.85 l", current_system.calc_l_consumption("60306")); // Kraftstoffverbrauch nach Frankfurt
        assertEquals("58.11 l", current_system.calc_l_consumption("20095")); // Kraftstoffverbrauch nach Hamburg
        assertEquals("60.36 l", current_system.calc_l_consumption("10115")); // Kraftstoffverbrauch nach Berlin
    }

    @Test
    public void random_destinations() {

        current_system.sign_in_user("David", "123Esel");
        ArrayList<String> random_destination_car = current_system.random_destinations_car();
        ArrayList<String> random_destination_bike = current_system.random_destinations_bike();

        assertEquals(3, random_destination_car.size()); // random_destinations_car gibt genau 3 destinations zurück
        assertEquals(3, random_destination_bike.size()); // random destinations_bike gibt genau 3 destinations zurück

        // random_destinations_car gibt nur destinations mit mindestens 150 km
        // Entfernung zurück
        assertEquals(true, Double.parseDouble(
                current_system.distance(random_destination_car.get(0).split(";")[0]).replace(" km", "")) > 150);
        // random_destinations_bike gibt nur destinations mit maximal 100 km Entfernung
        // zurück
        assertEquals(true, Double.parseDouble(
                current_system.distance(random_destination_bike.get(0).split(";")[0]).replace(" km", "")) < 100);
    }

    @Test
    public void encoding() {

        String test_password = "123Esel";

        assertEquals("MTIzRXNlbA==", System.encoding(test_password));

    }

    @Test
    public void decoding() {

        String test_password = "MTIzRXNlbA==";

        assertEquals("123Esel", System.decoding(test_password));

    }

    @Test
    public void sign_in_user() {

        assertEquals(true, current_system.sign_in_user("Daniel", "1401Daniel"));
        assertEquals("Daniel", current_system.getDetails()[0]);

    }

    @Test
    public void sign_out_user() {

        assertEquals(true, current_system.sign_in_user("David", "123Esel"));
        assertEquals("David", current_system.getDetails()[0]);
        current_system.sign_out_user();
        assertEquals("", current_system.getDetails()[0]);

    }

    @Test
    public void sign_up_user() {
        // Username darf nicht doppelt vorkommen!
        assertEquals(false, current_system.sign_up_user("David", "123Esel", "123Esel", "Mannheim", "68161", "AMG", "10",
                "300", "20"));
        assertEquals(true, current_system.sign_up_user("Selim", "Penis69", "Penis69", "Mannheim", "68161", "AMG", "10",
                "300", "20"));
        // PLZ muss mit Stadt übereinstimmen
        assertEquals(false, current_system.sign_up_user("Lukas", "123Esel", "123Esel", "Mannheim", "11105", "AMG", "10",
                "300", "20"));
        assertEquals(true, current_system.sign_up_user("Lukas", "123Esel", "123Esel", "Mannheim", "68305", "AMG", "10",
                "300", "20"));

        assertEquals("Lukas", current_system.getDetails()[0]);
        current_system.sign_out_user();
        assertEquals("", current_system.getDetails()[0]);
        assertEquals(true, current_system.sign_in_user("Lukas", "123Esel"));
    }

    @Test
    public void change_user_details() {

        current_system.sign_in_user("David", "123Esel");
        current_system.change_user_details("Enes", "123Esel", "Mannheim", "68161", "", "", "", "");
        assertEquals("Enes", current_system.getDetails()[0]);
        current_system.change_user_details("David", "123Esel", "Mannheim", "68161", "AMG", "10", "100", "20");

    }

    @Test
    public void change_user_password() {

        current_system.sign_in_user("David", "123Esel");
        assertEquals(true, current_system.change_user_password("123Esel", "Pizza69", "Pizza69"));
        assertEquals(true, current_system.change_user_password("Pizza69", "123Esel", "123Esel"));
        assertEquals(true, current_system.change_user_details("Enes", "123Esel", "Mannheim", "68161", "", "", "", ""));
        current_system.change_user_details("David", "123Esel", "Mannheim", "68161", "AMG", "10", "100", "20");

        ;

    }
    /*
     * Tests auf Basis von user_data.csv:
     * Daniel;MTQwMURhbmllbA==;Mannheim;68305;BMW;1.5;50.4;40.2
     * David;MTIzRXNlbA==;Mannheim;68161;AMG;10.0;100.0;20.0
     */

}