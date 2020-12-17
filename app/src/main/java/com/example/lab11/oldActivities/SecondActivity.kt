package com.example.lab11.oldActivities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lab11.MainActivity
import com.example.lab11.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun showPreviousActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun showNextActivity(view: View) {
        val intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)
    }

    fun setDefaultButtonsColor(view: View) {
        button3.setBackgroundColor(Color.GRAY)
        button4.setBackgroundColor(Color.GRAY)
    }

    fun setGreenButtonsColor(view: View) {
        button3.setBackgroundColor(Color.GREEN)
        button4.setBackgroundColor(Color.GREEN)
    }

    fun setBlueButtonsColor(view: View) {
        button3.setBackgroundColor(Color.BLUE)
        button4.setBackgroundColor(Color.BLUE)
    }
}