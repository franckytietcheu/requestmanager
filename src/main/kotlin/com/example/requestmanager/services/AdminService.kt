package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.admin.Admin
import com.example.requestmanager.models.entities.admin.AdminRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminService {

    @Autowired
    private lateinit var adminRepository: AdminRepository


    fun createAdmin(admin: Admin) : RequestResult<Admin>{

        val adminToCreate = admin.copy(adminId = System.currentTimeMillis().toString())

        if(adminToCreate.adminNames.isBlank()){
            return RequestResult.error(message = "Un admin doit absolument avoir un nom")
        }

        return RequestResult.success(data = adminRepository.save(adminToCreate))

    }


    fun getAllAdmin() : RequestResult<List<Admin>>{
        return RequestResult.success(data = adminRepository.findAll())
    }

}