package com.example.textviewbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonSayHello(View view) {
        TextView helloText = findViewById(R.id.helloTextView);
        helloText.setText(R.string.hello_world);
    }
}