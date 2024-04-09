package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.niveau.Niveau
import com.example.requestmanager.models.entities.niveau.NiveauRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NiveauService {

    @Autowired
    private lateinit var niveauRepository: NiveauRepository


    fun createNiveau(niveau: Niveau) : RequestResult<Niveau>{

        var niveauToCreate = niveau.copy(niveauId = System.currentTimeMillis().toString(), creationDate = System.currentTimeMillis())

        if(niveauToCreate.name.isBlank()){
            return RequestResult.error(message = "Un niveau doit absolument avoir un nom")
        }

        niveauToCreate = niveauRepository.save(niveauToCreate)

        return RequestResult.success(data = niveauToCreate)

    }


    fun getAllNiveau() : RequestResult<List<Niveau>>{
        return RequestResult.success(data = niveauRepository.findAll())
    }

}