package com.example.valuuttamuunnin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);
    }

    public void closeSettings(View view) {
        // Back to the main
        Intent intent = new Intent(this, MainActivity.class);

        // Create bundle
        Bundle extras = new Bundle();

        // Bundle the input text
        EditText inputSettingText = findViewById(R.id.inputSettingText);
        String inputSettingString = inputSettingText.getText().toString();
        extras.putString("INPUT_TEXT", inputSettingString);

        // Bundle the switch status
        Switch switchCurrency = findViewById(R.id.switchCurrency);
        boolean switchActivated = switchCurrency.isChecked();
        extras.putBoolean("SWITCH_ACTIVATED", switchActivated);

        // Send back the output text
        EditText outputSettingText= findViewById(R.id.outputSettingText);
        String outputSettingString = outputSettingText.getText().toString();
        extras.putString("OUTPUT_TEXT", outputSettingString);

        // Bundle the conversion rate
        EditText conversionNum = findViewById(R.id.conversionNum);
        float conversionRate = Float.parseFloat(conversionNum.getText().toString());
        extras.putFloat("CONVERSION_RATE", conversionRate);

        // Prepare to send extras to main
        intent.putExtras(extras);

        // Go to main and send extras to it
        startActivity(intent);
    }
}