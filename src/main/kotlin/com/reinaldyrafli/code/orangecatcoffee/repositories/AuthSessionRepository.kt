package com.reinaldyrafli.code.orangecatcoffee.repositories

import com.reinaldyrafli.code.orangecatcoffee.repositories.models.AuthSession
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AuthSessionRepository : CrudRepository<AuthSession, Int> {
    @Query("SELECT * FROM auth_session WHERE token = :token AND expires_at > NOW() LIMIT 1")
    fun findByToken(token: String): Optional<AuthSession>
}