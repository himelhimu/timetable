package com.example.sabbir.flixtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sabbir.flixtime.databinding.MainActivityBinding
import com.example.sabbir.flixtime.models.Departure
import com.example.sabbir.flixtime.ui.main.MainViewModel
import com.example.sabbir.flixtime.ui.main.TimeTableAdapter
import com.example.sabbir.flixtime.utils.NetworkConnectionDetector
import com.example.sabbir.flixtime.utils.isInternetAvailable
import com.example.sabbir.flixtime.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainActivityBinding
    private var rvAdapter: TimeTableAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isInternetAvailable()) {
            viewModel.getDepartureList()
        } else {
            binding.progressCircular.visibility = View.GONE
            showShortToast("No internet available!")
            binding.emptyMessage.visibility = View.VISIBLE
            subscribeToNetworkChanges()

        }

        viewModel.apiResponse.observe(this, {
            if (it.data != null && it.data.isNotEmpty()) {
                setUpAdapter(it.data)
            } else if(!it.message.isNullOrEmpty())showShortToast(it.message)
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            if (isInternetAvailable()) viewModel.getDepartureList()
            else showShortToast("No internet available!")
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpAdapter(itemList: List<Departure>) {
        binding.progressCircular.visibility = View.GONE
        binding.emptyMessage.visibility = View.GONE
        if (rvAdapter != null) {
            rvAdapter?.clear()
            rvAdapter?.addAll(itemList)

        } else {
            val layoutMngr = LinearLayoutManager(this).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            binding.rcvStationList.layoutManager = layoutMngr
            binding.rcvStationList.itemAnimator = DefaultItemAnimator()
            rvAdapter = TimeTableAdapter(ArrayList(itemList))
            binding.rcvStationList.adapter = rvAdapter
        }

    }

    private fun subscribeToNetworkChanges() {
        NetworkConnectionDetector(this).observe(this, { isConnected ->
            if (isConnected) {
                showShortToast("Internet connection resotred!")
                binding.progressCircular.visibility = View.VISIBLE
                viewModel.getDepartureList()
            }
        })
    }
}