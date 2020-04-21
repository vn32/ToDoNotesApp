package com.example.todonotesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        checkLoginStatus()
    }

    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun checkLoginStatus() {
        val isLoggedIn = sharedPreferences.getBoolean(PrefConstant.IS_LOGGED_IN, false)
        if (isLoggedIn) {
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
/*
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

 */