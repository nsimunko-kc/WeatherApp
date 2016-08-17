package com.test.android.nikola.sunshine;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] values) {
        super(context, R.layout.my_row_layout_2, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View myView = inflater.inflate(R.layout.my_row_layout_2, parent, false);

        String dailyForecast = getItem(position);

        TextView myTextView = (TextView) myView.findViewById(R.id.textView_1);
        myTextView.setText(dailyForecast);

        String regex[] = dailyForecast.split(" ");
        String weather = regex[2].toLowerCase();

        ImageView myImageView = (ImageView) myView.findViewById(R.id.imageView_1);

        if(weather.equals("sunny")) {
            myImageView.setImageResource(R.drawable.weather_sunny);
        }else if(weather.equals("cloudy")) {
            myImageView.setImageResource(R.drawable.weather_cloudy);
        }else if(weather.equals("rainy")) {
            myImageView.setImageResource(R.drawable.weather_rainy);
        }else if(weather.equals("stormy")) {
            myImageView.setImageResource(R.drawable.weather_stormy);
        }else {
            myImageView.setImageResource(R.drawable.weather_icon_launcher);
        }


        return myView;
    }
}
