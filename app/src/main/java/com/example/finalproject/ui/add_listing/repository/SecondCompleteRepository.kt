package com.example.finalproject.ui.add_listing.repository

import com.example.finalproject.retrofit.ApiService
import com.example.finalproject.ui.add_listing.models.SecondCompleteResponse
import com.example.finalproject.ui.add_listing.request.SecondCompleteRequest
import retrofit2.Call

class SecondCompleteRepository(
    private val apiService: ApiService
) {
    fun secondComplete(
        token: String, residenceId: String, roofStyle: String, roofMatl: String,
        houseStyle: String, msSubClass: String, centralAir: String, street: String,
        alley: String, heating: String, heatingQc: String, masVnrType: String, masVnrArea: String,
        exterior1st: String, exterior2nd: String, exterCond: String, exterQual: String,
        condition1: String, condition2: String
    ): Call<SecondCompleteResponse> {
        val data = SecondCompleteRequest(
            roofStyle, roofMatl, houseStyle, msSubClass, centralAir, street, alley,
            heating, heatingQc, masVnrType, masVnrArea, exterior1st, exterior2nd,
            exterCond, exterQual, condition1, condition2
        )
        return apiService.secondComplete(token, residenceId, data)
    }
}