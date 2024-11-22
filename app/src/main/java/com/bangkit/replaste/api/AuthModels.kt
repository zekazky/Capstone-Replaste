package com.bangkit.replaste.api


// models utk request
data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val full_name: String
)

data class ResetPasswordRequest(
    val email: String,
    val new_password: String? = null,
    val reset_token: String? = null,
    val confirm_password: String? = null
)

data class NewPasswordRequest(
    val new_password: String,
    val confirm_password: String
)

//models utk response
data class AuthResponse(
    val token: String, // Token autentikasi (misalnya untuk login)
    val message: String
)

data class BaseResponse(
    val message: String
)

data class MessageResponse(
    val message: String
)
