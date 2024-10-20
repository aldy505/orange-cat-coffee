package com.reinaldyrafli.code.orangecatcoffee.repositories.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.Instant

@Table("products")
data class Products(
    @Id val id: Int? = null,
    val name: String,
    val price: BigDecimal,
    val description: String? = null,
    val image: String? = null,
    @Column("maximum_order") val maximumOrder: Int? = null,
    val disabled: Boolean,
    @Column("created_at") val createdAt: Instant,
    @Column("created_by") val createdBy: String,
    @Column("updated_at") val updatedAt: Instant,
    @Column("updated_by") val updatedBy: String,
    val catalogId: Int,
)
