package br.org.openweathermaps.models;

import java.io.Serializable;

/**
 * Created by Rafael on 21/07/2016.
 */
public class City implements Serializable {

    private String name;

    private float maxTemp;

    private float minTemp;

    private String description;

    public City(String name, float maxTemp, float minTemp, String description) {
        this.name = name;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.description = description;
    }


    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
