package com.example.sabbir.flixtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.sabbir.flixtime.databinding.MainActivityBinding
import com.example.sabbir.flixtime.ui.main.MainViewModel
import com.example.sabbir.flixtime.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getDepartureList()
        viewModel.apiResponse.observe(this, {
            if (it.data != null) {
                showShortToast(it.message)
            } else showShortToast(it.message)
        })
    }
}