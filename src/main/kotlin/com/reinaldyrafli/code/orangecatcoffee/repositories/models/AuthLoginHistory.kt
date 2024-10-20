package com.reinaldyrafli.code.orangecatcoffee.repositories.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("auth_login_history")
data class AuthLoginHistory(
    @Id val id: Int? = null,
    @Column("customer_id") val customerId: Int,
    val ip: String,
    @Column("user_agent") val userAgent: String,
    @Column("login_at") val loginAt: Instant,
)
