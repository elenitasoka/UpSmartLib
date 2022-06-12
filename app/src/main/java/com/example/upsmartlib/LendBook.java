package com.example.upsmartlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class LendBook extends AppCompatActivity {
    private TextView title, author;
    private ImageView image;
    Button button2;
    String userID;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore db;
    String BookID;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend_book);
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //   title=findViewById(R.id.title);
        //    author=findViewById(R.id.Author);
        image = findViewById(R.id.image);
        button2 = findViewById(R.id.button2);
        Intent i = getIntent();
        Book book = (Book) i.getSerializableExtra("book");
        BookID = book.getBookId();
     //     title.setText(book.getTitle());
      //     author.setText(book.getAuthor());
        Picasso.get()
               .load(book.getImage())
               .into(image);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = db.collection("users").document(userID).collection("LendedBooks").document(BookID);
                documentReference.set(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "onSuccess: Now you can read your new book" + userID);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });
                startActivity(new Intent(getApplicationContext(), UserChoice.class));
            }
        });

    }

    }
