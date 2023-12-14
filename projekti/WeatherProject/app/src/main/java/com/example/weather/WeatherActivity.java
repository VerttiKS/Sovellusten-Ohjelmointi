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

    private LocationManager locationManager;

    //Setting variables
    private String cityName = "Tampere";
    private boolean useGPS = false;
    private boolean useFahrenheit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Get intent extras
        Bundle extras = getIntent().getExtras();

        // This happens, if we flip the screen, if it doesn't happen, we get "extras" Bundle from MainActivity
        if(savedInstanceState != null)
        {
            cityName = savedInstanceState.getString("CITY_NAME", "Tampere");
            useGPS = savedInstanceState.getBoolean("USE_GPS", false);
            useFahrenheit = savedInstanceState.getBoolean("USE_FAHRENHEIT", false);

        } else if (extras != null) {
            cityName = extras.getString("INPUT_LOCATION", "Tampere");
            useGPS = extras.getBoolean("GPS_ACTIVATED", false);
            useFahrenheit = extras.getBoolean("FAHRENHEIT_ACTIVATED", false);
        }

        //Here we decide what to do based on the GPS
        if(useGPS)
        {
            startGPS();
        }
        else
        {
            weatherAPI(false);
        }
    }

    //If we flip the screen, the info won't go away
    @Override
    protected void onSaveInstanceState (Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putString("CITY_NAME", cityName);
        bundle.putBoolean("USE_GPS", useGPS);
        bundle.putBoolean("USE_FAHRENHEIT", useFahrenheit);
    }

    public void weatherAPI(boolean fromGPS){

        //Get API key (this won't be in the git)
        String KEY = Keys.API_KEY;

        //Set city
        String city = cityName;

        //Default city name to Tampere incase of problems
        if(city == null || city == "")
        {
            city = "Tampere";
        }

        //Basically, if we don't get our string from the GPS, we need to add this to tell the API it's a city name
        if(!fromGPS)
        {
            city = "q=" + city;
        }

        //Here we get the languageKey and set the unit to metric
        String languageKey = getString(R.string.languageCode);
        String units = "metric";

        //If we're using fahrenheit, change the unit to imperials
        if(useFahrenheit)
        {
            units = "imperial";
        }

        //Get the URL ready and send it for parsing
        String API_URL = "https://api.openweathermap.org/data/2.5/weather?" + city + KEY + "&units=" + units + "&lang=" + languageKey;

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

            //Setup unit names
            String temp_unit = "°C";
            String wind_unit = " m/s";

            //If we're using fahrenheit, change the unit to imperials
            if(useFahrenheit)
            {
                temp_unit = "°F";
                wind_unit = " mph";
            }

            //Get and set temperature
            double temperature = weatherJSON.getJSONObject("main").getDouble("temp");

            TextView temperatureText = findViewById(R.id.textTemp);
            temperatureText.setText(temperature + temp_unit);

            //Get and set wind
            double wind = weatherJSON.getJSONObject("wind").getDouble("speed");

            TextView windText = findViewById(R.id.textWind);
            windText.setText(wind + wind_unit);

            //Get and set name
            String locationName = weatherJSON.getString("name");

            TextView locationText = findViewById(R.id.textCity);
            locationText.setText(locationName);

            //Get and set description
            String weatherDescription = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("description");

            TextView descriptionText = findViewById(R.id.textDescription);
            descriptionText.setText(weatherDescription);

            //Get and set image (picasso helps)
            String imageURLData = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("icon");
            String imageURL = "https://openweathermap.org/img/wn/" + imageURLData + "@2x.png";

            ImageView image = findViewById(R.id.imageW);
            Picasso.get().load(imageURL).resize(320,320).into(image);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void startGPS() {
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

        //Here we set the first location as the lastKnown location (we use live location afterwards)
        if(currentLocation != null)
        {
            double latitude = currentLocation.getLatitude();
            double longitude = currentLocation.getLongitude();

            //We set the location to the cityName. It'll be used in the url of the weatherAPI
            String gpsLocation = "lat=" + latitude + "&lon=" + longitude;
            cityName = gpsLocation;

            weatherAPI(true);
        }

        //Here we move on to the live location
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                //We set the location to the cityName. It'll be used in the url of the weatherAPI
                String gpsLocation = "lat=" + latitude + "&lon=" + longitude;
                cityName = gpsLocation;

                weatherAPI(true);
            }
        });
    }
}