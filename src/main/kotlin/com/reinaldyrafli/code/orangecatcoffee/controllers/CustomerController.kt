package com.reinaldyrafli.code.orangecatcoffee.controllers

import com.reinaldyrafli.code.orangecatcoffee.controllers.dto.customer.RegisterRequest
import com.reinaldyrafli.code.orangecatcoffee.controllers.dto.customer.RegisterResponse
import com.reinaldyrafli.code.orangecatcoffee.services.CustomerService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/customer")
class CustomerController(
    private val customerService: CustomerService,
) {
    @PostMapping("/register")
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
}