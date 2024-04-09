package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.niveau.Niveau
import com.example.requestmanager.services.NiveauService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("niveaux")
class NiveauController {

    @Autowired
    private lateinit var niveauService: NiveauService

    @PostMapping("create")
    fun createNiveau(@RequestBody niveau: Niveau) : RequestResult<Niveau>{
        return niveauService.createNiveau(niveau)
    }

    @GetMapping("all")
    fun getAllNiveaux() : RequestResult<List<Niveau>>{
        return niveauService.getAllNiveau()
    }
}