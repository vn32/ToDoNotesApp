package com.example.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupSharedpreference();
        checkLoginStatus();
    }

    private void setupSharedpreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME,MODE_PRIVATE);
    }

    private void checkLoginStatus(){
        //if user is logged in ->Mynotesactivity
        //else loginactivity
        boolean isLoggedIn=sharedPreferences.getBoolean(PrefConstant.IS_LOGGED_IN,false);
        if(isLoggedIn)
        {
            //mynotes
            Intent intent=new Intent(SplashActivity.this,MyNotesActivity.class);
            startActivity(intent);
        } else {
            //login
            Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
