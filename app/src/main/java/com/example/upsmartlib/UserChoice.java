package com.example.upsmartlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserChoice extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private TextView LendBtn;
    private TextView SeeYourBooksBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);
        LendBtn = (TextView) findViewById(R.id.LendBtn);
        LendBtn.setOnClickListener(this);
        SeeYourBooksBtn= (TextView) findViewById(R.id.SeeYourBooksBtn);
        SeeYourBooksBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.LendBtn:
                startActivity(new Intent(getApplicationContext(),SeeBooksAdmin.class));
                break;
            case R.id.SeeYourBooksBtn:
                startActivity(new Intent(getApplicationContext(), SeeBooksAdmin.class));
        }

    }
}