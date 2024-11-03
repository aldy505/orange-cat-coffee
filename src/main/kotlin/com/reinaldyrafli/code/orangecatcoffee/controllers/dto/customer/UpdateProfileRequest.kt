package com.reinaldyrafli.code.orangecatcoffee.controllers.dto.customer

import com.reinaldyrafli.code.orangecatcoffee.primitives.CustomerGender

data class UpdateProfileRequest(
    val fullName: String?,
    val phoneNumber: String?,
    val gender: CustomerGender?,
)
