package com.example.nasaapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.navigateToNextFragment(fragment: Fragment) {
    this.beginTransaction().replace(R.id.container, fragment)
        .addToBackStack(null).commit()
}