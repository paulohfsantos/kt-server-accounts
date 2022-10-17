package com.pucpr.backaccount.controller

import com.pucpr.backaccount.model.Account
import com.pucpr.backaccount.repository.AccountRepo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("accounts")
class AccountController(val repo: AccountRepo) {

    @PostMapping
    fun addAccount(@RequestBody account: Account): ResponseEntity<Account> {
        return ResponseEntity.ok(repo.save(account))
    }

    @GetMapping
    fun listAccounts(): ResponseEntity<MutableList<Account>> {
        return ResponseEntity.ok(repo.findAll())
    }

    @GetMapping("/{id}")
    fun getAccount(@PathVariable id: String): ResponseEntity<Account> {
        return ResponseEntity.ok(repo.findById(id).orElse(null))
    }

    @PutMapping("{doc}")
    fun updateAccount(@PathVariable document: String, @RequestBody account: Account): ResponseEntity<Account> {
        val findDoc = repo.findByDoc(document)
        val accountDatabase = findDoc.orElseThrow {
            RuntimeException("Account doc: $document not found")
        }

        val onSave = repo.save(accountDatabase.copy(name = account.name, balance = account.balance))

        return ResponseEntity.ok(onSave)

    }

    @DeleteMapping("{id}")
    fun deleteAccount(@PathVariable id: String): ResponseEntity<String> {
        repo.deleteById(id)
        return ResponseEntity.ok(id)
    }

}