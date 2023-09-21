package com.example.lengthconvertor;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText valueEditText;
    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueEditText = findViewById(R.id.valueEditText);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.length_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLength();
            }
        });
    }

    private void convertLength() {
        String valueStr = valueEditText.getText().toString();
        if (valueStr.isEmpty()) {
            resultTextView.setText("Please enter a value.");
            return;
        }

        double value = Double.parseDouble(valueStr);
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        double convertedValue = convert(value, fromUnit, toUnit);
        displayResult(convertedValue, toUnit);
    }

    private double convert(double value, String fromUnit, String toUnit) {
        // Define conversion factors as needed for different units
        double meterToInch = 39.3701;
        double meterToFeet = 3.28084;
        double inchToMeter = 0.0254;
        double feetToMeter = 0.3048;

        if (fromUnit.equals("Meter") && toUnit.equals("Inch")) {
            return value * meterToInch;
        } else if (fromUnit.equals("Meter") && toUnit.equals("Feet")) {
            return value * meterToFeet;
        } else if (fromUnit.equals("Inch") && toUnit.equals("Meter")) {
            return value * inchToMeter;
        } else if (fromUnit.equals("Feet") && toUnit.equals("Meter")) {
            return value * feetToMeter;
        } else {
            return value; // Default to no conversion
        }
    }

    private void displayResult(double convertedValue, String toUnit) {
        DecimalFormat df = new DecimalFormat("#.##");
        String result = df.format(convertedValue) + " " + toUnit;
        resultTextView.setText(result);
    }
}
