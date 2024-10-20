package com.reinaldyrafli.code.orangecatcoffee.repositories.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("stores_products")
data class StoresProducts(
    @Id val id: Int? = null,
    @Column("store_id") val storeId: Int,
    @Column("product_id") val productId: Int,
)
