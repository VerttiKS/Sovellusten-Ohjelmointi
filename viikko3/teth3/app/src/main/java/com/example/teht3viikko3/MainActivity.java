package com.example.teht3viikko3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String message = "Hello world!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            message = savedInstanceState.getString("MESSAGE_VALUE", "Hello world!");
        }

        TextView HelloWorld = findViewById(R.id.textView);
        HelloWorld.setText(message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("START", "Hello onStart!");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RESUME", "Hello onResume!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("PAUSE", "Hello onPause!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("STOP", "Hello onStop!");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DESTROY", "Hello onDestroy!");
    }


    @Override
    protected void onSaveInstanceState (Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putString("MESSAGE_VALUE", message);
    }

    public void changeText(View view) {
        EditText myEditText = findViewById(R.id.editTextText);
        String newMessage = myEditText.getText().toString();
        message = newMessage;

        TextView HelloWorld = findViewById(R.id.textView);
        HelloWorld.setText(message);
    }
}