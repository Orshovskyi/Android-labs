package com.example.lab11

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setLabelText(view: View) {
        val enteredText = textField.text.toString()
        textView.text = enteredText
    }

    fun showSecondActivity(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    fun showAuthorInfo(view: View) {
        val toast = Toast.makeText(this, "Author: Serhii Orshovskiy", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun copyToClipBoard(view: View) {
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("simple text", textView.text)
        clipboard.setPrimaryClip(clip)
    }
}