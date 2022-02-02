package com.example.safesound;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddContact extends AppCompatActivity {
    TextInputEditText name, phone;
    Button button;
    ProgressBar progressBar;
    //FirebaseDatabase firebaseDatabase;
    //DatabaseReference databaseReference;
    //private String contactId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        name=findViewById(R.id.EditContactName);
        phone=findViewById(R.id.idEditPhone);
        button=findViewById(R.id.idButonAddContact);
        progressBar=findViewById(R.id.ContactProgressBar);
        //firebaseDatabase=FirebaseDatabase.getInstance();
        //databaseReference=firebaseDatabase.getReference("Contacts");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName=name.getText().toString();
                String phoneNo=phone.getText().toString();
                Toast.makeText(getApplicationContext(),"Name is"+contactName+"Phone is "+phoneNo,Toast.LENGTH_SHORT).show();


            }
        });
    }
}
