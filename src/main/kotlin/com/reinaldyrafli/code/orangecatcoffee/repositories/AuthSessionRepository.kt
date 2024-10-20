package com.reinaldyrafli.code.orangecatcoffee.repositories

import com.reinaldyrafli.code.orangecatcoffee.repositories.models.AuthSession
import org.springframework.data.repository.CrudRepository

interface AuthSessionRepository : CrudRepository<AuthSession, Int> {
}