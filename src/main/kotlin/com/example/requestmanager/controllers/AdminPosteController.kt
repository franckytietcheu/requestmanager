package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.dto.AdminPosteRequestDto
import com.example.requestmanager.models.entities.admin_poste.AdminPoste
import com.example.requestmanager.services.AdminPosteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("adminPoste")
class AdminPosteController {

    @Autowired
    private lateinit var adminPosteService: AdminPosteService

    @PostMapping("assign")
    fun assignPosteToAdmin(
        @RequestBody adminPosteRequestDto: AdminPosteRequestDto
    ) : RequestResult<AdminPoste> {
        return adminPosteService.assignPosteToAdmin(adminPosteRequestDto)
    }

}