package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.filiere.Filiere
import com.example.requestmanager.models.entities.filiere.FiliereRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FiliereService {

    @Autowired
    private lateinit var filiereRepository: FiliereRepository


    fun createFiliere(filiere: Filiere) : RequestResult<Filiere>{

        var filiereToCreate = filiere.copy(filiereId = System.currentTimeMillis().toString(), dateCreation = System.currentTimeMillis())

        if(filiere.name.isBlank()){
            return RequestResult.error(message = "Une filière doit absolument avoir un nom")
        }

        filiereToCreate = filiereRepository.save(filiereToCreate)

        return RequestResult.success(data = filiereToCreate)

    }

    fun getAllFiliere() : RequestResult<List<Filiere>>{
        return RequestResult.success(data = filiereRepository.findAll())
    }

}