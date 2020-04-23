package com.example.todonotesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todonotesapp.R
import com.example.todonotesapp.clicklisteners.ItemClickListener
import com.example.todonotesapp.db.Notes

class NotesAdapter(val listNotes: List<Notes>,val itemClickListener: ItemClickListener) : RecyclerView.Adapter<NotesAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notes = listNotes[position]
        val title = notes.title
        val description = notes.description
        holder.textViewTitle.text = title
        holder.checkboxMarkStatus.isChecked=notes.isTaskCompleted
        holder.textViewDescription.text = description
        holder.itemView.setOnClickListener { itemClickListener.onClick(notes) }
        //for checkbox
        holder.checkboxMarkStatus.setOnCheckedChangeListener(object:CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                notes.isTaskCompleted=isChecked
                itemClickListener.onUpdate(notes)

            }

        })
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView= itemView.findViewById(R.id.textViewTitle)
        var textViewDescription: TextView= itemView.findViewById(R.id.textViewDescription)
        var checkboxMarkStatus:CheckBox=itemView.findViewById(R.id.checkboxMarkStatus)
    }
}
/*
package com.example.todonotesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todonotesapp.view.MyNotesActivity;
import com.example.todonotesapp.R;
import com.example.todonotesapp.clicklisteners.ItemClickListener;
import com.example.todonotesapp.model.Notes;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<Notes> listNotes;
    private ItemClickListener itemClickListener;
    public NotesAdapter(List<Notes> list ,ItemClickListener itemClickListener)
    {//assigning value of list to listNotes inside the constructor
        this.listNotes=list;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_layout,parent,false);
        return new ViewHolder(view);
        //oncreateviewholder is responsible for binding the layout with the adapter
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        //responsible for setting the data to the TextView
        //binding the listNotes data to the notes_adapter_layout i.e viewholder
        final Notes notes=listNotes.get(position);
        String title= notes.getTitle();//get title and getdescription are method of NOtes class
        String description= notes.getDescription();
        holder.textViewTitle.setText(title);
        holder.textViewDescription.setText(description);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            //here itemView is one item of combination Title and description in CardView of notesadapterlayout

            @Override
            public void onClick(View view) {
                itemClickListener.onClick(notes);
            }
        });


    }

    @Override
    public int getItemCount() {
        //we counting the size of listnotes
        return listNotes.size();
    }
    //responsible for holding all the views into a specific widgets
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle,textViewDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //mapping the data using findviewbyid into the constructor of viewholder class
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewDescription=itemView.findViewById(R.id.textViewDescription);

        }
    }
}

 */