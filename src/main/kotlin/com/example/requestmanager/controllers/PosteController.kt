package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.poste.Poste
import com.example.requestmanager.services.PosteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("postes")
class PosteController {

    @Autowired
    private lateinit var posteService: PosteService

    @PostMapping("create")
    fun createPoste(@RequestBody poste: Poste) : RequestResult<Poste>{
        return posteService.createPoste(poste)
    }

    @GetMapping("all")
    fun getAllPoste() : RequestResult<List<Poste>>{
        return posteService.getAllPoste()
    }


}