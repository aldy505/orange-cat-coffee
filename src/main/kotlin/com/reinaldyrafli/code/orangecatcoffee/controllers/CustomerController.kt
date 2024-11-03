package com.reinaldyrafli.code.orangecatcoffee.controllers

import com.reinaldyrafli.code.orangecatcoffee.controllers.dto.common.CommonErrorResponse
import com.reinaldyrafli.code.orangecatcoffee.controllers.dto.customer.*
import com.reinaldyrafli.code.orangecatcoffee.exceptions.InvalidCustomerException
import com.reinaldyrafli.code.orangecatcoffee.exceptions.InvalidCustomerReason
import com.reinaldyrafli.code.orangecatcoffee.services.CustomerAuthenticationService
import com.reinaldyrafli.code.orangecatcoffee.services.CustomerService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/customer")
class CustomerController(
    private val customerService: CustomerService,
    private val customerAuthenticationService: CustomerAuthenticationService,
) {
    @PostMapping("/register", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun register(
        @RequestBody request: RegisterRequest,
        @RequestHeader(value = "User-Agent") userAgent: String,
        rawRequest: HttpServletRequest
    ): RegisterResponse {
        val ipAddress = rawRequest.getHeader("X-Forwarded-For") ?: rawRequest.remoteAddr
        val authorizedCustomer =
            customerService.register(request.email, request.password, request.fullName, ipAddress, userAgent)

        return RegisterResponse(authorizedCustomer.token, authorizedCustomer.expiresAt)
    }

    @PostMapping("/login", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun login(
        @RequestBody request: LoginRequest,
        @RequestHeader(value = "User-Agent") userAgent: String,
        rawRequest: HttpServletRequest
    ): LoginResponse {
        val ipAddress = rawRequest.getHeader("X-Forwarded-For") ?: rawRequest.remoteAddr
        val authorizedCustomer =
            customerAuthenticationService.login(request.email, request.password, ipAddress, userAgent)

        return LoginResponse(authorizedCustomer.token, authorizedCustomer.expiresAt)
    }

    @GetMapping("/profile", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProfile(
        @RequestHeader(value = "Authorization") token: String
    ): ProfileResponse {
        val customer = customerAuthenticationService.validateAccessToken(token)
            ?: throw InvalidCustomerException(InvalidCustomerReason.CustomerNotFound)
        if (customer.disabled) throw InvalidCustomerException(InvalidCustomerReason.CustomerDisabled)

        return ProfileResponse(
            customer.email,
            customer.fullName,
            customer.gender,
            customer.birthday,
            customer.phoneNumber,
            customer.profilePicture
        )
    }

    @PutMapping("/profile", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateProfile(
        @RequestHeader(value = "Authorization") token: String,
        @RequestBody request: UpdateProfileRequest,
    ): ProfileResponse {
        val customer = customerAuthenticationService.validateAccessToken(token)
            ?: throw InvalidCustomerException(InvalidCustomerReason.CustomerNotFound)
        if (customer.disabled) throw InvalidCustomerException(InvalidCustomerReason.CustomerDisabled)

        customerService.updateProfile(customer.id, request.fullName, request.phoneNumber, request.gender)

        val updatedCustomer = customerAuthenticationService.validateAccessToken(token)
            ?: throw InvalidCustomerException(InvalidCustomerReason.CustomerNotFound)
        if (updatedCustomer.disabled) throw InvalidCustomerException(InvalidCustomerReason.CustomerDisabled)
        return ProfileResponse(
            updatedCustomer.email,
            updatedCustomer.fullName,
            updatedCustomer.gender,
            updatedCustomer.birthday,
            updatedCustomer.phoneNumber,
            updatedCustomer.profilePicture
        )
    }

    @ExceptionHandler(value = [InvalidCustomerException::class])
    fun handleInvalidCustomerException(exception: InvalidCustomerException): ResponseEntity<CommonErrorResponse> {
        return when (exception.reason) {
            InvalidCustomerReason.InvalidEmail -> ResponseEntity(CommonErrorResponse("Invalid email or password"), HttpStatus.BAD_REQUEST)
            InvalidCustomerReason.InvalidPassword -> ResponseEntity(CommonErrorResponse("Invalid email or password"), HttpStatus.BAD_REQUEST)
            InvalidCustomerReason.CustomerNotFound -> ResponseEntity(CommonErrorResponse("Customer not found"), HttpStatus.BAD_REQUEST)
            InvalidCustomerReason.CustomerDisabled -> ResponseEntity(CommonErrorResponse("Customer is disabled"), HttpStatus.BAD_REQUEST)
            InvalidCustomerReason.AlreadyRegistered -> ResponseEntity(CommonErrorResponse("Customer has already registered"), HttpStatus.BAD_REQUEST)
        }
    }
}