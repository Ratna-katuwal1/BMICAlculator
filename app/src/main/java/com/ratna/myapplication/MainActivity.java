package com.ratna.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView weightValueText, ageValueText, increaseWeightButton, decreaseWeightButton, increaseAgeButton, decreaseAgeButton, calculateButton, displayHeight;
    SeekBar heightSeekBar;
    int heightValue, weightValue, ageValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        weightValueText = findViewById(R.id.weightText);
        ageValueText = findViewById(R.id.ageText);
        increaseWeightButton = findViewById(R.id.plusWeight);
        decreaseWeightButton = findViewById(R.id.minusWeight);
        increaseAgeButton = findViewById(R.id.plusAge);
        decreaseAgeButton = findViewById(R.id.minusAge);
        calculateButton = findViewById(R.id.calculateBtn);
        displayHeight = findViewById(R.id.displayHeight);
        heightSeekBar = findViewById(R.id.seekBar);

        heightValue = Integer.parseInt(displayHeight.getText().toString());
        weightValue = Integer.parseInt(weightValueText.getText().toString());
        ageValue = Integer.parseInt(ageValueText.getText().toString());

        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String height = String.valueOf(progress);
                displayHeight.setText(height);
                heightValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        increaseWeightButton.setOnClickListener(view -> {
            weightValue++;
            weightValueText.setText(String.valueOf(weightValue + ""));
        });

        decreaseWeightButton.setOnClickListener(view -> {
            weightValue--;
            weightValueText.setText(String.valueOf(weightValue + ""));
        });

        increaseAgeButton.setOnClickListener(view -> {
            ageValue++;
            ageValueText.setText(String.valueOf(ageValue + ""));
        });

        decreaseAgeButton.setOnClickListener(view -> {
            ageValue--;
            ageValueText.setText(String.valueOf(ageValue + ""));
        });

        calculateButton.setOnClickListener(view -> {
            int weightValue = Integer.parseInt(weightValueText.getText().toString());
            int heightValue = Integer.parseInt(displayHeight.getText().toString());

            double valueBmi = calculateBmi(weightValue, heightValue);
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("bmi", valueBmi);
            intent.putExtra("weight", weightValue);
            startActivity(intent);
        });

    }

    private double calculateBmi(int weight, int height) {
        double heightInMeters = height / 100.0;
        double bmi = weight / (heightInMeters * heightInMeters);
        return Math.round(bmi * 100.0) / 100.0;
    }
}