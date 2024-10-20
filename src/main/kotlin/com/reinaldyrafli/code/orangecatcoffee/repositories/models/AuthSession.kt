package com.reinaldyrafli.code.orangecatcoffee.repositories.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("auth_session")
data class AuthSession(
    @Id val id: Int? = null,
    @Column("customer_id") val customerId: Int,
    val token: String,
    @Column("expires_at") val expiresAt: Instant,
)
