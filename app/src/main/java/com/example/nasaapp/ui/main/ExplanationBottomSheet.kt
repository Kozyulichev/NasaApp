package com.example.nasaapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.nasaapp.databinding.ExplanationBottomSheetBinding
import com.example.nasaapp.entities.NasaDTO
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExplanationBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var binding: ExplanationBottomSheetBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(BUNDLE_EXTRA1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            ExplanationBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomSheetDescriptionHeader.text = param1
    }

    companion object {
        const val BUNDLE_EXTRA1 = "wiki"

        @JvmStatic
        fun newInstance(param1: Bundle): ExplanationBottomSheet {
            var fragment = ExplanationBottomSheet()
            fragment.arguments = param1
            return fragment
        }
    }
}
