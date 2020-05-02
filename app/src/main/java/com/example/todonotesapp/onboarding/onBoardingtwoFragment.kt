package com.example.todonotesapp.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.todonotesapp.R

class OnBoardingTwoFragment : Fragment() {
    lateinit var textViewNext:TextView
    lateinit var textViewBack:TextView
    lateinit var onOptionClick: OnOptionClick
    //initializing interface variable
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onOptionClick=context as OnOptionClick
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boardingtwo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
    }

    private fun bindViews(view: View) {
        textViewNext=view.findViewById(R.id.textViewNext)
        textViewBack=view.findViewById(R.id.textViewBack)
        clickListener()

    }

    private fun clickListener() {
        textViewBack.setOnClickListener { onOptionClick.onOptionBack() }
        textViewNext.setOnClickListener { onOptionClick.onOptionNext() }
    }

    interface OnOptionClick{
        fun onOptionNext()
        fun onOptionBack()
    }
}
