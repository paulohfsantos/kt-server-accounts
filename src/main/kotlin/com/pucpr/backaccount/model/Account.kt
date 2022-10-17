package com.pucpr.backaccount.model

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Account(
    val id: String? = null,
    val name: String,
    val doc: String,
    val balance: Long? = 0
)
