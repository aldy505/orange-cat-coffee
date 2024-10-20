package com.reinaldyrafli.code.orangecatcoffee.repositories.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.LocalDate

@Table("customers")
data class Customers(
    @Id val id: Int? = null,
    val email: String,
    val password: String,
    @Column("full_name") val fullName: String,
    val gender: Int? = null,
    val birthday: LocalDate? = null,
    @Column("phone_number") val phoneNumber: String? = null,
    @Column("profile_picture") val profilePicture: String? = null,
    val disabled: Boolean,
    @Column("created_at") val createdAt: Instant,
    @Column("created_by") val createdBy: String,
    @Column("updated_at") val updatedAt: Instant,
    @Column("updated_by") val updatedBy: String,
    @Column("deleted_at") val deletedAt: Instant,
    @Column("deleted_by") val deletedBy: String,
)
