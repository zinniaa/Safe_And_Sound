package com.example.safesound;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SOS extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
    }

    public void TriggerSOS(View view) {
        Toast.makeText(getApplicationContext(), "Trigger SOS", Toast.LENGTH_LONG).show();
    }
}
