package com.example.lab11

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_converter.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lateinit var fragment: Fragment
        if(item.itemId == R.id.share){
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT, "Conversion result: " + input.text + " " + spinner1.selectedItem + " = " + resultField.text + " " + spinner2.selectedItem)
                this.type = "text/plain"
            }
            startActivity(shareIntent)
        } else if(item.itemId == R.id.aboutMenuItem) {
            fragment = AboutFragment()
            val fm: FragmentManager = supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.replace(R.id.fragment_place, fragment)

            ft.commit()
        } else if(item.itemId == R.id.homeMenuItem) {
            fragment = ConverterFragment()
            val fm: FragmentManager = supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.replace(R.id.fragment_place, fragment)

            ft.commit()
        } else {
            return super.onOptionsItemSelected(item)
        }

        return true
    }
}