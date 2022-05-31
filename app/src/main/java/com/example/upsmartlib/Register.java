package com.example.upsmartlib;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;

public class Register extends AppCompatActivity  {
    EditText mFullName, mEmail, mPassword, mDescription;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    private     FirebaseDatabase database;
    private  DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private static final  String USER="user";
    private static final String TAG="register";
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mDescription = findViewById(R.id.Description);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);
        progressBar = findViewById(R.id.progressBar);

        database=FirebaseDatabase.getInstance();
        mDatabase=database.getReference(USER);
        mAuth =FirebaseAuth. getInstance();


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String Fullname=mFullName.getText().toString().trim();
                String description=mDescription.getText().toString().trim();
                user=new User(Fullname,email,password);
                RegisterUser(email,password);

            }

        });
        
    }
public  void RegisterUser(String email,String password){
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new  OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(Register.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

}
public void updateUI(FirebaseUser currentUser){
        String keyid=mDatabase.push().getKey();
        mDatabase.child(keyid).setValue(user);
        Intent loginIntent=new Intent(this,MainActivity.class);
    startActivity(loginIntent);
}

}
    
