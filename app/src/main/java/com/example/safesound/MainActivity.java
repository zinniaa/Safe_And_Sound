package com.example.safesound;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        //ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#612897"));
        // Set BackgroundDrawable
        //actionBar.setBackgroundDrawable(colorDrawable);
        //actionBar.setTitle("Safe  Sound");
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setHomeAsUpIndicator(R.drawable.applogo);

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
                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Item 2 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item4:
                Toast.makeText(getApplicationContext(), "Item 4 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item5:
                Toast.makeText(getApplicationContext(), "Item 5 Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

}