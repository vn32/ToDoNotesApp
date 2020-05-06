package com.example.todonotesapp.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.todonotesapp.utils.PrefConstant
import com.example.todonotesapp.R
import com.example.todonotesapp.onboarding.BoardingActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        checkLoginStatus()
        fcmToken()
//        setUpNotificationMessage("this is local notification")
    }
//    private fun setUpNotificationMessage(body: String?) {
//        val channelId="Todo "
//        val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        val notificationBuilder = NotificationCompat.Builder(this, channelId)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle("ToDo Notes App")
//                .setContentText(body)
//                .setSound(ringtone)
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(channelId, "Local-Notes", NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(channel)
//        }
//        notificationManager.notify(0, notificationBuilder.build())
//
//    }

    private fun fcmToken() {
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("FiresPlace", "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    // Log and toast
//                    val msg = getString(R.string.msg_token_fmt, token)
                    Log.d("FiresPlace", token)
                    Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                })
    }

    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun checkLoginStatus() {
        val isBoardingSuccess=sharedPreferences.getBoolean(PrefConstant.ON_BOARDED_SUCCESSFULLY,false)
        val isLoggedIn = sharedPreferences.getBoolean(PrefConstant.IS_LOGGED_IN, false)
        if (isLoggedIn) {
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)


        } else {
            //if on boarded success->login
            //else go to onboardingactivity
            if(isBoardingSuccess){
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)

            } else {
                val intent = Intent(this@SplashActivity, BoardingActivity::class.java)
                startActivity(intent)


            }
        }
        finish()


    }
}
/*
java code
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