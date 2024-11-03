package com.reinaldyrafli.code.orangecatcoffee.repositories.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("orders")
data class Orders(
    @Id val id: Int? = null,
    @Column("customer_id") val customerId: Int,
    @Column("store_id") val storeId: Int,
    val status: Int,
    @Column("order_type") val orderType: Int,
    @Column("delivery_address") val deliveryAddress: String?,
    @Column("delivery_instructions") val deliveryInstructions: String?,
    @Column("delivery_tracking_number") val deliveryTrackingNumber: String?,
    @Column("payment_method") val paymentMethod: Int,
    @Column("payment_instructions") val paymentInstructions: String?,
    @Column("payment_tracking_number") val paymentTrackingNumber: String?,
    @Column("total_items") val totalItems: Int,
    @Column("total_discounts") val totalDiscounts: Int,
    @Column("total_price") val totalPrice: Int,
    val rating: Int?,
    val review: String?,
    @Column("created_at") val createdAt: Instant,
    @Column("created_by") val createdBy: String,
    @Column("updated_at") val updatedAt: Instant,
    @Column("updated_by") val updatedBy: String,
)
