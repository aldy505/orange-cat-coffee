package com.reinaldyrafli.code.orangecatcoffee.exceptions

enum class InvalidCustomerReason {
    InvalidEmail,
    InvalidPassword,
    CustomerNotFound,
    CustomerDisabled;

    override fun toString(): String {
        return when (this) {
            InvalidEmail -> "Invalid email"
            InvalidPassword -> "Invalid password"
            CustomerNotFound -> "Customer not found"
            CustomerDisabled -> "Customer disabled"
        }
    }
}

class InvalidCustomerException(message: InvalidCustomerReason) : Exception(message.toString()) {
}