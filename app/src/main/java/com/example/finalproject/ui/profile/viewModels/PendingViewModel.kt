package com.example.finalproject.ui.profile.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.ui.profile.models.ResidenceResponse
import com.example.finalproject.ui.profile.repository.PendingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PendingViewModel(
    private val pendingRepository: PendingRepository
): ViewModel() {
    val pendingResponseLiveData: MutableLiveData<ResidenceResponse> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getPending(token: String) {
        pendingRepository.getPending(token)
            .enqueue(object : Callback<ResidenceResponse>{
                override fun onResponse(
                    call: Call<ResidenceResponse>,
                    response: Response<ResidenceResponse>
                ) {
                    if (response.isSuccessful) {
                        pendingResponseLiveData.value = response.body()
                    } else {
                        errorLiveData.value = response.errorBody().toString()
                    }
                }

                override fun onFailure(call: Call<ResidenceResponse>, t: Throwable) {
                    errorLiveData.value = t.message
                }

            })
    }
}