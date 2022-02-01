package com.example.safesound;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        mAuth = FirebaseAuth.getInstance();


        //ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#612897"));
        // Set BackgroundDrawable
        //actionBar.setBackgroundDrawable(colorDrawable);
        //actionBar.setTitle("Safe  Sound");
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setHomeAsUpIndicator(R.drawable.applogo);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                ToEmergencyContacts();
                return true;
            case R.id.item2:
                toMyProfile();
                return true;
            case R.id.item3:
                toHowToUse();
                return true;
            case R.id.item4:
                toSettings();
                return true;
            case R.id.item5:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toSettings() {
        Intent i = new Intent(getApplicationContext(), Settings.class);
        startActivity(i);
    }

    private void toHowToUse() {
        Intent i = new Intent(getApplicationContext(), HowToUse.class);
        startActivity(i);
    }


    private void toMyProfile() {
        Intent i = new Intent(getApplicationContext(), MyProfile.class);
        startActivity(i);

    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
        finish();

    }
    private void ToEmergencyContacts() {
        Intent i = new Intent(getApplicationContext(), EmergencyContacts.class);
        startActivity(i);
    }


    public void ToHelplineNumbers(View v){
        Intent i = new Intent(getApplicationContext(), HelplineNumbers.class);
        startActivity(i);
    }

    public void ToMyLocation(View v){
        Intent i = new Intent(getApplicationContext(), GetLocation.class);
        startActivity(i);
    }

    public void ToFakeCall(View v){
        Intent i = new Intent(getApplicationContext(), FakeCall.class);
        startActivity(i);
    }

    public void ToAlarm(View v){
        Intent i = new Intent(getApplicationContext(), Alarm.class);
        startActivity(i);
    }
    public void ToSOS(View v){
        Intent i = new Intent(getApplicationContext(), SOS.class);
        startActivity(i);
    }


}