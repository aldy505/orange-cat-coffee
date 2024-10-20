package com.reinaldyrafli.code.orangecatcoffee.repositories

import com.reinaldyrafli.code.orangecatcoffee.repositories.models.Customers
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface CustomersRepository : CrudRepository<Customers, Int> {
    @Query("SELECT * FROM customers WHERE email = :email LIMIT 1")
    fun findByEmail(@Param("email") email: String): Optional<Customers>
}