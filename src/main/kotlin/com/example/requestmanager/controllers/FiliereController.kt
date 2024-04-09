package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.filiere.Filiere
import com.example.requestmanager.services.FiliereService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("filieres")
class FiliereController {

    @Autowired
    private lateinit var filiereService: FiliereService

    @PostMapping("create")
    fun createFiliere(@RequestBody filiere: Filiere) : RequestResult<Filiere>{
        return filiereService.createFiliere(filiere)
    }

    @GetMapping("all")
    fun getAllFiliere() : RequestResult<List<Filiere>>{
        return filiereService.getAllFiliere()
    }
}