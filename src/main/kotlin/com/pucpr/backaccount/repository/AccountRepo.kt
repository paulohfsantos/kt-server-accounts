package com.pucpr.backaccount.repository

import com.pucpr.backaccount.model.Account
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AccountRepo: MongoRepository<Account, String> {
    fun findByDoc(document: String): Optional<Account>
}