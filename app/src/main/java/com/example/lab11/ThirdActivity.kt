package com.example.lab11

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_third.*


class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            textView5.text = "You have chosen: $dayOfMonth.${month + 1}.$year"
        }
    }

    fun showPreviousActivity(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    fun showNextActivity(view: View) {

    }

    fun clear(view: View) {
        textView5.text = ""
    }

    fun copyToClipBoard(view: View) {
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("simple text", textView5.text)
        clipboard.setPrimaryClip(clip)
    }
}