package com.example.todonotesapp.clicklisteners

//funtion void interface
interface ItemClickListener {
    fun onClick(notes: com.example.todonotesapp.db.Notes)
    fun onUpdate(notes: com.example.todonotesapp.db.Notes)
}