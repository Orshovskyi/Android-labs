package com.example.lab11.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.lab11.R

class AboutFragment : Fragment() {

    private lateinit var viewModel: AboutViewModel
    private lateinit var viewModelFactory: AboutViewModelFactory
    lateinit var contacts: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View  = inflater.inflate(R.layout.fragment_about, container, false)
        contacts = view.findViewById(R.id.contacts)
        viewModelFactory =
            AboutViewModelFactory(contacts.text.toString())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AboutViewModel::class.java)
        // Inflate the layout for this fragment
        return view
    }

}