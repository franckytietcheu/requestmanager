package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.dto.AdminPosteRequestDto
import com.example.requestmanager.models.entities.admin.AdminRepository
import com.example.requestmanager.models.entities.admin_poste.AdminPoste
import com.example.requestmanager.models.entities.admin_poste.AdminPosteRepository
import com.example.requestmanager.models.entities.poste.PosteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminPosteService {

    @Autowired
    private lateinit var adminPosteRepository: AdminPosteRepository

    @Autowired
    private lateinit var posteRepository: PosteRepository

    @Autowired
    private lateinit var adminRepository: AdminRepository

    fun assignPosteToAdmin(adminPosteRequestDto: AdminPosteRequestDto) : RequestResult<AdminPoste>{

        val posteOptionnal = posteRepository.findById(adminPosteRequestDto.posteId)
        if(!posteOptionnal.isPresent){
            return RequestResult.error(message = "le poste d'affectation fourni n'est pas valide")
        }

        val adminOptionnal = adminRepository.findById(adminPosteRequestDto.adminId)
        if(!adminOptionnal.isPresent){
            return RequestResult.error(message = "l'administrateur fourni n'est pas valide")
        }

        val lastAffectationOnPoste = adminPosteRepository.findByPosteAndStillValid(posteOptionnal.get(),true)
        if(lastAffectationOnPoste.isPresent){
            adminPosteRepository.save(lastAffectationOnPoste.get().apply {
                stillValid = false
            })
        }

        val newAffectation = AdminPoste(
            adminPosteId = System.currentTimeMillis().toString(),
            admin = adminOptionnal.get(),
            poste = posteOptionnal.get(),
            dateAffectation = System.currentTimeMillis(),
            stillValid = true
        )
        adminPosteRepository.save(newAffectation)

        return RequestResult.success(data = newAffectation)

    }

}