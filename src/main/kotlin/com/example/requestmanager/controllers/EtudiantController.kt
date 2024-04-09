package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.etudiant.Etudiant
import com.example.requestmanager.services.EtudiantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("etudiants")
class EtudiantController {

    @Autowired
    private lateinit var etudiantService: EtudiantService

    @PostMapping("create")
    fun createEtudiant(@RequestBody etudiant: Etudiant) : RequestResult<Etudiant>{
        return etudiantService.createEtudiant(etudiant)
    }

    @GetMapping("all")
    fun getAllEtudiant() : RequestResult<List<Etudiant>>{
        return etudiantService.getAllStudent()
    }

}