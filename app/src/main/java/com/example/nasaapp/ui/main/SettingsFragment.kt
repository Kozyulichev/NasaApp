package com.example.nasaapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaapp.R
import com.example.nasaapp.ThemeHolder
import com.example.nasaapp.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private val viewModel: SettingsFragmentViewModel by lazy {
        ViewModelProvider(this).get(SettingsFragmentViewModel::class.java)
    }
    private lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.button.setOnClickListener {
            activity?.recreate()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    enum class ThemeSettings {
        PINK, STANDARD
    }
}

class SettingsFragmentViewModel : ViewModel() {

    var themeApp: MutableLiveData<SettingsFragment.ThemeSettings> =
        MutableLiveData(SettingsFragment.ThemeSettings.STANDARD)
    val pinkTheme = R.style.Theme_NasaApp1
    val standardTheme = R.style.Theme_NasaApp

    fun setTheme(theme: SettingsFragment.ThemeSettings) {
        when (theme) {
            SettingsFragment.ThemeSettings.PINK -> {
                ThemeHolder.theme = pinkTheme
                themeApp.value = SettingsFragment.ThemeSettings.PINK
            }
            SettingsFragment.ThemeSettings.STANDARD -> {
                ThemeHolder.theme = standardTheme
                themeApp.value = SettingsFragment.ThemeSettings.STANDARD
            }
        }
    }
}