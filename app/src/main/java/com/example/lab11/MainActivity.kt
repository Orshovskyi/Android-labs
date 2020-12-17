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
import timber.log.Timber
import kotlin.math.roundToInt

const val KEY_RESTORE = "key_value_to_restore"

class MainActivity : AppCompatActivity() {

    private lateinit var timer: Timer
    private lateinit var inFocusTimer: InFocusTimer
    var valueToRestore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.i("onCreate called")
        if(savedInstanceState != null){
            Timber.i("Saved value: ${savedInstanceState.getInt(KEY_RESTORE, 0)}")
            Timber.i("Unsaved value: $valueToRestore")
        }
        timer = Timer(this.lifecycle)
        inFocusTimer = InFocusTimer(this.lifecycle)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        valueToRestore = 10
        outState.putInt(KEY_RESTORE, valueToRestore)
        Timber.i("onSaveInstanceState called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.i("onRestoreInstanceState called")
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

    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }

    override fun onDestroy() {
        val inFocusSeconds = inFocusTimer.secondsCount
        val generalSeconds = timer.secondsCount
        val appWasInFocus = (inFocusSeconds.toDouble() / generalSeconds.toDouble()) * 100
        appWasInFocus.roundToInt()
        Timber.i("$appWasInFocus% - App was in focus")
        super.onDestroy()
        Timber.i("onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart called")
    }
}