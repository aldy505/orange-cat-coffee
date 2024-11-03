package com.reinaldyrafli.code.orangecatcoffee.repositories.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.Instant

@Table("order_items")
data class OrderItems(
    @Id val id: Int? = null,
    @Column("order_id") val orderId: Int,
    @Column("product_id") val productId: Int,
    @Column("product_name") val productName: String,
    @Column("product_description") val productDescription: String?,
    @Column("product_image") val productImage: String?,
    @Column("product_category_id") val productCategoryId: Int?,
    val quantity: Int,
    val price: BigDecimal,
    @Column("created_at") val createdAt: Instant,
    @Column("created_by") val createdBy: String,
    @Column("updated_at") val updatedAt: Instant,
    @Column("updated_by") val updatedBy: String,
)
