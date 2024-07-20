package com.ratna.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    TextView bmiResult, bmiMessage, bmiType;
    Button reCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        bmiResult = findViewById(R.id.bmiValues);
        bmiMessage = findViewById(R.id.bmiMessage);
        bmiType = findViewById(R.id.bmiType);
        reCalculate = findViewById(R.id.btnReCalculateAgain);

        Intent intent = getIntent();
        double bmi = intent.getDoubleExtra("bmi", 0.0);
        int weight = intent.getIntExtra("weight", 0);

        String gender = intent.getStringExtra("gender");

        bmiResult.setText(bmi + "");
        DisplayResult(bmi);
        reCalculate.setOnClickListener(view -> {
            finish();
        });
    }

    private void DisplayResult(double bmi) {
        String type;
        String message;
        int color;

        if (bmi < 18.5){
            type = "UnderWeight";
            message = "Your are underweight, have to take a balanced diet";
            color = getResources().getColor(R.color.light_blue);
        } else if (bmi >= 18.5 && bmi <= 24.9){
            type = "Normal";
            message = "Your are normal, keep it up";
            color = getResources().getColor(R.color.green);
        } else if (bmi >= 25 && bmi <= 29.9) {
            type ="OverWeight";
            message = "You are Overweight, need to do exercise";
            color = getResources().getColor(R.color.yellow);
        } else {
            type = "Obese";
            message ="You need to consult a Doctor, You are extremely Unhealthy";
            color = getResources().getColor(R.color.red);
        }
        bmiType.setText(type);
        bmiMessage.setText(message);
        bmiResult.setTextColor(color);
    }
}