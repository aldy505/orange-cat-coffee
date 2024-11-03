package com.reinaldyrafli.code.orangecatcoffee.primitives

enum class OrderType {
    PickUp,
    Delivery;

    companion object {
        fun fromInt(i: Int?): OrderType? {
            return when (i) {
                0 -> PickUp
                1 -> Delivery
                else -> null
            }
        }
    }
}