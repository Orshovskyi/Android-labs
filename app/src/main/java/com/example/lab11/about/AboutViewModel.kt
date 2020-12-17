package com.example.lab11.about

import androidx.lifecycle.ViewModel
import timber.log.Timber

class AboutViewModel(contacts: String) : ViewModel() {

    init {
        Timber.i("AboutViewModel created")
    }

}