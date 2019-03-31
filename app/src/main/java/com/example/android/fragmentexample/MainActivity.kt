/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentexample


import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

/*
    LINK DO CODELAB: https://codelabs.developers.google.com/codelabs/advanced-android-training-fragments/
     */

class MainActivity : AppCompatActivity() {

    private var mButton: Button? = null
    private var isFragmentDisplayed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mButton = findViewById(R.id.abrir_botao)

        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mButton!!.setText(R.string.close)
            }
        }

        // Set the click listener for the button.
        mButton!!.setOnClickListener {
            if (!isFragmentDisplayed) {
                displayFragment()
            } else {
                closeFragment()
            }
        }

    }

    fun displayFragment() {
        // Instantiate the fragment.
        val simpleFragment = SimpleFragment.newInstance()
        // Get the FragmentManager and start a transaction.
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Add the SimpleFragment.
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit()

        // Update the Button text.
        mButton!!.setText(R.string.close)
        // Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true
    }

    fun closeFragment() {
        // Get the FragmentManager.
        val fragmentManager = supportFragmentManager
        // Check to see if the fragment is already showing.
        val simpleFragment = fragmentManager.findFragmentById(R.id.fragment_container) as SimpleFragment
        if (simpleFragment != null) {
            // Create and commit the transaction to remove the fragment.
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
        }
        // Update the Button text.
        mButton!!.setText(R.string.open)
        // Set boolean flag to indicate fragment is closed.
        isFragmentDisplayed = false
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }

    companion object {
        internal val STATE_FRAGMENT = "state_of_fragment"
    }

}
