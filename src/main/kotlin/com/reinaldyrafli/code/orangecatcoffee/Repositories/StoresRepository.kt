package com.reinaldyrafli.code.orangecatcoffee.Repositories

import com.reinaldyrafli.code.orangecatcoffee.Repositories.Models.Stores
import org.springframework.data.repository.CrudRepository

interface StoresRepository : CrudRepository<Stores, Int>