package com.reinaldyrafli.code.orangecatcoffee.Controllers.DTO

data class Store(
    val id: Int,
    val name: String,
    val description: String? = null,
    val image: String? = null,
    val address: String? = null,
    val coordinatesLatitude: Double? = null,
    val coordinatesLongitude: Double? = null,
    val disabled: Boolean? = false,
)
