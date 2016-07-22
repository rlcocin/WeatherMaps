package br.org.openweathermaps.webservices;

import java.util.List;

import br.org.openweathermaps.models.City;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface WeatherAPI {

    @GET("/data/2.5/find?&cnt=15")
    Call<List<City>> findWeather(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String appId);

}
