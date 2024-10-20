package com.reinaldyrafli.code.orangecatcoffee.repositories

import com.reinaldyrafli.code.orangecatcoffee.repositories.models.Catalogs
import org.springframework.data.repository.CrudRepository

interface CatalogsRepository : CrudRepository<Catalogs, Int>