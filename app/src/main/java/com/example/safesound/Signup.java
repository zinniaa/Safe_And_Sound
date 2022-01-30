package com.example.safesound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void ToLogin(View v){
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
    public void Register(View v){
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
}