package com.reinaldyrafli.code.orangecatcoffee.services

import com.reinaldyrafli.code.orangecatcoffee.exceptions.InvalidCustomerException
import com.reinaldyrafli.code.orangecatcoffee.exceptions.InvalidCustomerReason
import com.reinaldyrafli.code.orangecatcoffee.primitives.AuthorizedCustomer
import com.reinaldyrafli.code.orangecatcoffee.primitives.Customer
import com.reinaldyrafli.code.orangecatcoffee.primitives.CustomerGender
import com.reinaldyrafli.code.orangecatcoffee.repositories.AuthLoginHistoryRepository
import com.reinaldyrafli.code.orangecatcoffee.repositories.AuthSessionRepository
import com.reinaldyrafli.code.orangecatcoffee.repositories.CustomersRepository
import com.reinaldyrafli.code.orangecatcoffee.repositories.EmailRepository
import com.reinaldyrafli.code.orangecatcoffee.repositories.models.AuthLoginHistory
import com.reinaldyrafli.code.orangecatcoffee.repositories.models.AuthSession
import com.reinaldyrafli.code.orangecatcoffee.repositories.models.Customers
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class CustomerService(
    private val customersRepository: CustomersRepository,
    private val authLoginHistoryRepository: AuthLoginHistoryRepository,
    private val authSessionRepository: AuthSessionRepository,
    private val emailRepository: EmailRepository,
) {
    fun register(
        email: String,
        password: String,
        fullName: String,
        ipAddress: String,
        userAgent: String
    ): AuthorizedCustomer {
        if (customersRepository.existsByEmail(email)) throw InvalidCustomerException(InvalidCustomerReason.AlreadyRegistered)

        val passwordEncoder = BCryptPasswordEncoder(16)
        val encodedPassword: String = passwordEncoder.encode(password)
        var customer = Customers(
            null,
            email,
            encodedPassword,
            fullName,
            null,
            null,
            null,
            null,
            false,
            Instant.now(),
            "system",
            Instant.now(),
            "system",
            null,
            null
        )
        customersRepository.save(customer)

        emailRepository.send(
            "no-reply@orange-cat-coffee.com",
            email,
            "Orange Cat Coffee - Welcome!",
            "<!DOCTYPE html><html><body><h1>Welcome to Orange Cat Coffee!</h1><p>Your account has been created successfully.</p></body></html>",
            "Welcome to Orange Cat Coffee!\nYour account has been created successfully.",
        )

        // Re-fetch customer to get the new id
        customer = customersRepository.findByEmail(email).get()
        if (customer.id == null) throw InvalidCustomerException(InvalidCustomerReason.CustomerNotFound)

        val token = UUID.randomUUID().toString()
        val tokenExpiry = Instant.now().plusSeconds(60 * 60 * 24 * 7) // 1 week

        authLoginHistoryRepository.save(AuthLoginHistory(null, customer.id!!, ipAddress, userAgent, Instant.now()))
        authSessionRepository.save(AuthSession(null, customer.id!!, token, tokenExpiry))

        return AuthorizedCustomer(
            Customer(
                customer.id!!,
                customer.email,
                customer.password,
                customer.fullName,
                CustomerGender.fromInt(customer.gender),
                customer.birthday,
                customer.phoneNumber,
                customer.profilePicture,
                customer.disabled
            ),
            token,
            tokenExpiry,
        )
    }

    fun updateProfile(customerId: Int, fullName: String?, phoneNumber: String?, gender: CustomerGender?) {
        val customer = customersRepository.findById(customerId).get()
        if (customer.id == null) throw InvalidCustomerException(InvalidCustomerReason.CustomerNotFound)

        if (fullName != null && customer.fullName != fullName) {
            customer.fullName = fullName
        }

        if (phoneNumber != null && customer.phoneNumber != phoneNumber) {
            customer.phoneNumber = phoneNumber
        }

        if (gender != null && customer.gender != gender.ordinal) {
            customer.gender = gender.ordinal
        }

        customer.updatedAt = Instant.now()
        customer.updatedBy = "system"
        customersRepository.save(customer)
    }
}