package com.reinaldyrafli.code.orangecatcoffee.exceptions

enum class InvalidCustomerReason {
    InvalidEmail,
    InvalidPassword,
    CustomerNotFound,
    CustomerDisabled,
    AlreadyRegistered;

    override fun toString(): String {
        return when (this) {
            InvalidEmail -> "Invalid email"
            InvalidPassword -> "Invalid password"
            CustomerNotFound -> "Customer not found"
            CustomerDisabled -> "Customer disabled"
            AlreadyRegistered -> "Already registered"
        }
    }
}

class InvalidCustomerException(val reason: InvalidCustomerReason) : Exception(reason.toString()) {
}