package com.example.nasaapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaapp.databinding.FragmentEarthPhotoBinding
import java.text.SimpleDateFormat
import java.util.*

class EarthPhotoFragment : Fragment() {

    private val viewModel: EarthPhotoFragmentViewModel by lazy {
        ViewModelProvider(this).get(EarthPhotoFragmentViewModel::class.java)
    }

    private lateinit var binding: FragmentEarthPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEarthPhotoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this


    }
}

class EarthPhotoFragmentViewModel : ViewModel() {
//var earthPhoto:MutableLiveData
    val ssss = "https://api.nasa.gov/planetary/earth/imagery?lon=100.75&lat=1.5&date=2014-02-01&api_key=g4VGTPUodvUyso8c6hpNzGrEoZ6gd0G1olr4MfXM"
    var image:MutableLiveData<String> = MutableLiveData()
    val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    var lat:MutableLiveData<String> = MutableLiveData()
    var lon:MutableLiveData<String> = MutableLiveData()

    fun getEarthPhoto(){
        if (lat.value!=null && lon.value!=null){
            image.value = "https://api.nasa.gov/planetary/earth/imagery?lon=$lon&lat=$lat&date=$todayDate&api_key=g4VGTPUodvUyso8c6hpNzGrEoZ6gd0G1olr4MfXM"
        }
    }
}