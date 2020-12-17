package com.example.lab11.converter

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lab11.R
import kotlinx.android.synthetic.main.activity_third.*
import timber.log.Timber


class ConverterFragment : Fragment() {
    private lateinit var viewModel: ConverterViewModel

    lateinit var converBtn: Button
    lateinit var spinner1: Spinner
    lateinit var spinner2: Spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("ViewModelProviders.of called")
        viewModel = ViewModelProviders.of(this).get(ConverterViewModel::class.java)

        // Inflate the layout for this fragment
        val view: View  = inflater.inflate(R.layout.fragment_converter, container, false)
        converBtn = view.findViewById(R.id.convertBtn)
        converBtn.setOnClickListener(View.OnClickListener {
            convert(view)
            viewModel.onConvertBtnClicked()
        })


        val adapter =
            this.activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item,
                viewModel.items.value as Array<out String>
            ) }
        spinner1 = view.findViewById(R.id.spinner1)
        spinner1.adapter = adapter
        spinner2 = view.findViewById(R.id.spinner2)
        spinner2.adapter = adapter

        viewModel.lastSelectedUnitId1.observe(this, Observer { newUnitId ->
            spinner1.setSelection(newUnitId)
        })

        viewModel.lastSelectedUnitId2.observe(this, Observer { newUnitId ->
            spinner2.setSelection(newUnitId)
        })

        viewModel.eventValueConverted.observe(viewLifecycleOwner, Observer { isConverted ->
            if (isConverted) {
                notifyThatValueWasConverted()
                viewModel.onConvertValueComplete()
            }
        })

        viewModel.eventBuzz.observe(this, Observer { buzzType ->
            if (buzzType != ConverterViewModel.BuzzType.NO_BUZZ) {
                buzz(buzzType.pattern)
                viewModel.onBuzzComplete()
            }
        })

        return view
    }

    fun convert(view: View) {
        val inputValue = input.text.toString()

        var inputValueDouble = 0.0
        try {
            inputValueDouble = inputValue.toDouble()
        } catch (ex: NumberFormatException){
            val toast = Toast.makeText(this.context, "Please enter valid value", Toast.LENGTH_SHORT)
            toast.show()
            return
        }

        when (spinner2.selectedItem) {
            spinner1.selectedItem -> resultField.setText(input.text)
            "ton" -> toTon(inputValueDouble)
            "hundredweight" -> toHundredweight(inputValueDouble)
            "kilogram" -> toKilogram(inputValueDouble)
            "gram" -> toGram(inputValueDouble)
            "carat" -> toCarat(inputValueDouble)
            "milligram" -> toMilligram(inputValueDouble)
            "pound" -> toPound(inputValueDouble)
            "ounce" -> toOunce(inputValueDouble)
        }

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

    override fun onDestroy() {
        Timber.i("onDestroy Fragment")
        viewModel.lastSelectedUnitId1.value = spinner1.selectedItemId.toInt()
        viewModel.lastSelectedUnitId2.value = spinner2.selectedItemId.toInt()
        super.onDestroy()
    }

    fun notifyThatValueWasConverted() {
        Toast.makeText(this.context, "Value converted", Toast.LENGTH_SHORT).show()
    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()
        buzzer?.let {
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }
}