package com.example.alexvladimir.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void navegarmusica(View view){
        Intent i = new Intent (this,music.class);
        startActivity(i);
    }

    public void message(View view){
        Toast.makeText(MainMenu.this, "coming soon", Toast.LENGTH_SHORT).show();
    }
}
