package com.reinaldyrafli.code.orangecatcoffee.primitives

data class Catalog(
    val id: Int,
    val name: String,
    val description: String? = null,
    val image: String? = null,
    val disabled: Boolean = false,
)