package com.reinaldyrafli.code.orangecatcoffee.controllers.dto.customer

data class RegisterRequest(
    val email: String,
    val password: String,
    val fullName: String,
)