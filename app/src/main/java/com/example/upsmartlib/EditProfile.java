package com.example.upsmartlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity  implements View.OnClickListener {
    private TextView textView;
    private TextView AddBookBtn;
    private TextView SeeBooksBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        AddBookBtn = (TextView) findViewById(R.id.AddBookBtn);
        AddBookBtn.setOnClickListener(this);
        SeeBooksBtn= (TextView) findViewById(R.id.SeeBooksBtn);
        SeeBooksBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.AddBookBtn:
                startActivity(new Intent(getApplicationContext(), ChangeUserInfo.class));
                break;
            case R.id.SeeBooksBtn:
                startActivity(new Intent(getApplicationContext(), AddReadBook.class));
        }

    }
}