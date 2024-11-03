package com.reinaldyrafli.code.orangecatcoffee.controllers.dto.customer

import com.reinaldyrafli.code.orangecatcoffee.primitives.CustomerGender
import java.time.LocalDate

data class ProfileResponse(
    val email: String,
    val fullName: String,
    val gender: CustomerGender?,
    val birthday: LocalDate?,
    val phoneNumber: String?,
    val profilePicture: String?,
)
