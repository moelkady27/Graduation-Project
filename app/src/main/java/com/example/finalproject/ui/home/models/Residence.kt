package com.example.finalproject.ui.home.models

data class Residence(
    val KitchenAbvGr: Int,
    val _id: String,
    val bedroomAbvGr: Int,
    val category: String,
    val centralAir: String,
    val hasBasement: Boolean,
    val hasFireplace: Boolean,
    val hasGarage: Boolean,
    val images: List<Image>,
    val isCompleted: Boolean,
    val isLiked: Boolean,
    val isSold: Boolean,
    val likes: Int,
    val location: Location,
    val neighborhood: String,
    val ownerId: OwnerId,
    val paymentPeriod: String,
    val reviews: List<Any>,
    val salePrice: Int,
    val status: String,
    val title: String,
    val totRmsAbvGrd: Int,
    val totalarea: Int,
    val totalbaths: Int,
    val totalporchsf: Int,
    val type: String
)