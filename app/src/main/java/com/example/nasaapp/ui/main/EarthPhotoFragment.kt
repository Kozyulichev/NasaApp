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
import com.example.nasaapp.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class EarthPhotoFragment : Fragment() {

    private val viewModel: EarthPhotoFragmentViewModel by lazy {
        ViewModelProvider(this).get(EarthPhotoFragmentViewModel::class.java)
    }

    private lateinit var binding: FragmentEarthPhotoBinding
    val scope = CoroutineScope(Dispatchers.Main)
    var uri: String? = null

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
//        55.891597, 37.724968
//       binding.button.setOnClickListener {
//  }
    }
}

class EarthPhotoFragmentViewModel : ViewModel() {
    var image: MutableLiveData<String> = MutableLiveData("https://api.nasa.gov/planetary/earth/imagery?lon=37.724968&lat=55.724968&date=2020-07-01&&dim=0.5&api_key=g4VGTPUodvUyso8c6hpNzGrEoZ6gd0G1olr4MfXM")
    val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    var loading:MutableLiveData<Boolean> = MutableLiveData(true)
    var lat: String? = null
    var lon: String? = null
    private val repository = RepositoryImpl()
    val scope = CoroutineScope(Dispatchers.Main)

    fun getEarthPhoto() {
        scope.launch {
            if (lat != null && lon != null) {
                loading.value = true
                val string =
                    "https://api.nasa.gov/planetary/earth/imagery?lon=$lat&lat=$lon&date=2014-02-01&&dim=0.5&api_key=g4VGTPUodvUyso8c6hpNzGrEoZ6gd0G1olr4MfXM"
                image.value = string
                loading.value = false
            }
        }
    }
}