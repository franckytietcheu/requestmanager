package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.dto.AdminResponseDto
import com.example.requestmanager.models.dto.UserResponseDto
import com.example.requestmanager.models.entities.admin.Admin
import com.example.requestmanager.models.entities.admin.AdminRepository
import com.example.requestmanager.models.entities.admin_poste.AdminPosteRepository
import com.example.requestmanager.models.entities.etudiant.EtudiantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountService {

    @Autowired
    private lateinit var etudiantRepository : EtudiantRepository

    @Autowired
    private lateinit var adminRepository: AdminRepository

    @Autowired
    private lateinit var adminPosteRepository: AdminPosteRepository


    fun login(login : String, password : String) : RequestResult<UserResponseDto> {
        val etudiantOptionnal = etudiantRepository.findByStudentPseudoAndPassword(login,password)
        return if(etudiantOptionnal.isPresent){
            RequestResult.success(data = UserResponseDto.fromEtudiant( etudiantOptionnal.get()))
        }else{
            val adminOptionnal = adminRepository.findByEmailAddressAndPassword(login,password)
            if(adminOptionnal.isPresent){
                val adminResponseDto = buildAdminResponse(adminOptionnal.get())
                RequestResult.success(data = UserResponseDto.fromAdmin(adminResponseDto))
            }else {
                RequestResult.error(message = "Pseudo ou mot de passe incorrect")
            }
        }
    }

    private fun buildAdminResponse(admin: Admin): AdminResponseDto {
        val postes = adminPosteRepository.findByAdminAndStillValid(admin = admin, stillValid = true)
        return AdminResponseDto.fromAdmin(admin).apply {
            this.postes = postes.map { it.poste }
        }
    }

}