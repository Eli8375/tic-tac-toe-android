package com.example.tic_tac_toe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SettingsFragment : Fragment() {

    private lateinit var chooseColorTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val parentView = inflater.inflate(R.layout.fragment_settings, container, false)

        chooseColorTextView = parentView.findViewById<TextView>(R.id.choose_color)


        // Inflate the layout for this fragment
        return parentView
    }

}