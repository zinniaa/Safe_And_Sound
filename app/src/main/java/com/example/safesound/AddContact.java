package com.example.safesound;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AddContact extends AppCompatActivity {
    TextInputEditText name, phone;
    Button button;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    String contactId,uid,tag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        name=findViewById(R.id.EditContactName);
        phone=findViewById(R.id.idEditPhone);
        button=findViewById(R.id.idButonAddContact);
        progressBar=findViewById(R.id.ContactProgressBar);
        databaseReference=FirebaseDatabase.getInstance().getReference("Users");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName= Objects.requireNonNull(name.getText()).toString();
                String phoneNo= Objects.requireNonNull(phone.getText()).toString();
                contactId=contactName;

                Contacts newContact=new Contacts(contactId,contactName,phoneNo);

                //Toast.makeText(getApplicationContext(),"Name is"+contactName+"Phone is "+phoneNo,Toast.LENGTH_SHORT).show();

                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Contacts").child(contactId).setValue(newContact).addOnCompleteListener(new OnCompleteListener<Void>() {;

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"New Contact Created",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



            }
        });
    }
}
