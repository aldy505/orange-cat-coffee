package com.reinaldyrafli.code.orangecatcoffee.primitives

import java.time.Instant

data class AuthorizedCustomer(
    val customer: Customer,
    val token: String,
    val expiresAt: Instant,
)
