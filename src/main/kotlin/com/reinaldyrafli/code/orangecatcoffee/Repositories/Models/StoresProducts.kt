package com.reinaldyrafli.code.orangecatcoffee.Repositories.Models

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("stores_products")
data class StoresProducts(
    @Column("store_id") val storeId: Int,
    @Column("product_id") val productId: Int,
)
