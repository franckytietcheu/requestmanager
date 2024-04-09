package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.admin.Admin
import com.example.requestmanager.services.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admins")
class AdminController {

    @Autowired
    private lateinit var adminService: AdminService

    @PostMapping("create")
    fun createAdmin(@RequestBody admin: Admin) : RequestResult<Admin>{
        return adminService.createAdmin(admin)
    }

    @GetMapping("all")
    fun getAllAdmin() : RequestResult<List<Admin>>{
        return adminService.getAllAdmin()
    }

}