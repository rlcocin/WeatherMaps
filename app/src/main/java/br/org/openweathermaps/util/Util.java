package br.org.openweathermaps.util;

/**
 * Created by Rafael on 21/07/2016.
 */
public class Util {

    public static final String APP_ID = "68d7a1fe4d4503fee1638b62328c1654";
    public static final String API_URL = "http://api.openweathermap.org";



    public static float convertFahrenheitToCelsius(float temp){
        return (temp - 273.15f);
    }
}
