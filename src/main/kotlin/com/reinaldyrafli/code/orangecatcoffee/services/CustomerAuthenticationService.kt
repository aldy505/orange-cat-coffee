package com.reinaldyrafli.code.orangecatcoffee.services

import com.reinaldyrafli.code.orangecatcoffee.exceptions.InvalidCustomerException
import com.reinaldyrafli.code.orangecatcoffee.exceptions.InvalidCustomerReason
import com.reinaldyrafli.code.orangecatcoffee.primitives.AuthorizedCustomer
import com.reinaldyrafli.code.orangecatcoffee.primitives.Customer
import com.reinaldyrafli.code.orangecatcoffee.primitives.CustomerGender
import com.reinaldyrafli.code.orangecatcoffee.repositories.AuthLoginHistoryRepository
import com.reinaldyrafli.code.orangecatcoffee.repositories.AuthSessionRepository
import com.reinaldyrafli.code.orangecatcoffee.repositories.CustomersRepository
import com.reinaldyrafli.code.orangecatcoffee.repositories.models.AuthLoginHistory
import com.reinaldyrafli.code.orangecatcoffee.repositories.models.AuthSession
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class CustomerAuthenticationService(
    private val customersRepository: CustomersRepository,
    private val authLoginHistoryRepository: AuthLoginHistoryRepository,
    private val authSessionRepository: AuthSessionRepository
) {
    fun login(email: String, password: String, ip: String, userAgent: String): AuthorizedCustomer {
        val customer = customersRepository.findByEmail(email).get()
        // Make sure `customer.id` is not null when we're accessing it.
        if (customer.id == null) throw InvalidCustomerException(InvalidCustomerReason.CustomerNotFound)

        // Validate password
        val passwordEncoder = BCryptPasswordEncoder(16)
        val encodedPassword: Boolean = passwordEncoder.matches(password, customer.password)
        if (!encodedPassword) throw InvalidCustomerException(InvalidCustomerReason.InvalidPassword)

        // Password validated, create a new token
        val token = UUID.randomUUID().toString()
        val tokenExpiry = Instant.now().plusSeconds(60 * 60 * 24 * 7) // 1 week

        authLoginHistoryRepository.save(AuthLoginHistory(null, customer.id, ip, userAgent, Instant.now()))
        authSessionRepository.save(AuthSession(null, customer.id, token, tokenExpiry))

        return AuthorizedCustomer(
            Customer(
                customer.id,
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

    fun validateAccessToken(token: String): Customer? {
        val authSession = authSessionRepository.findByToken(token).get()
        if (authSession.id == null) return null

        val expiresAt = authSession.expiresAt
        if (expiresAt.isBefore(Instant.now())) return null

        val customer = customersRepository.findById(authSession.customerId).get()
        if (customer.id == null) return null

        return Customer(
            customer.id,
            customer.email,
            customer.password,
            customer.fullName,
            CustomerGender.fromInt(customer.gender),
            customer.birthday,
            customer.phoneNumber,
            customer.profilePicture,
            customer.disabled
        )
    }
}