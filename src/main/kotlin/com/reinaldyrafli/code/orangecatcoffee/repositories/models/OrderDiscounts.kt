package com.reinaldyrafli.code.orangecatcoffee.repositories.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.Instant

@Table("order_discounts")
data class OrderDiscounts(
    @Id val id: Int? = null,
    @Column("order_id") val orderId: Int,
    @Column("product_id") val productId: Int,
    @Column("voucher_code") val voucherCode: String?,
    @Column("discount_amount") val discountAmount: BigDecimal,
    @Column("discount_percentage") val discountPercentage: Double,
    @Column("created_at") val createdAt: Instant,
    @Column("created_by") val createdBy: String,
    @Column("updated_at") val updatedAt: Instant,
    @Column("updated_by") val updatedBy: String,
)
