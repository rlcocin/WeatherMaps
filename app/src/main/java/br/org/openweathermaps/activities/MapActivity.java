package br.org.openweathermaps.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import br.org.openweathermaps.R;
import br.org.openweathermaps.activities.deserealizer.CityDeserializer;
import br.org.openweathermaps.manager.MapManager;
import br.org.openweathermaps.models.City;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import br.org.openweathermaps.util.Util;
import br.org.openweathermaps.webservices.WeatherAPI;

public class MapActivity extends WeatherMapsActivity implements GoogleMap.OnMapClickListener, Toolbar.OnMenuItemClickListener, Callback<List<City>> {

    public static final String TAG_CITIES = "CITIES";

    private ProgressDialog mProgressDialog;

    private GoogleMap mMap;

    private Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setupToolbar(false);
        this.mToolbar.setOnMenuItemClickListener(this);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (mMarker == null) {
            mMarker = mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
        } else {
            mMarker.setPosition(latLng);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                if (mMarker != null) {
                    showProgressDialog();
                    MapManager.requestWeather(mMarker.getPosition(), this);
                } else {
                    Toast.makeText(this, this.getString(R.string.select_position), Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
        return false;
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.setOnMapClickListener(this);
    }


    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(this.getString(R.string.loading));
        mProgressDialog.show();
    }

    private void dismissProgressDialog() {
        mProgressDialog.hide();
    }

    @Override
    public void onResponse(Call<List<City>> call, Response<List<City>> response) {
        List<City> cities = response.body();
        Intent intent = new Intent(this, ListCitiesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG_CITIES, (java.io.Serializable) cities);
        intent.putExtras(bundle);
        dismissProgressDialog();
        this.startActivity(intent);

    }

    @Override
    public void onFailure(Call<List<City>> call, Throwable t) {
        dismissProgressDialog();
        Toast.makeText(this, this.getString(R.string.conection_error), Toast.LENGTH_LONG).show();
    }


}
