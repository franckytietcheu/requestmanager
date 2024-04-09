package com.example.requestmanager.models.dto

import com.example.requestmanager.models.entities.admin.Admin
import com.example.requestmanager.models.entities.poste.Poste

data class AdminResponseDto(
    val adminId : String = "",
    var adminNames : String = "",
    var emailAddress : String = "",
    var phoneNumber : String = "",
    var postes : List<Poste> = listOf()
) {

    companion object {
        fun fromAdmin(admin: Admin) : AdminResponseDto{
            return AdminResponseDto(
                adminId = admin.adminId,
                adminNames = admin.adminNames,
                emailAddress = admin.emailAddress,
                phoneNumber = admin.phoneNumber,
            )
        }
    }

}