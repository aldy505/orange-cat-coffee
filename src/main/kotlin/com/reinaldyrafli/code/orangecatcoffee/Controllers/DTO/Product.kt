package com.reinaldyrafli.code.orangecatcoffee.Controllers.DTO

import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val price: BigDecimal,
    val description: String? = null,
    val image: String? = null,
    val category: String? = null,
    val maximumOrder: Int? = null,
)