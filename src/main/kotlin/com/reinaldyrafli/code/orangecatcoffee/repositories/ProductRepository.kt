package com.reinaldyrafli.code.orangecatcoffee.repositories

import com.reinaldyrafli.code.orangecatcoffee.repositories.models.Products
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Products, Int>