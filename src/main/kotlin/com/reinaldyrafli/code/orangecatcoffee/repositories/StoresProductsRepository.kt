package com.reinaldyrafli.code.orangecatcoffee.repositories

import com.reinaldyrafli.code.orangecatcoffee.repositories.models.StoresProducts
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface StoresProductsRepository : CrudRepository<StoresProducts, Int> {
    @Query("SELECT * FROM stores_products WHERE store_id = :storeId")
    fun findByStoreId(@Param("storeId") storeId: Int): List<StoresProducts>
}