package com.reinaldyrafli.code.orangecatcoffee.controllers

import com.reinaldyrafli.code.orangecatcoffee.controllers.dto.CommonErrorResponse
import com.reinaldyrafli.code.orangecatcoffee.controllers.dto.customer.RegisterRequest
import com.reinaldyrafli.code.orangecatcoffee.controllers.dto.customer.RegisterResponse
import com.reinaldyrafli.code.orangecatcoffee.exceptions.InvalidCustomerException
import com.reinaldyrafli.code.orangecatcoffee.exceptions.InvalidCustomerReason
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