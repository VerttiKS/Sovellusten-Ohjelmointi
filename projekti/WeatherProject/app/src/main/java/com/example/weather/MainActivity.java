package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startWeather(View view) {
        //Choose the WeatherActivity as our location
        Intent intent = new Intent(this, WeatherActivity.class);

        //Create bundle
        Bundle extras = new Bundle();

        //Bundle the location input text
        EditText inputSettingText = findViewById(R.id.editCityName);
        String inputLocation = inputSettingText.getText().toString();
        extras.putString("INPUT_LOCATION", inputLocation);

        //Bundle the GPS switch status
        Switch switchGPS = findViewById(R.id.switchGPS);
        boolean gpsActivated = switchGPS.isChecked();
        extras.putBoolean("GPS_ACTIVATED", gpsActivated);

        //Bundle the Fahrenheit switch status
        Switch switchFah = findViewById(R.id.switchFahrenheit);
        boolean fahActivated = switchFah.isChecked();
        extras.putBoolean("FAHRENHEIT_ACTIVATED", fahActivated);

        //Prepare to send "extras" bundles to WeatherActivity
        intent.putExtras(extras);

        // Go to WeatherActivity and send extras to it
        startActivity(intent);
    }

}