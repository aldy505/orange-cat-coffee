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
    @Column("full_name") var fullName: String,
    var gender: Int? = null,
    val birthday: LocalDate? = null,
    @Column("phone_number") var phoneNumber: String? = null,
    @Column("profile_picture") var profilePicture: String? = null,
    var disabled: Boolean,
    @Column("created_at") val createdAt: Instant,
    @Column("created_by") val createdBy: String,
    @Column("updated_at") var updatedAt: Instant,
    @Column("updated_by") var updatedBy: String,
    @Column("deleted_at") val deletedAt: Instant? = null,
    @Column("deleted_by") val deletedBy: String? = null,
)
