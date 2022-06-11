package com.example.upsmartlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddBookAdmin extends AppCompatActivity {
    FirebaseFirestore db;
    EditText mtitle, mAuthor, mpages, mimage,mavailablecopys,mbookid;
    Button AddBtn;
    public static final String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_admin);
        mtitle = findViewById(R.id.title);
        mAuthor = findViewById(R.id.Author);
        mpages= findViewById(R.id.pages);
        mimage = findViewById(R.id.image);
        mavailablecopys= findViewById(R.id.availablecopys);
        mbookid = findViewById(R.id.bookid);
        AddBtn = findViewById(R.id.AddBtn);

        db = FirebaseFirestore.getInstance();


        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mtitle.getText().toString().trim();
                String Author = mAuthor .getText().toString().trim();
                String pages = mpages.getText().toString().trim();
                String image = mimage.getText().toString().trim();
                int availablecopys= Integer.parseInt(mavailablecopys.getText().toString());
                String bookid  = mbookid .getText().toString().trim();
                DocumentReference documentReference = db.collection("books").document();
                Book book=new Book( title, Author, image,  pages, availablecopys, bookid);


                documentReference.set(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "onSuccess: Book is created ");
                        startActivity(new Intent(getApplicationContext(), AdminMenu.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                        startActivity(new Intent(getApplicationContext(), AddBookAdmin.class));
                    }
                });
            }
        });

    }
}