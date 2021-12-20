package com.example.sabbir.flixtime.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sabbir.flixtime.models.ApiResponse
import com.example.sabbir.flixtime.models.Departure
import com.example.sabbir.flixtime.models.TimeTable
import com.example.sabbir.flixtime.network.DepartureService
import com.example.sabbir.flixtime.utils.DepartureJSONDeserilizer
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val departureService: DepartureService) :
    ViewModel() {

    private var _apiResponse = MutableLiveData<ApiResponse<List<Departure>>>().apply {
        value = ApiResponse(emptyList(), null)
    }
    val apiResponse: LiveData<ApiResponse<List<Departure>>> = _apiResponse

    fun getDepartureList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = departureService.getDepartureList()
            if (response.isSuccessful) {
                val timeTable = response.body()
                if (timeTable != null) {
                    _apiResponse.postValue(
                        ApiResponse(
                            timeTable.departureList,
                            "Data received successfully"
                        )
                    )
                } else _apiResponse.postValue(ApiResponse(null, "No data available, please try again later!"))
            } else {
                _apiResponse.postValue(ApiResponse(null, response.errorBody().toString()))
            }
        }
    }
}