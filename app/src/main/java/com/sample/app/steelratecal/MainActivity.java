package com.sample.app.steelratecal;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private static final float FOURTEEN_RATE_OF_INTEREST = 14.0f;
    private static final float EIGHTEEN_RATE_OF_INTEREST = 18.0f;

    private EditText etBasicPrice;
    private TextView tv14, tv14Total;
    private TextView tv18, tv18Total;
    private double basicPrice;
    private double interest14;
    private double interest18;
    private double total14;
    private double total18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBasicPrice = (TextInputEditText) findViewById(R.id.etBasicPrice);
        etBasicPrice.addTextChangedListener(this);
        tv14 = (TextView) findViewById(R.id.tv14);
        tv14Total = (TextView) findViewById(R.id.tv14Total);
        tv18 = (TextView) findViewById(R.id.tv18);
        tv18Total = (TextView) findViewById(R.id.tv18Total);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        basicPrice = 0;
        if (!TextUtils.isEmpty(s)) {
            basicPrice = Double.valueOf(s.toString());
        }

        interest14 = calInterest(basicPrice, FOURTEEN_RATE_OF_INTEREST);
        interest18 = calInterest(basicPrice, EIGHTEEN_RATE_OF_INTEREST);
        total14 = calTotal(basicPrice, interest14);
        total18 = calTotal(basicPrice, interest18);
        tv14.setText(String.valueOf(interest14));
        tv14Total.setText(String.valueOf(total14));
        tv18.setText(String.valueOf(interest18));
        tv18Total.setText(String.valueOf(total18));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private double calInterest(double basicPrice, float rateOfInterest) {
        return (basicPrice * rateOfInterest) / 100;
    }

    private double calTotal(double basicPrice, double calculatedInterest) {
        return basicPrice + calculatedInterest;
    }
}
