package br.org.openweathermaps.manager;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import br.org.openweathermaps.activities.MapActivity;
import br.org.openweathermaps.activities.deserealizer.CityDeserializer;
import br.org.openweathermaps.models.City;
import br.org.openweathermaps.util.Util;
import br.org.openweathermaps.webservices.WeatherAPI;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MapManager {


    public static void requestWeather(LatLng latLng, MapActivity view) {
        Type listCityType = new TypeToken<List<City>>() {
        }.getType();
        CityDeserializer cityDeserializer = new CityDeserializer();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(listCityType, cityDeserializer).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Util.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
        Call<List<City>> call = weatherAPI.findWeather(String.valueOf(latLng.latitude), String.valueOf(latLng.longitude), Util.APP_ID);
        call.enqueue(view);
    }
}
