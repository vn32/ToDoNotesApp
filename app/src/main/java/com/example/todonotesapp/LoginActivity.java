package com.example.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //mapping of variable with id of widges
        EditTextFullName=findViewById(R.id.EditTextFullName);
        EditTextUserName=findViewById(R.id.EditTextUserName);
        buttonLoginId=findViewById(R.id.buttonLoginId);
        View.OnClickListener clickAction=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FullName=EditTextFullName.getText().toString();
                String UserName=EditTextUserName.getText().toString();
                //checking either of Fullname + username are empty or not by TextUtils.isEmpty()
                if(!TextUtils.isEmpty(FullName) && !TextUtils.isEmpty(UserName)) {
                    Intent intent = new Intent(LoginActivity.this, MyNotesActivity.class);
                    intent.putExtra("full_name", FullName);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "FullName and UserName can't be empty", Toast.LENGTH_SHORT).show();
                }

            }
        };
        buttonLoginId.setOnClickListener(clickAction);


    }
}
