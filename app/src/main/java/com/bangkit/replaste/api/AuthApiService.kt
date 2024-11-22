package com.bangkit.replaste.api


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {

    @POST(ApiConstants.LOGIN)
    fun login(@Body body: LoginRequest): Call<AuthResponse>

    @POST(ApiConstants.REGISTER)
    fun register(@Body body: RegisterRequest): Call<AuthResponse>

    @POST("${ApiConstants.RESET_PASSWORD}/{token}")
    fun resetPassword(
        @Path("token") token: String,
        @Body body: NewPasswordRequest
    ): Call<BaseResponse>

    @POST(ApiConstants.REQUEST_RESET)
    fun requestPasswordReset(@Body request: ResetPasswordRequest): Call<MessageResponse>
}