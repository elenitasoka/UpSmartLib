package com.example.upsmartlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfile extends AppCompatActivity {
    private TextView desc,fullName;
    Button seeMoreBooksCritics,SeeMoreBooks,button;
    private ImageView imageView2,imageView3;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore db;

User v=new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        desc = findViewById(R.id.desc);
        fullName = findViewById(R.id.fullName);
        seeMoreBooksCritics = findViewById(R.id.seeMoreBooksCritics);
        button=findViewById(R.id.button);
        SeeMoreBooks = findViewById(R.id.SeeMoreBooks);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);


        userID = fAuth.getCurrentUser().getUid();
        DocumentReference docRef = db.collection("users").document(userID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot !=null){
                        v  = documentSnapshot.toObject(User.class);
                        fullName.setText(v.getFullName());
                        desc.setText(v.getDescription());

                }
            }
        }



    });
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(), EditProfile.class));
          }
      });
}
}
