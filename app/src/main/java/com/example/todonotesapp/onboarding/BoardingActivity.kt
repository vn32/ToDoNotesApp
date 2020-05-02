package com.example.todonotesapp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.todonotesapp.R
import com.example.todonotesapp.view.LoginActivity

class BoardingActivity : AppCompatActivity(),OnBoardingOneFragment.OnNextClick,OnBoardingTwoFragment.OnOptionClick{
    lateinit var viewPager:ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding)
        bindViews()
    }

    private fun bindViews() {

        viewPager=findViewById(R.id.ViewPager)
        val adapter=FragmentAdapter(supportFragmentManager)
        viewPager.adapter=adapter
    }

    override fun onClick() {
        viewPager.currentItem=1
    }

    override fun onOptionNext() {
      val intent= Intent(this@BoardingActivity,LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionBack() {
       viewPager.currentItem=0
    }
}
