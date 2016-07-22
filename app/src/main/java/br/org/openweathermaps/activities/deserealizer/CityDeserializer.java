package br.org.openweathermaps.activities.deserealizer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.org.openweathermaps.models.City;


/**
 * Created by Rafael on 21/07/2016.
 */
public class CityDeserializer implements JsonDeserializer<List<City>> {

    private static final String LIST_TAG = "list";
    private static final String NAME_TAG = "name";
    private static final String MAIN_TAG = "main";
    private static final String TEMP_MAX_TAG = "temp_max";
    private static final String TEMP_MIN_TAG = "temp_min";
    private static final String WEATHER_TAG = "weather";
    private static final String DESCRIPTION_TAG = "description";

    @Override
    public List<City> deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        List<City> result = new ArrayList<>();

        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonArray cities = jsonObject.get(LIST_TAG).getAsJsonArray();
        for (JsonElement cityJson : cities) {
            JsonObject cityJsonObject = cityJson.getAsJsonObject();
            City city = new City();
            city.setName(cityJsonObject.getAsJsonPrimitive(NAME_TAG).getAsString());

            JsonObject mainObject = cityJsonObject.getAsJsonObject(MAIN_TAG);
            city.setMaxTemp(mainObject.getAsJsonPrimitive(TEMP_MAX_TAG).getAsFloat());
            city.setMinTemp(mainObject.getAsJsonPrimitive(TEMP_MIN_TAG).getAsFloat());

            final JsonArray weathers = cityJsonObject.get(WEATHER_TAG).getAsJsonArray();
            final JsonElement weather = weathers.get(0);
            if (weather != null) {
                city.setDescription(weather.getAsJsonObject().getAsJsonPrimitive(DESCRIPTION_TAG).getAsString());
            }
            result.add(city);
        }
        return result;
    }
}