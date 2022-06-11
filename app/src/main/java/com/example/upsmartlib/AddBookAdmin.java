package com.example.upsmartlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddBookAdmin extends AppCompatActivity {
    FirebaseFirestore db;
    EditText mFullName, mEmail, mPassword, mDescription;
    Button AddBookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_admin);


    }
}