package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.dto.UserResponseDto
import com.example.requestmanager.models.entities.etudiant.Etudiant
import com.example.requestmanager.services.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController {

    @Autowired
    private lateinit var accountService: AccountService

    @PostMapping("login")
    fun login(
        @RequestParam login : String,
        @RequestParam password : String) : RequestResult<UserResponseDto> {
        return accountService.login(login, password)
    }
}