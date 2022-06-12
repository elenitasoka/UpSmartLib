package com.example.upsmartlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    private TextView button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button3 = (TextView) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4= (TextView) findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button4:
                startActivity(new Intent(getApplicationContext(),UserChoice.class));
                break;
            case R.id.button3:
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
        }

    }
}