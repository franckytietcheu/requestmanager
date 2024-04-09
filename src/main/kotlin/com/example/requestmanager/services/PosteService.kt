package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.poste.Poste
import com.example.requestmanager.models.entities.poste.PosteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PosteService {

    @Autowired
    private lateinit var posteRepository: PosteRepository

    fun createPoste(poste: Poste) : RequestResult<Poste>{

        val posteToCreate = poste.copy(posteId = System.currentTimeMillis().toString(), posteCreationDate = System.currentTimeMillis())

        if(posteToCreate.posteName.isBlank()){
            return RequestResult.error(message = "Un poste doit absolument avoir un nom")
        }

        return RequestResult.success(data = posteRepository.save(posteToCreate))

    }

    fun getAllPoste() : RequestResult<List<Poste>>{
        return RequestResult.success(posteRepository.findAll())
    }

}