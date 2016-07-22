package br.org.openweathermaps.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import br.org.openweathermaps.R;
import br.org.openweathermaps.activities.adapters.CityAdapter;
import br.org.openweathermaps.models.City;

public class ListCitiesActivity extends WeatherMapsActivity implements AdapterView.OnItemClickListener {

    public static final String TAG_CITY = "CITY";

    private ListView mCitiesLv;

    private List<City> mCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cities_acitvity);
        this.setupToolbar(true);

        Bundle bundle = this.getIntent().getExtras();
        mCities = (List<City>) bundle.getSerializable(MapActivity.TAG_CITIES);
        mCitiesLv = (ListView) findViewById(R.id.cities_list);
        CityAdapter cityAdapter = new CityAdapter(mCities, this);
        mCitiesLv.setAdapter(cityAdapter);
        mCitiesLv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailsCityActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG_CITY, mCities.get(position));
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return false;
    }
}
