package com.reinaldyrafli.code.orangecatcoffee.Controllers.DTO

data class Inventory(
    val store: Store,
    val products: List<Product>
)