package com.example.todonotesapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class LoginActivity : AppCompatActivity() {
    lateinit var editTextUserName: EditText
    lateinit var editTextFullName: EditText
    lateinit var buttonLoginId: Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindViews()
        setUpSharedPreferences()
        val clickAction = View.OnClickListener {
            val fullName = editTextFullName.text.toString()
            val userName = editTextUserName.text.toString()
            if (fullName.isNotEmpty() && userName.isNotEmpty()) {
                val intent = Intent(this@LoginActivity, MyNotesActivity::class.java)
                intent.putExtra(AppConstant.Full_Name, fullName)
                startActivity(intent)
                saveLoginStatus()
                saveFullName(fullName)
            } else {
                Toast.makeText(this@LoginActivity, "FullName and UserName can't be empty", Toast.LENGTH_SHORT).show()
            }
        }
        buttonLoginId.setOnClickListener(clickAction)
    }
    private fun bindViews(){
        editTextFullName = findViewById(R.id.EditTextFullName)
        editTextUserName = findViewById(R.id.EditTextUserName)
        buttonLoginId = findViewById(R.id.buttonLoginId)
        val clickAction=object : View.OnClickListener{
            override fun onClick(v: View?) {
                val fullName = editTextFullName.text.toString()
                val userName = editTextUserName.text.toString()
                if (fullName.isNotEmpty() && userName.isNotEmpty()) {
                    val intent = Intent(this@LoginActivity, MyNotesActivity::class.java)
                    intent.putExtra(AppConstant.Full_Name, fullName)
                    startActivity(intent)
                    saveLoginStatus()
                    saveFullName(fullName)
                } else {
                    Toast.makeText(this@LoginActivity, "FullName and UserName can't be empty", Toast.LENGTH_SHORT).show()
                }

            }

        }
        buttonLoginId.setOnClickListener(clickAction)

    }

    private fun saveFullName(fullName: String) {
         editor = sharedPreferences.edit()
        //Full name is stored into editor
        editor.putString(PrefConstant.FULL_NAME, fullName)
        editor.apply()
    }

    private fun setUpSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun saveLoginStatus() {
        editor = sharedPreferences.edit()
        editor.putBoolean(PrefConstant.IS_LOGGED_IN, true) //assign true to Is_Logged_In
        editor.apply()
    }
}
/*
package com.example.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText EditTextUserName,EditTextFullName;
    Button buttonLoginId;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //mapping of variable with id of widges
        EditTextFullName=findViewById(R.id.EditTextFullName);
        EditTextUserName=findViewById(R.id.EditTextUserName);
        buttonLoginId=findViewById(R.id.buttonLoginId);
        setUpSharedPreferences();
        View.OnClickListener clickAction=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FullName=EditTextFullName.getText().toString();
                String UserName=EditTextUserName.getText().toString();
                //checking either of Fullname + username are empty or not by TextUtils.isEmpty()
                if(!TextUtils.isEmpty(FullName) && !TextUtils.isEmpty(UserName)) {
                    Intent intent = new Intent(LoginActivity.this, MyNotesActivity.class);
                    //AppConstant.Full_name is the key
                    intent.putExtra(AppConstant.Full_Name, FullName);
                    startActivity(intent);
                    //Login
                    saveLoginStatus();
                    saveFullName(FullName);
                } else {
                    Toast.makeText(LoginActivity.this, "FullName and UserName can't be empty", Toast.LENGTH_SHORT).show();
                }

            }
        };
        buttonLoginId.setOnClickListener(clickAction);
    }

    private void saveFullName(String fullName) {
        editor=sharedPreferences.edit();
        //Full name is stored into editor
        editor.putString(PrefConstant.FULL_NAME,fullName);
        editor.apply();

    }

    private void setUpSharedPreferences(){
        //getsharedprefrences take key and value
        sharedPreferences=getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME,MODE_PRIVATE);
    }
    private void saveLoginStatus() {
        editor=sharedPreferences.edit();
        editor.putBoolean(PrefConstant.IS_LOGGED_IN,true);//assign true to Is_Logged_In
        editor.apply();
    }

}

 */