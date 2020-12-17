package com.example.lab11.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

class ConverterViewModel : ViewModel(){

    private val _items = MutableLiveData<Array<String>>()
    val items: LiveData<Array<String>>
        get() = _items

    var lastSelectedUnitId1 = MutableLiveData<Int>()
    var lastSelectedUnitId2 = MutableLiveData<Int>()

    private val _eventValueConverted = MutableLiveData<Boolean>()
    val eventValueConverted: LiveData<Boolean>
        get() = _eventValueConverted

    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz: LiveData<BuzzType>
        get() = _eventBuzz

    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    init {
        Timber.i("ConverterViewModel created")
        _eventValueConverted.value = false
        lastSelectedUnitId1.value = 0
        lastSelectedUnitId2.value = 0
        _items.value = arrayOf("ton", "hundredweight", "kilogram", "gram", "carat", "milligram", "pound", "ounce")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ConverterViewModel destroyed")
    }

    fun onConvertBtnClicked() {
        _eventBuzz.value =
            BuzzType.GAME_OVER
        _eventValueConverted.value = true
    }

    /** Methods for completed events **/

    fun onConvertValueComplete() {
        _eventValueConverted.value = false
    }

    fun onBuzzComplete() {
        _eventBuzz.value =
            BuzzType.NO_BUZZ
    }
}