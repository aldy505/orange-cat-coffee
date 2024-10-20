package com.reinaldyrafli.code.orangecatcoffee.repositories

import com.reinaldyrafli.code.orangecatcoffee.repositories.models.AuthLoginHistory
import org.springframework.data.repository.CrudRepository

interface AuthLoginHistoryRepository : CrudRepository<AuthLoginHistory, Int> {
}