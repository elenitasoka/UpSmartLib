package com.example.upsmartlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{
    EditText mFullName,mEmail,mPassword,mDescription;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFullName = findViewById(R.id.fullName);
        mEmail =findViewById(R.id.Email);
        mPassword=findViewById(R.id.Password);
        mDescription=findViewById(R.id.Description);
        mRegisterBtn =findViewById(R.id.registerBtn);
        mLoginBtn=findViewById(R.id.createText);
        fAuth=FirebaseAuth.getInstance();
        progressBar =findViewById(R.id.progressBar);

        mRegisterBtn.setOnClickListener(this);


        if(fAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }

/*
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mEmail.setError("Password is Required");
                    return;
                }
                if (password.length()<6)
                {
                    mPassword.setError("Password must be >=8 Characters");
                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                        Toast.makeText(Register.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createText:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.registerBtn:
                registerUser();
        }
    }

    private void registerUser() {
        String email=mEmail.getText().toString().trim();
        String password=mPassword.getText().toString().trim();
        String fullname=mFullName.getText().toString().trim();
        String description=mDescription.getText().toString().trim();

            if(fullname.isEmpty())
            {
                mFullName.setError("Full Name is required");
                mFullName.requestFocus();
                return;

            }
        if(email.isEmpty())
        {
            mEmail.setError("Email is required");
            mEmail.requestFocus();
            return;

        }
        if(description.isEmpty())
        {
            mDescription.setError("Full Name is required");
            mDescription.requestFocus();
            return;

        }
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Provide valid email!");
            mEmail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            mPassword.setError("Password is required");
            mPassword.requestFocus();
            return;

        }
        if(password.length()<6){
            mPassword.setError("Password must be more that 6 characters");
            mPassword.requestFocus();
            return;

        }
        progressBar.setVisibility(View.VISIBLE);
        fAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User(fullname,email,description);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this,"User has been created!",Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE)
                                        ;
                                    }
                                    else{
                                        Toast.makeText(Register.this,"User isnt created",Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }
                    }
                });

    }
}