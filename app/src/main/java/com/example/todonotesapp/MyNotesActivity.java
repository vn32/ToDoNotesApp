package com.example.todonotesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyNotesActivity extends AppCompatActivity {
    String fullname;
    FloatingActionButton fabAddNotes;
    TextView textViewDescription,textViewTitle;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        //assigning value to fabAddNotes
        bindView();
        setupSharedPreference();
        getIntentData();

//        fabAddNotes=findViewById(R.id.fabAddNotes);
//        textViewTitle=findViewById(R.id.textViewTitle);
//        textViewDescription=findViewById(R.id.textViewDescription);
        //assign value to varibale fullname of data accessed
//        Intent intent=getIntent();
//        fullname=intent.getStringExtra(AppConstant.Full_Name);
        fabAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a layout
               setUpDialogBox();
            }
        });
        //to access the action bar and replace it by value obtained
        getSupportActionBar().setTitle(fullname);

    }

    private void setupSharedPreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME,MODE_PRIVATE);
    }

    private void getIntentData() {
        Intent intent=getIntent();
        fullname=intent.getStringExtra(AppConstant.Full_Name);
        if(TextUtils.isEmpty(fullname)){
            fullname=sharedPreferences.getString(PrefConstant.FULL_NAME,"");
        }

    }

    private void bindView(){
        fabAddNotes=findViewById(R.id.fabAddNotes);
        textViewTitle=findViewById(R.id.textViewTitle);
        textViewDescription=findViewById(R.id.textViewDescription);
    }
    private void setUpDialogBox() {
    View view= LayoutInflater.from(MyNotesActivity.this).inflate(R.layout.add_notes_dialog_layout,null);
        final EditText EditTextTitle=view.findViewById(R.id.EditTextTitle);
        final EditText EditTextDescription=view.findViewById(R.id.EditTextDescription);
        Button buttonSubmit=view.findViewById(R.id.buttonSubmit);
        //in android there is no pop-up term
        //use dialog
        final AlertDialog dialog= new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //assigning data to textViewTitle and textViewDescription
                textViewTitle.setText(EditTextTitle.getText().toString());
                textViewDescription.setText(EditTextDescription.getText().toString());
                dialog.hide();
            }
        });
        dialog.show();



    }
}
