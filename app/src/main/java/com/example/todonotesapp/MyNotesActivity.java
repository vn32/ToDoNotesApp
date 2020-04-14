package com.example.todonotesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyNotesActivity extends AppCompatActivity {
    String fullname;
    FloatingActionButton fabAddNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        //assigning value to fabAddNotes
        fabAddNotes=findViewById(R.id.fabAddNotes);

        Intent intent=getIntent();
        //assign value to varibale fullname of data accessed
        fullname=intent.getStringExtra("full_name");
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
    private void setUpDialogBox() {
    View view= LayoutInflater.from(MyNotesActivity.this).inflate(R.layout.add_notes_dialog_layout,null);
        EditText EditTextTitle=view.findViewById(R.id.EditTextTitle);
        EditText EditTextDescription=view.findViewById(R.id.EditTextDescription);
        Button buttonSubmit=view.findViewById(R.id.buttonSubmit);
        //in android there is no pop-up term
        //use dialog
        AlertDialog dialog= new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();
        dialog.show();



    }
}
