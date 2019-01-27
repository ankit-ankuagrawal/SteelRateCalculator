package com.sample.app.steelratecal;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnTouchListener {

    private static final float FOURTEEN_RATE_OF_INTEREST = 14.0f;
    private static final float EIGHTEEN_RATE_OF_INTEREST = 18.0f;

    private EditText etBasicPrice, etRateDifference, etTransport;
    private TextView tv14, tv14Total;
    private TextView tv18, tv18Total;
    private double basicPrice, rateDifference, transport;
    private double interest14;
    private double interest18;
    private double total14;
    private double total18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBasicPrice = (TextInputEditText) findViewById(R.id.etBasicPrice);
        etRateDifference = (TextInputEditText) findViewById(R.id.etRateDifference);
        etTransport = (TextInputEditText) findViewById(R.id.etTransport);

        etBasicPrice.addTextChangedListener(this);
        etRateDifference.addTextChangedListener(this);
        etTransport.addTextChangedListener(this);

        etBasicPrice.setOnTouchListener(this);
        etRateDifference.setOnTouchListener(this);
        etTransport.setOnTouchListener(this);

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
        try {

            basicPrice = Double.valueOf("0" + etBasicPrice.getText().toString());
            rateDifference = Double.valueOf("0" + etRateDifference.getText().toString());
            transport = Double.valueOf("0" + etTransport.getText().toString());

            interest14 = calInterest(basicPrice + rateDifference, FOURTEEN_RATE_OF_INTEREST);
            interest18 = calInterest(basicPrice + rateDifference, EIGHTEEN_RATE_OF_INTEREST);

            total14 = calTotal(basicPrice + rateDifference + transport, interest14);
            total18 = calTotal(basicPrice + rateDifference + transport, interest18);

            tv14.setText(formatUpto2Decimal(interest14));
            tv14Total.setText(formatUpto2Decimal(total14));
            tv18.setText(formatUpto2Decimal(interest18));
            tv18Total.setText(formatUpto2Decimal(total18));

        } catch (NumberFormatException e) {

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private double calInterest(double basicPrice, float rateOfInterest) {
        return (basicPrice * rateOfInterest) / 100;
    }

    private String formatUpto2Decimal(double price) {
        return new DecimalFormat("##.##").format(price);
    }

    private double calTotal(double basicPrice, double calculatedInterest) {
        return basicPrice + calculatedInterest;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getRawX() >= (v.getRight() - ((EditText) v).getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                // your action here
                ((TextInputEditText) v).setText("");
                return false;
            }

        }
        return false;
    }
}
