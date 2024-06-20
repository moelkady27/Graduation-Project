package com.example.finalproject.ui.recommendation.models

data class Data(
    val Id: Int,
    val _id: String,
    val alley: String,
    val avgRating: Int,
    val bedroomAbvGr: Int,
    val bldgType: String,
    val bsmtCond: String,
    val bsmtExposure: String,
    val bsmtFinType1: String,
    val bsmtQual: String,
    val bsmtUnfSF: Int,
    val category: String,
    val centralAir: String,
    val condition1: String,
    val condition2: String,
    val createdAt: String,
    val electrical: String,
    val exterCond: String,
    val exterQual: String,
    val exterior1st: String,
    val exterior2nd: String,
    val fireplaceQu: String,
    val fireplaces: Int,
    val foundation: String,
    val garageCars: Int,
    val garageFinish: String,
    val garageQual: String,
    val garageType: String,
    val hasBasement: Boolean,
    val hasFireplace: Boolean,
    val hasGarage: Boolean,
    val heating: String,
    val heatingQc: String,
    val houseStyle: String,
    val houseage: Int,
    val houseremodelage: Int,
    val images: List<Image>,
    val isCompleted: Boolean,
    val isLiked: Boolean,
    val isSold: Boolean,
    val kitchenQual: String,
    val landContour: String,
    val landSlope: String,
    val likedUsers: List<Any>,
    val location: Location,
    val lotArea: Int,
    val lotConfig: String,
    val lotFrontage: Int,
    val lotShape: String,
    val lowQualFinSF: Int,
    val masVnrArea: Int,
    val masVnrType: String,
    val miscVal: Int,
    val moSold: Int,
    val msSubClass: String,
    val mszoning: String,
    val neighborhood: String,
    val overallCond: Int,
    val overallQual: Int,
    val pavedDrive: String,
    val paymentPeriod: String,
    val poolArea: Int,
    val reviews: List<String>,
    val roofMatl: String,
    val roofStyle: String,
    val saleCondition: String,
    val salePrice: Double,
    val saleType: String,
    val status: String,
    val street: String,
    val title: String,
    val totRmsAbvGrd: Int,
    val totalarea: Int,
    val totalbaths: Double,
    val totalporchsf: Int,
    val totalsf: Int,
    val type: String,
    val updatedAt: String,
    val utilities: String
)