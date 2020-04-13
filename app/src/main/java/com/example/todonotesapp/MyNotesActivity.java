package com.example.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyNotesActivity extends AppCompatActivity {
    String fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        Intent intent=getIntent();
        //assign value to varibale fullname of data accessed
        fullname=intent.getStringExtra("full_name");
        //to access the action bar and replace it by value obtained
        getSupportActionBar().setTitle(fullname);
    }
}
