package com.test.android.nikola.sunshine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.android.nikola.sunshine.R;

public class WeatherInfo extends Activity {

    private TextView selectedDay;
    private TextView forecast;
    private TextView minTemp;
    private TextView maxTemp;
    private ImageView weatherImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);

        String intentMsg = getIntent().getStringExtra(MainActivity.MSG_TAG).toString();

        String[] weatherInfo = intentMsg.split(" ");
        String[] temps = weatherInfo[4].split("/");

        selectedDay = (TextView) findViewById(R.id.weather_info_title_text);
        forecast = (TextView) findViewById(R.id.forecast_textView);
        minTemp = (TextView) findViewById(R.id.minTemp_textView);
        maxTemp = (TextView) findViewById(R.id.maxTemp_textView);
        weatherImage = (ImageView) findViewById(R.id.imageView);

        selectedDay.setText(weatherInfo[0]);
        forecast.setText(weatherInfo[2]);
        minTemp.setText(temps[0]);
        maxTemp.setText(temps[1]);


        if(weatherInfo[2].equals("Sunny")) {
            weatherImage.setImageResource(R.drawable.weather_sunny);
        }else if(weatherInfo[2].equals("Cloudy")) {
            weatherImage.setImageResource(R.drawable.weather_cloudy);
        }else if(weatherInfo[2].equals("Rainy")) {
            weatherImage.setImageResource(R.drawable.weather_rainy);
        }else if(weatherInfo[2].equals("Stormy")) {
            weatherImage.setImageResource(R.drawable.weather_stormy);
        }else {
            weatherImage.setImageResource(R.drawable.weather_icon_launcher);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weather_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_about) {
            ShowAboutDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    private void ShowAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.title_about_dialog);
        builder.setMessage(R.string.content_about_dialog);
        builder.setNeutralButton("Got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
