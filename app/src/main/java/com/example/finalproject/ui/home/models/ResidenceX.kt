package com.example.finalproject.ui.home.models

data class ResidenceX(
    val KitchenAbvGr: Int,
    val _id: String,
    val bedroomAbvGr: Int,
    val category: String,
    val centralAir: String,
    val createdAt: String,
    val hasBasement: Boolean,
    val hasFireplace: Boolean,
    val hasGarage: Boolean,
    val images: List<ImageXX>,
    val isCompleted: Boolean,
    var isLiked: Boolean,
    val isSold: Boolean,
    val location: LocationXX,
    val neighborhood: String,
    val ownerId: OwnerIdX,
    val paymentPeriod: String,
    val reviews: List<Any>,
    val salePrice: Int,
    val status: String,
    val title: String,
    val totRmsAbvGrd: Int,
    val totalarea: Int,
    val totalbaths: Int,
    val totalporchsf: Int,
    val type: String,
    val updatedAt: String
)