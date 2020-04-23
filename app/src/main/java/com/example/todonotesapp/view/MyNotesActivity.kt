package com.example.todonotesapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todonotesapp.NotesApp
import com.example.todonotesapp.utils.AppConstant
import com.example.todonotesapp.utils.PrefConstant
import com.example.todonotesapp.R
import com.example.todonotesapp.adapter.NotesAdapter
import com.example.todonotesapp.clicklisteners.ItemClickListener
import com.example.todonotesapp.db.Notes
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MyNotesActivity : AppCompatActivity() {
    var fullName: String?=null
    lateinit var fabAddNotes: FloatingActionButton
    lateinit var recyclerViewNotes: RecyclerView
    var notesList = ArrayList<Notes>()
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        bindView()
        setupSharedPreference()
        getIntentData()
        getDataFromDb()
        setUpRecyclerView()
        supportActionBar?.title = fullName
        fabAddNotes.setOnClickListener (object:View.OnClickListener{
            override fun onClick(v: View?) {
                setUpDialogBox()
            }
        })
    }


    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun getIntentData() {
        val intent = intent
        if(intent.hasExtra(AppConstant.Full_Name)){
            fullName = intent.getStringExtra(AppConstant.Full_Name)
        }
        if (fullName==null) {
            fullName = sharedPreferences.getString(PrefConstant.FULL_NAME," ")?:""
        }

    }

    private fun bindView() {
        fabAddNotes = findViewById(R.id.fabAddNotes)
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)
    }

    @SuppressLint("InflateParams")
    private fun setUpDialogBox() {
        val view = LayoutInflater.from(this@MyNotesActivity).inflate(R.layout.add_notes_dialog_layout, null)
        val editTextTitle = view.findViewById<EditText>(R.id.EditTextTitle)
        val editTextDescription = view.findViewById<EditText>(R.id.EditTextDescription)
        val buttonSubmit = view.findViewById<Button>(R.id.buttonSubmit)
        val dialog = AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
        buttonSubmit.setOnClickListener (object:View.OnClickListener{
            override fun onClick(p0: View?) {
                val title = editTextTitle.text.toString()
                val description = editTextDescription.text.toString()
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    //this error is coming because in db->notes there are 5 column,
                    // so we have to pass it specifically,rest 3 are by default value
//                    val notes = Notes(title,description)
                    val notes = Notes(title=title,description=description)
                    //adding into recyclerview
                    notesList.add(notes)
                    addNotesToDb(notes)
                } else {
                    Toast.makeText(this@MyNotesActivity, "Title and Description can't be empty", Toast.LENGTH_SHORT).show()
                }
//                setUpRecyclerView()
                dialog.hide()
            }
        })
        dialog.show()
    }
    private fun getDataFromDb(){
        val notesApp=applicationContext as NotesApp
        val notesDao=notesApp.getNotesDb().notesDao()
        val noteList=notesDao.getAll()
        notesList.addAll(noteList)

    }

    private fun addNotesToDb(notes: Notes) {
        //insert into Db
        val notesApp=applicationContext as NotesApp
        val notesDao=notesApp.getNotesDb().notesDao()
        notesDao.insert(notes)

    }

    private fun setUpRecyclerView() {
        val itemClickListener= object : ItemClickListener {
            override fun onClick(notes: Notes) {
                val intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(AppConstant.TITLE, notes.title)
                intent.putExtra(AppConstant.DESCRIPTION, notes.description)
                startActivity(intent)
            }

            override fun onUpdate(notes: Notes) {
              //update the value of checkbox
                val notesApp=applicationContext as NotesApp
                val notesDao=notesApp.getNotesDb().notesDao()
                notesDao.updateNotes(notes)
            }
        }
        val notesAdapter = NotesAdapter(notesList, itemClickListener)
        val linearLayoutManager = LinearLayoutManager(this@MyNotesActivity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL //setting the item is shown horizontal  or veritical
        recyclerViewNotes.layoutManager = linearLayoutManager
        recyclerViewNotes.adapter = notesAdapter
    }
}
/*
package com.example.todonotesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Toast;

import com.example.todonotesapp.adapter.NotesAdapter;
import com.example.todonotesapp.clicklisteners.ItemClickListener;
import com.example.todonotesapp.model.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyNotesActivity extends AppCompatActivity {
    String fullname;
    FloatingActionButton fabAddNotes;
    RecyclerView recyclerViewNotes;
    ArrayList<Notes> notesList=new ArrayList<>();
//    TextView textViewDescription,textViewTitle;
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
        recyclerViewNotes=findViewById(R.id.recyclerViewNotes);
//        textViewTitle=findViewById(R.id.textViewTitle);
//        textViewDescription=findViewById(R.id.textViewDescription);
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
//                textViewTitle.setText(EditTextTitle.getText().toString());
//                textViewDescription.setText(EditTextDescription.getText().toString());
                //here we can set value to add into list
                String title=EditTextTitle.getText().toString();
                String description=EditTextDescription.getText().toString();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
                    Notes notes=new Notes();
                    notes.setTitle(title);
                    notes.setDescription(description);
                    notesList.add(notes);
                } else {
                    Toast.makeText(MyNotesActivity.this, "Title and Description can't be empty", Toast.LENGTH_SHORT).show();
                }
//                Log.d("MyNotesActivity", String.valueOf(notesList.size()));
                //acccesing notesadapter or binding notes with adapter
                setUpRecyclerView();
                dialog.hide();
            }
        });
        dialog.show();



    }

    private void setUpRecyclerView() {
        //interface
        ItemClickListener itemClickListener=new ItemClickListener() {
            @Override
            public void onClick(Notes notes) {
//                Log.d("MyNotesActivity",notes.getTitle());
                Intent intent=new Intent(MyNotesActivity.this,DetailActivity.class);
                intent.putExtra(AppConstant.TITLE,notes.getTitle());
                intent.putExtra(AppConstant.DESCRIPTION,notes.getDescription());
                startActivity(intent);
            }
        };

        NotesAdapter notesAdapter=new NotesAdapter(notesList,itemClickListener);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyNotesActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);//setting the item is shown horizontal  or veritical
        recyclerViewNotes.setLayoutManager(linearLayoutManager);
        recyclerViewNotes.setAdapter(notesAdapter);

    }
}
 */