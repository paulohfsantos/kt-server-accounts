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

    @GetMapping("/{doc}")
    fun getAccount(@PathVariable doc: String): ResponseEntity<Account> {
        return ResponseEntity.ok(repo.findByDoc(doc).orElse(null))
    }

    @PutMapping("/{id}")
    fun updateAccount(@PathVariable id: String, @RequestBody account: Account): ResponseEntity<Account> {
        val user = repo.findById(id).orElse(null)
        user.name = account.name
        user.balance = account.balance
        user.doc = account.doc

        return ResponseEntity.ok(repo.save(user))
    }

    @DeleteMapping("/{doc}")
    fun deleteAccount(@PathVariable doc: String) {
        val user = repo.findByDoc(doc).ifPresent {
            repo.delete(it)
        }

        return user
    }

}