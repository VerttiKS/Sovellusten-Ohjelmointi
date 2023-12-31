package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.drawable.Drawable;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void weatherAPI(View view){

        //Get API key (this won't be in the git)
        String KEY = Keys.API_KEY;

        //Set city
        EditText cityEdit = findViewById(R.id.editCityName);
        String city = cityEdit.getText().toString();

        if(city == null || city == "")
        {
            city = "Tampere";
        }

        TextView cityName = findViewById(R.id.textCity);
        cityName.setText("City: " + city);

        //Get the URL ready and send it for parsing
        String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric" + KEY;

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

            //Set temperature
            double temperature = weatherJSON.getJSONObject("main").getDouble("temp");

            TextView temperatureText = findViewById(R.id.textTemp);
            temperatureText.setText("Temp: " + temperature + "°C");

            //Set wind
            double wind = weatherJSON.getJSONObject("wind").getDouble("speed");

            TextView windText = findViewById(R.id.textWind);
            windText.setText("Wind: " + wind + "m/s");

            //Set image
            String imageURLData = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("icon");
            String imageURL = "https://openweathermap.org/img/wn/" + imageURLData + "@2x.png";

            ImageView image = findViewById(R.id.imageW);
            Picasso.get().load(imageURL).resize(300,300).into(image);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}