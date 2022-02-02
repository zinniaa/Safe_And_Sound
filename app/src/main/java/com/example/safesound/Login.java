package com.example.safesound;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText my_email;
    EditText my_pass;
    ProgressBar progress_bar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        my_email=findViewById(R.id.email_signin);
        my_pass=findViewById(R.id.password_signin);
        button=findViewById(R.id.login_button);

        mAuth= FirebaseAuth.getInstance();
        progress_bar=findViewById(R.id.login_progress_bar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=my_email.getText().toString().trim();
                String pass=my_pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    my_email.setError("Email is Required");
                    my_email.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    my_pass.setError("Password is Required");
                    my_pass.requestFocus();
                    return;
                }

                if(pass.length()<6){
                    my_pass.setError("Password must be more than 6 characters");
                    my_pass.requestFocus();
                    return;

                }
                progress_bar.setVisibility(View.VISIBLE);

                //Authenticate The User
                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progress_bar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"Successfully Logged In",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Registration Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progress_bar.setVisibility(View.GONE);

                        }

                    }
                });
            }
        });

    }

    public void ToSignUp(View v){
        Intent i = new Intent(getApplicationContext(), Signup.class);
        startActivity(i);
    }
    public void Login(View v){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

}