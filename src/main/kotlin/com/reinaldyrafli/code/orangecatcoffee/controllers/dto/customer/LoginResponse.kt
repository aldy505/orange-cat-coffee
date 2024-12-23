package com.reinaldyrafli.code.orangecatcoffee.controllers.dto.customer

import java.time.Instant

data class LoginResponse(
    val token: String,
    val expiresAt: Instant,
)
