package com.example.requestmanager.controllers

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.fichier.Fichier
import com.example.requestmanager.services.FichierService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("fichiers")
class FichierController {

    @Autowired
    private lateinit var fichierService: FichierService

    @PostMapping("create")
    fun createFichier(
        @RequestParam("file") file : MultipartFile
    ) : RequestResult<Fichier>{
        return fichierService.createFichier(file)
    }

    @GetMapping("all")
    fun getAllFile() : RequestResult<List<Fichier>>{
        return fichierService.getAllFichiers()
    }

}