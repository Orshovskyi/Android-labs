package com.example.lab11.oldActivities

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab11.MainActivity
import com.example.lab11.R
import kotlinx.android.synthetic.main.activity_third.*


class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val items = arrayOf("ton", "hundredweight", "kilogram", "gram", "carat", "milligram", "pound", "ounce")

        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        spinner1.adapter = adapter
        spinner2.adapter = adapter
    }

    fun showSecondActivity(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    fun showMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun convert(view: View) {
        val inputValue = input.text.toString()
        val inputValueDouble = inputValue.toDouble()
        when (spinner2.selectedItem) {
            "ton" -> toTon(inputValueDouble)
            "hundredweight" -> toHundredweight(inputValueDouble)
            "kilogram" -> toKilogram(inputValueDouble)
            "gram" -> toGram(inputValueDouble)
            "carat" -> toCarat(inputValueDouble)
            "milligram" -> toMilligram(inputValueDouble)
            "pound" -> toPound(inputValueDouble)
            "ounce" -> toOunce(inputValueDouble)
        }

        copyToClipBoard()
    }

    fun copyToClipBoard() {
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("simple text", resultField.text)
        clipboard.setPrimaryClip(clip)
        val toast = Toast.makeText(this, "Result copied", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun toTon(input: Double) {
        when (spinner1.selectedItem) {
            "hundredweight" -> setResult(input/10)
            "kilogram" -> setResult(input/1000)
            "gram" -> setResult(input/1000000)
            "carat" -> setResult(input/5000000)
            "milligram" -> setResult(input/1000000000)
            "pound" -> setResult(input/2205.07)
            "ounce" -> setResult(input/35273.36)
        }
    }

    fun toHundredweight(input: Double) {
        when (spinner1.selectedItem) {
            "ton" -> setResult(input/0.1)
            "kilogram" -> setResult(input/10)
            "gram" -> setResult(input/100000)
            "carat" -> setResult(input/500000)
            "milligram" -> setResult(input/100000000)
            "pound" -> setResult(input/220.5)
            "ounce" -> setResult(input/3527.3)
        }
    }

    fun toKilogram(input: Double) {
        when (spinner1.selectedItem) {
            "ton" -> setResult(input/0.001)
            "hundredweight" -> setResult(input/0.01)
            "gram" -> setResult(input/1000)
            "carat" -> setResult(input/5000)
            "milligram" -> setResult(input/1000000)
            "pound" -> setResult(input/2.2)
            "ounce" -> setResult(input/35.27)
        }
    }

    fun toGram(input: Double) {
        when (spinner1.selectedItem) {
            "ton" -> setResult(input/0.000001)
            "hundredweight" -> setResult(input/0.00001)
            "kilogram" -> setResult(input/0.001)
            "carat" -> setResult(input/5)
            "milligram" -> setResult(input/1000)
            "pound" -> setResult(input/0.0022)
            "ounce" -> setResult(input/0.035)
        }
    }

    fun toCarat(input: Double) {
        when (spinner1.selectedItem) {
            "ton" -> setResult(input/2.0000000e-7)
            "hundredweight" -> setResult(input/0.000002)
            "kilogram" -> setResult(input/0.0002)
            "gram" -> setResult(input/0.2)
            "milligram" -> setResult(input/200)
            "pound" -> setResult(input/0.00044)
            "ounce" -> setResult(input/0.007)
        }
    }

    fun toMilligram(input: Double) {
        when (spinner1.selectedItem) {
            "ton" -> setResult(input/1e-9)
            "hundredweight" -> setResult(input/1e-8)
            "kilogram" -> setResult(input/0.000001)
            "gram" -> setResult(input/0.001)
            "carat" -> setResult(input/0.005)
            "pound" -> setResult(input/0.0000022)
            "ounce" -> setResult(input/0.0000353)
        }
    }

    fun toPound(input: Double) {
        when (spinner1.selectedItem) {
            "ton" -> setResult(input/0.0004535)
            "hundredweight" -> setResult(input/0.004535)
            "kilogram" -> setResult(input/0.4535)
            "gram" -> setResult(input/453.5)
            "carat" -> setResult(input/2267.5)
            "milligram" -> setResult(input/453500)
            "ounce" -> setResult(input/15.99)
        }
    }

    fun toOunce(input: Double) {
        when (spinner1.selectedItem) {
            "ton" -> setResult(input/0.0000284)
            "hundredweight" -> setResult(input/0.0002835)
            "kilogram" -> setResult(input/0.02835)
            "gram" -> setResult(input/28.35)
            "carat" -> setResult(input/141.75)
            "milligram" -> setResult(input/28350)
            "pound" -> setResult(input/0.0625)
        }
    }

    fun setResult(result: Double) {
        val s: String = result.toString()
        resultField.setText(s)
    }
}