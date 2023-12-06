package com.example.finalproject.retrofit

import com.example.finalproject.ui.register.models.ResendCodeResponse
import com.example.finalproject.ui.register.models.SignInResponse
import com.example.finalproject.ui.register.models.SignUpResponse
import com.example.finalproject.ui.register.models.VerificationCodeSignUpResponse
import com.example.finalproject.ui.register.request.SignInRequest
import com.example.finalproject.ui.register.request.SignUpRequest
import com.example.finalproject.ui.register.request.VerificationCodeSignUpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/signup")
    fun signup(
        @Body req: SignUpRequest
    ) : Call<SignUpResponse>

    @POST("api/v1/auth/login")
    fun login(
        @Body req: SignInRequest
    ) : Call<SignInResponse>

    @POST("api/v1/auth/verification/{id}")
    fun verifyAccount(
        @Path("id") userId: String,
        @Body request: VerificationCodeSignUpRequest
    ): Call<VerificationCodeSignUpResponse>

    @GET("/api/v1/auth/resend-code/{id}")
    fun resendCode(
        @Path("id") userId: String
    ): Call<ResendCodeResponse>
}