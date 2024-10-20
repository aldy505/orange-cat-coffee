package com.reinaldyrafli.code.orangecatcoffee.primitives

import java.time.LocalDate

data class Customer(
    val id: Int,
    val email: String,
    val password: String,
    val fullName: String,
    val gender: CustomerGender? = null,
    val birthday: LocalDate? = null,
    val phoneNumber: String? = null,
    val profilePicture: String? = null,
    val disabled: Boolean = false,
)
