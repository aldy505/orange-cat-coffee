package com.reinaldyrafli.code.orangecatcoffee.repositories

import com.reinaldyrafli.code.orangecatcoffee.repositories.models.Stores
import org.springframework.data.repository.CrudRepository

interface StoresRepository : CrudRepository<Stores, Int>