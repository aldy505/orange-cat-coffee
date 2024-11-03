package com.reinaldyrafli.code.orangecatcoffee.repositories

import com.reinaldyrafli.code.orangecatcoffee.repositories.models.Orders
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface OrdersRepository : CrudRepository<Orders, Int> {
    @Query("SELECT * FROM orders WHERE customer_id = :customerId")
    fun findByCustomerId(@Param("customerId") customerId: Int): List<Orders>
}