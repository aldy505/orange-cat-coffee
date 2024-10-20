package com.reinaldyrafli.code.orangecatcoffee.repositories.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("stores")
data class Stores(
    @Id val id: Int? = null,
    val name: String,
    val description: String? = null,
    val image: String? = null,
    val address: String? = null,
    @Column("coordinates_latitude") val coordinatesLatitude: Double? = null,
    @Column("coordinates_longitude") val coordinatesLongitude: Double? = null,
    val disabled: Boolean,
    @Column("created_at") val createdAt: Instant,
    @Column("created_by") val createdBy: String,
    @Column("updated_at") val updatedAt: Instant,
    @Column("updated_by") val updatedBy: String,
)