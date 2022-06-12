package com.example.upsmartlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddReadBook extends AppCompatActivity {
    FirebaseFirestore db;
    EditText mtitle, mAuthor, mpages, mimage,mavailablecopys,mbookid;
    Button AddBtn;
    String userID;
    FirebaseAuth fAuth;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_read_book);
        mtitle = findViewById(R.id.title);
        mAuthor = findViewById(R.id.Author);
        mpages= findViewById(R.id.pages);
        mimage = findViewById(R.id.image);
        mavailablecopys= findViewById(R.id.availablecopys);
        mbookid = findViewById(R.id.bookid);
        AddBtn = findViewById(R.id.AddBtn);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();


        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mtitle.getText().toString().trim();
                String Author = mAuthor .getText().toString().trim();
                String pages = mpages.getText().toString().trim();
                String image = mimage.getText().toString().trim();
                int availablecopys= Integer.parseInt(mavailablecopys.getText().toString());
                String bookid  = mbookid .getText().toString().trim();
                Book book=new Book( title, Author, image,  pages, availablecopys, bookid);

                CollectionReference documentReference = db.collection("users").document(userID).collection("ReadBooks");
                documentReference.add(book).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }

                });
                startActivity(new Intent(getApplicationContext(), UserChoice.class));

            }
        });
    }
}