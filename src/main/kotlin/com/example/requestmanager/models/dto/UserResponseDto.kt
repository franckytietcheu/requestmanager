package com.example.requestmanager.models.dto

import com.example.requestmanager.models.entities.etudiant.Etudiant
import com.example.requestmanager.models.entities.filiere.Filiere
import com.example.requestmanager.models.entities.niveau.Niveau
import com.example.requestmanager.models.entities.poste.Poste

enum class UserType {
    STUDENT, ADMIN
}

data class UserResponseDto(
    val names : String,
    val type : UserType,
    val id : String,
    val filiere : Filiere? = null,
    val niveau: Niveau? = null,
    val poste : List<Poste>? = null,
    val email : String
){

    companion object {

        fun fromEtudiant(etudiant: Etudiant) : UserResponseDto {
            return UserResponseDto(
                names = etudiant.studentNames,
                type = UserType.STUDENT,
                id = etudiant.studentId,
                filiere = etudiant.filiere,
                niveau = etudiant.niveau,
                poste = null,
                email = etudiant.email
            )
        }

        fun fromAdmin(admin: AdminResponseDto) : UserResponseDto {
            return UserResponseDto(
                names = admin.adminNames,
                type = UserType.ADMIN,
                id = admin.adminId,
                filiere = null,
                niveau = null,
                poste = admin.postes,
                email = admin.emailAddress
            )
        }

    }

}
