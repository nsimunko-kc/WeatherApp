package com.test.android.nikola.sunshine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    public static String MSG_TAG = "WEATHER_INFO";

    private ListAdapter mListAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] weekForecast = {
                "Today - Cloudy - 18°/27°",
                "Thursday - Cloudy - 18°/25°",
                "Friday - Rainy - 15°/25°",
                "Saturday - Stormy - 17°/28°",
                "Sunday - Rainy - 17°/27°",
                "Monday - Sunny - 20°/31°",
                "Tuesday - Sunny - 22°/35°"
        };

        mListAdapter = new CustomAdapter(this, weekForecast);

        mListView = (ListView) findViewById(R.id.forecast_listView);
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Create a new intent, pass weather info to the other activity
                // WeatherInfo activity shows detailed weather information
                Intent intent = new Intent(MainActivity.this, WeatherInfo.class);
                intent.putExtra(MSG_TAG, String.valueOf(mListView.getItemAtPosition(position)));

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.action_about) {
            ShowAboutDialog();
        }
        return super.onOptionsItemSelected(item);
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
