package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    public void weatherAPI(View view){

        //Get API key (this won't be in the git)
        String KEY = Keys.API_KEY;

        //Set city
        //EditText cityEdit = findViewById(R.id.editCityName);
        //String city = cityEdit.getText().toString();
        String city = "Tampere";

        if(city == null || city == "")
        {
            city = "Tampere";
        }

        //Get localization
        String languageKey = getString(R.string.languageCode);
        String units = getString(R.string.units);

        TextView cityName = findViewById(R.id.textCity);
        cityName.setText(city);

        //Get the URL ready and send it for parsing
        String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=" + city + KEY + "&units=" + units + "&lang=" + languageKey;

        StringRequest request = new StringRequest(Request.Method.GET, API_URL, response -> {

            ParseJsonUpdateUI(response);
        }, error -> {
            Toast.makeText(this, "API ERROR", Toast.LENGTH_LONG).show();
        });

        Volley.newRequestQueue(this).add(request);
    }

    private void ParseJsonUpdateUI(String response)
    {
        try {
            JSONObject weatherJSON = new JSONObject(response);

            //Get units
            String temp_unit = getString(R.string.temp_unit);
            String wind_unit = getString(R.string.wind_unit);

            //Set temperature
            double temperature = weatherJSON.getJSONObject("main").getDouble("temp");

            TextView temperatureText = findViewById(R.id.textTemp);
            temperatureText.setText(temperature + temp_unit);

            //Set wind
            double wind = weatherJSON.getJSONObject("wind").getDouble("speed");

            TextView windText = findViewById(R.id.textWind);
            windText.setText(wind + wind_unit);

            //Set description
            String weatherDescription = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("description");

            TextView descriptionText = findViewById(R.id.textDescription);
            descriptionText.setText(weatherDescription);

            //Set image
            String imageURLData = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("icon");
            String imageURL = "https://openweathermap.org/img/wn/" + imageURLData + "@2x.png";

            ImageView image = findViewById(R.id.imageW);
            Picasso.get().load(imageURL).resize(320,320).into(image);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void startGPS(View view) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            return;
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                //TextView LatLongtextView = findViewById(R.id.coordinates);
                //LatLongtextView.setText("Lat: " + latitude + "\nLong: " + longitude);
            }
        });
    }
}