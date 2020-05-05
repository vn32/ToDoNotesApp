package com.example.todonotesapp.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.todonotesapp.R
import com.example.todonotesapp.utils.PrefConstant
import com.example.todonotesapp.view.LoginActivity

class BoardingActivity : AppCompatActivity(),OnBoardingOneFragment.OnNextClick,OnBoardingTwoFragment.OnOptionClick{
    lateinit var viewPager:ViewPager
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding)
        bindViews()
        setUpSharedPreferences()
    }

    private fun setUpSharedPreferences() {
        sharedPreferences=getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun bindViews() {

        viewPager=findViewById(R.id.ViewPager)
        val adapter=FragmentAdapter(supportFragmentManager)
        viewPager.adapter=adapter
    }

    override fun onClick() {
        viewPager.currentItem=1
    }

    override fun onOptionDone() {
        //2nd Fragment
        editor=sharedPreferences.edit()
        editor.putBoolean(PrefConstant.ON_BOARDED_SUCCESSFULLY,true)
        editor.apply()
      val intent= Intent(this@BoardingActivity,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionBack() {
       viewPager.currentItem=0
    }
}
