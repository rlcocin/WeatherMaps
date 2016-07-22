package br.org.openweathermaps.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.org.openweathermaps.R;
import br.org.openweathermaps.models.City;


/**
 * Created by Rafael on 21/07/2016.
 */
public class CityAdapter extends BaseAdapter {


    private List<City> mCities;

    private Context mContext;

    public CityAdapter(List<City> cities, Context context) {
        this.mCities = cities;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return mCities.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityHolder mViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.city_list_item, parent, false);
            mViewHolder = new CityHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (CityHolder) convertView.getTag();
        }

        City city = mCities.get(position);

        mViewHolder.getCityName().setText(city.getName());
        return convertView;
    }

    private class CityHolder {
        private TextView cityName;

        private CityHolder(View view) {
            this.cityName = (TextView) view.findViewById(R.id.city_name);
        }

        public TextView getCityName() {
            return cityName;
        }


    }
}
