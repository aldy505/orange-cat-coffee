package com.reinaldyrafli.code.orangecatcoffee.Repositories

import com.reinaldyrafli.code.orangecatcoffee.Repositories.Models.Products
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Products, Int>