package com.example.nasaapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaapp.R
import com.example.nasaapp.databinding.MainFragmentBinding
import com.example.nasaapp.entities.NasaDTO
import com.example.nasaapp.navigateToNextFragment
import com.example.nasaapp.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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
            parentFragmentManager.navigateToNextFragment(WikiBottomSheet.newInstance(bundle))
        }
    }

}

class MainViewModel : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)
    var nasaApi: MutableLiveData<NasaDTO> = MutableLiveData()
    private val repository = RepositoryImpl()
    var wikiText: String? = null
    var date12: MutableLiveData<String> = MutableLiveData()
    val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    val twoDaysAgo get() = getDate(3)
    val oneDayAgo get() = getDate(2)
    val yesterday get() = getDate(1)

    init {
        loadApi()
    }

    fun getDate(daysMinus: Int): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        calendar.time = simpleDateFormat.parse(todayDate)
        calendar.add(Calendar.DATE, -daysMinus)
        date12.value = simpleDateFormat.format(calendar.time)
        return simpleDateFormat.format(calendar.time)
    }

    fun loadApi() {
        scope.launch { nasaApi.value = repository.getApi() }
        date12.value = todayDate
    }

    fun getNewDataByDay(string: String) {
        scope.launch {
            nasaApi.value = repository.getDateFromDate(string)

        }
    }
}
