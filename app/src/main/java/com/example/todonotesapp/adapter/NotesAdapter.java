package com.example.todonotesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todonotesapp.MyNotesActivity;
import com.example.todonotesapp.R;
import com.example.todonotesapp.model.Notes;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    List<Notes> listNotes;
    public NotesAdapter(List<Notes> list)
    {//assigning value of list to listNotes inside the constructor
        this.listNotes=list;
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
        Notes notes=listNotes.get(position);
        String title=notes.getTitle();//get title and getdescription are method of NOtes class
        String description=notes.getDescription();
        holder.textViewTitle.setText(title);
        holder.textViewDescription.setText(description);


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
