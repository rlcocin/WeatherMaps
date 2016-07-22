package br.org.openweathermaps.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import br.org.openweathermaps.R;
import br.org.openweathermaps.models.City;
import br.org.openweathermaps.util.Util;

public class DetailsCityActivity extends WeatherMapsActivity {

    private City mCity;

    private TextView mCityName;

    private TextView mMaxTemp;

    private TextView mMinTemp;

    private TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_city);
        this.setupToolbar(true);

        Bundle bundle = this.getIntent().getExtras();
        mCity = (City) bundle.getSerializable(ListCitiesActivity.TAG_CITY);

        mCityName = (TextView) findViewById(R.id.city_name);
        mMaxTemp = (TextView) findViewById(R.id.max_temp);
        mMinTemp = (TextView) findViewById(R.id.min_temp);
        mDescription = (TextView) findViewById(R.id.description);

        mCityName.setText(String.format(this.getString(R.string.city), mCity.getName()));
        mDescription.setText(String.format(this.getString(R.string.description), mCity.getDescription()));
        mMaxTemp.setText(String.format(this.getString(R.string.temp_max), Util.convertFahrenheitToCelsius(mCity.getMaxTemp())));
        mMinTemp.setText(String.format(this.getString(R.string.temp_min), Util.convertFahrenheitToCelsius(mCity.getMinTemp())));
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
