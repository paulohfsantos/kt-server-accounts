package com.pucpr.backaccount.model

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Account(
    val id: String? = null,
    var name: String,
    var doc: String,
    var balance: Long? = 0
)
