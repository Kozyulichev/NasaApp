package com.example.nasaapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nasaapp.databinding.WikiBottomSheetFragmentBinding
import com.example.nasaapp.repository.RepositoryImpl
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class WikiBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: WikiBottomSheetFragmentBinding

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(BUNDLE_EXTRA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            WikiBottomSheetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView = binding.webView
        webView.webViewClient = WebViewClient()
        if (param1 != null) {
            webView.loadUrl("https://ru.wikipedia.org/wiki/$param1")
        } else webView.loadUrl("https://ru.wikipedia.org/wiki/")


    }

    companion object {
        const val BUNDLE_EXTRA = "wiki"

        @JvmStatic
        fun newInstance(param1: Bundle): WikiBottomSheet {
            var fragment = WikiBottomSheet()
            fragment.arguments = param1
            return fragment
        }
    }
}