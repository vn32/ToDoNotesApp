package com.example.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                Intent intent=new Intent(LoginActivity.this,MyNotesActivity.class);
                intent.putExtra("full_name",FullName);
                startActivity(intent);

            }
        };
        buttonLoginId.setOnClickListener(clickAction);


    }
}
