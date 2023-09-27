package com.example.valuuttamuunnin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Setup all extras strings
    private String inputString = "EUR";
    private boolean switchBool = false;
    private String outputString = "USD";
    private float conversionRate = 1.06f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get intent extras
        Bundle extras = getIntent().getExtras();

        // If extras is not null, get values
        if(extras != null)
        {
            inputString = extras.getString("INPUT_TEXT");
            switchBool = extras.getBoolean("SWITCH_ACTIVATED", false);
            outputString = extras.getString("OUTPUT_TEXT");
            conversionRate = extras.getFloat("CONVERSION_RATE", 1.06f);

            TextView inputText = findViewById(R.id.inputCurrencyText);
            TextView outputText = findViewById(R.id.outputCurrencyText);

            // Because you can't set default values for strings, we do it here
            if(inputString == null)
            {
                inputString = "EUR";
            }

            if(outputString == null)
            {
                outputString = "USD";
            }

            // Check if the switch is enabled and swap the strings, if it has
            if(switchBool == true)
            {
                inputText.setText(outputString);
                outputText.setText(inputString);
            }
            else
            {
                inputText.setText(inputString);
                outputText.setText(outputString);
            }
        }



    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class); // Store the activity
        startActivity(intent); // start the activity
    }

    public void convertCurrency(View view) {

        // Get the number input and output EditTexts
        EditText inputNumText = findViewById(R.id.inputCurrencyNum);
        EditText outputNumText = findViewById(R.id.outputCurrencyNum);

        float inputNum = Float.parseFloat(inputNumText.getText().toString());

        // Change output to the desired one. If switch is true, we divide.
        if(switchBool == true)
        {
            outputNumText.setText(String.valueOf(inputNum / conversionRate));
        }
        else
        {
            outputNumText.setText(String.valueOf(inputNum * conversionRate));
        }
    }
}