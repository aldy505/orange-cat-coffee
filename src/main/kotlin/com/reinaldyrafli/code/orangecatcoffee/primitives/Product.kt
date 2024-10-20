package com.reinaldyrafli.code.orangecatcoffee.primitives

import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val price: BigDecimal,
    val description: String? = null,
    val image: String? = null,
    val maximumOrder: Int? = null,
    val disabled: Boolean = false,
    val categoryId: Int? = null,
)