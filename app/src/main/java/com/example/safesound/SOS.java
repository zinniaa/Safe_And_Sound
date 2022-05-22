package com.example.safesound;

import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SOS extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ArrayList<String> contactModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        contactModelArrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Contacts");

    }

    private void getContacts() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contactModelArrayList.clear();
                for(DataSnapshot snap: snapshot.getChildren()){
                    Contacts contactsSOS=snap.getValue(Contacts.class);
                    contactModelArrayList.add(snap.child("phone").getValue(String.class));
                    SmsManager sms=SmsManager.getDefault();
                    sms.sendTextMessage(contactsSOS.phone, null, "SOS!!!I am Not Safe. Please Help Me", null,null);
                    Toast.makeText(getApplicationContext(), "Sending Alarm Message",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Read Failed");


            }
        });

    }

        public void TriggerSOS(View view) {
        Toast.makeText(getApplicationContext(), "Trigger SOS", Toast.LENGTH_LONG).show();
        getContacts();
    }
}
