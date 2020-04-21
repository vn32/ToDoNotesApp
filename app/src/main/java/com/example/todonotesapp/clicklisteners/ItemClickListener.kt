package com.example.todonotesapp.clicklisteners

import com.example.todonotesapp.model.Notes
//funtion void interface
interface ItemClickListener {
    fun onClick(notes: Notes)
}