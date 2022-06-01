package com.example.upsmartlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private TextView signIn;
    private EditText Email,Password;
 //   private Button signIn;
 private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private static final  String USER="user";
    private static final String TAG="MainActivity";
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        register=(TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        signIn=(TextView) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.register:
                startActivity(new Intent(this,Register.class));
                break;
            case R.id.signIn:
                String email=Email.getText().toString().trim();
                String password=Password.getText().toString().trim();
                signin(email,password);
        }


    }

    private void signin(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new  OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity .this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public void updateUI(FirebaseUser currentUser){
        Intent profilIntent=new Intent(this,Menu.class);
        profilIntent.putExtra("email",currentUser.getEmail());
        startActivity(profilIntent);
    }
}