package com.example.common_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openTuni(View view) {

        String website = "https://www.tuni.fi/fi";
        Uri webpage = Uri.parse(website);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        try{
            startActivity(intent);

        } catch(Exception e) {

        }

    }
}