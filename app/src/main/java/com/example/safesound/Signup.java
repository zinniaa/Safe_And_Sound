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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText name,email,phone,pass,rep_pass;
    Button b1;
    ProgressBar progress_bar;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        pass=findViewById(R.id.pass);
        rep_pass=findViewById(R.id.rep_pass);
        b1=findViewById(R.id.reg_button);

        mAuth=FirebaseAuth.getInstance();
        progress_bar=findViewById(R.id.signup_progress_bar);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m_email=email.getText().toString().trim();
                String m_pass=pass.getText().toString().trim();
                String m_rpass=rep_pass.getText().toString().trim();
                String m_name=name.getText().toString();
                String m_phone=phone.getText().toString();

                if(TextUtils.isEmpty(m_email)){
                    email.setError("Email is Required");
                    email.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(m_pass)){
                    pass.setError("Password is Required");
                    pass.requestFocus();
                    return;
                }

                if(m_pass.length()<6){
                    pass.setError("Password must be more than 6 characters");
                    pass.requestFocus();
                    return;

                }
                if(!m_pass.equals(m_rpass)){
                    rep_pass.setError("Passwords do not Match!");
                    rep_pass.requestFocus();
                    return;

                }
                progress_bar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(m_email,m_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){

                             User user=new User(m_name,m_email,m_phone);
                             FirebaseDatabase.getInstance().getReference("Users")
                                     .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                     .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if (task.isSuccessful()){
                                         progress_bar.setVisibility(View.GONE);
                                         Toast.makeText(getApplicationContext(),"New Account Created",Toast.LENGTH_SHORT).show();
                                         startActivity(new Intent(getApplicationContext(), Login.class));
                                         finish();
                                     }
                                     else{
                                         Toast.makeText(getApplicationContext(),"Registration Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                         progress_bar.setVisibility(View.GONE);
                                     }
                                 }
                             });


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



    public void ToLogin(View v){
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }

}