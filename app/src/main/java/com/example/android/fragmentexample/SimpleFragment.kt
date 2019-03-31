package com.example.android.fragmentexample


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

import com.example.android.fragmentexample.R


/**
 * A simple [Fragment] subclass.
 */
class SimpleFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment.
        val rootView = inflater!!.inflate(R.layout.fragment_simple, container, false)
        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)
        val estrela = rootView.findViewById<RatingBar>(R.id.estrela)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = radioGroup.findViewById<View>(checkedId)
            val index = radioGroup.indexOfChild(radioButton)
            val textView = rootView.findViewById<TextView>(R.id.fragment_header)
            when (index) {
                YES -> textView.setText(R.string.yes_message)
                NO -> textView.setText(R.string.no_message)
                else // Nenhuma escolha feita pelo usuário.
                -> {
                }
            }// nada a fazer.
        }

        estrela.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { ratingBar, v, b ->
            Log.i("----- ESTRELA -----", "onRatingChanged: $v")

            Toast.makeText(activity, "VC SELECIONOU $v ESTRELAS", Toast.LENGTH_SHORT).show()
        }

        // Return the View for the fragment's UI.
        return rootView
    }

    companion object {


        private val YES = 0
        private val NO = 1

        fun newInstance(): SimpleFragment {
            return SimpleFragment()
        }
    }

}// Required empty public constructor
