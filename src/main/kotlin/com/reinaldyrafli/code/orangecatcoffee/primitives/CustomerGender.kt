package com.reinaldyrafli.code.orangecatcoffee.primitives

enum class CustomerGender {
    Male,
    Female,
    Others;

    companion object {
        fun fromInt(i: Int?): CustomerGender? {
            return when (i) {
                0 -> Male
                1 -> Female
                null -> null
                else -> Others
            }
        }
    }
}