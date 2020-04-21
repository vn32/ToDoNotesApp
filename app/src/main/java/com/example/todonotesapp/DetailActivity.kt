package com.example.todonotesapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    lateinit var textEditTitle: TextView
    lateinit var textEditDescription: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindViews()
        setUpIntentData()
    }

    private fun setUpIntentData() {
        val intent = intent
        val title = intent.getStringExtra(AppConstant.TITLE)
        val description = intent.getStringExtra(AppConstant.DESCRIPTION)
        textEditTitle.text = title
        textEditDescription.text = description
    }

    private fun bindViews() {
        textEditTitle = findViewById(R.id.textViewTitle)
        textEditDescription = findViewById(R.id.textViewDescription)
    }
}
/*
package com.example.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView textEditTitle,textEditDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindViews();
        setUpIntentdata();
    }

    private void setUpIntentdata() {
        Intent intent=getIntent();
//        Log.d("DetailActivity",intent.getStringExtra(AppConstant.TITLE));
        String title=intent.getStringExtra(AppConstant.TITLE);
        String description=intent.getStringExtra(AppConstant.DESCRIPTION);
        textEditTitle.setText(title);
        textEditDescription.setText(description);
    }

    private void bindViews() {
        textEditTitle=findViewById(R.id.textViewTitle);
        textEditDescription=findViewById(R.id.textViewDescription);
    }
}

 */