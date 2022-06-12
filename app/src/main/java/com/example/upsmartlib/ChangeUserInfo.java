package com.example.upsmartlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChangeUserInfo extends AppCompatActivity {
    EditText mFullName, mEmail, mPassword, mDescription;
    Button mRegisterBtn;
    TextView textView3;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    private DatabaseReference mDatabase;
    public static final String TAG = "TAG";
    String userID;
    User v=new User();
    String prevname;
    String privemail;
    String privdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mDescription = findViewById(R.id.Description);
        mRegisterBtn = findViewById(R.id.registerBtn);



        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference docRef = db.collection("users").document(userID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if(task.isSuccessful()){
                DocumentSnapshot documentSnapshot=task.getResult();
                if(documentSnapshot !=null){
                    v  = documentSnapshot.toObject(User.class);
                    prevname=v.getFullName();
                    privemail=v.getEmail();
                    privdesc=v.getDescription();

                }
            }
            }
        });

       String fullname= mFullName.getText().toString().trim();
        String email= mEmail.getText().toString().trim();
        String description= mDescription.getText().toString().trim();


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullname=="null"){
                    docRef
                            .update("fullName",prevname);

                }else{
                    docRef
                            .update("fullName",fullname).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Log.d(TAG, "onSuccess: user Profile updated " + userID);



                                }
                            });

                }
                if(email=="null"){
                    docRef
                            .update("email",privemail);


                }else{
                    docRef
                            .update("email",email).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Log.d(TAG, "onSuccess: user Profile updated " + userID);


                                }
                            });

                }
                if(description=="null"){
                    docRef
                            .update("description", privdesc);


                }else{
                    docRef
                            .update("description",description).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                        Log.d(TAG, "onSuccess: user Profile updated " + userID);



                                }
                            });

                }

                startActivity(new Intent(getApplicationContext(), UserProfile.class));

            }

        });

    }
}