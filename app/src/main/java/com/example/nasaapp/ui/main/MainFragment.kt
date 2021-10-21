package com.example.nasaapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.nasaapp.R
import com.example.nasaapp.databinding.MainFragmentBinding
import com.example.nasaapp.entities.NasaDTO
import com.example.nasaapp.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        binding.image.setOnClickListener {
            val bundle = Bundle()
            val nasaApi: String? = viewModel.nasaApi.value?.explanation
            bundle.putString(ExplanationBottomSheet.BUNDLE_EXTRA1, nasaApi)
            val explanationBottomSheet = ExplanationBottomSheet()
            explanationBottomSheet.arguments = bundle
            explanationBottomSheet.show(childFragmentManager, ExplanationBottomSheet.BUNDLE_EXTRA1)
        }

        binding.textInputLayout.setEndIconOnClickListener {
            val bundle = Bundle()
            bundle.putString(WikiBottomSheet.BUNDLE_EXTRA, viewModel.wikiText)
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, WikiBottomSheet.newInstance(bundle))
                .addToBackStack(null).commit()
        }
    }

}

class MainViewModel : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)
    var nasaApi: MutableLiveData<NasaDTO> = MutableLiveData()
    private val repository = RepositoryImpl()
    var wikiText: String? = null
    val twoDaysAgo = "2020-02-01"
    val oneDayAgo = "2021-02-01"
    val yesterday = "2020-06-01"

    init {
        loadApi()
    }

    fun loadApi() {
        scope.launch { nasaApi.value = repository.getApi() }
    }

    fun getDate(string: String) {
        scope.launch {
            nasaApi.value = repository.getDateFromDate(string)
        }
    }
}