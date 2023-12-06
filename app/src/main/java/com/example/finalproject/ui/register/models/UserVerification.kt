package com.example.finalproject.ui.register.models

data class UserVerification(
    val __v: Int,
    val _id: String,
    val counter: Int,
    val createdAt: String,
    val email: String,
    val isVerified: Boolean,
    val otp: Any,
    val password: String,
    val role: String,
    val updatedAt: String,
    val userName: String,
    val wishlist: List<Any>
)