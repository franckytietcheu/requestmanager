package com.example.requestmanager.services

import com.example.requestmanager.helper.RequestResult
import com.example.requestmanager.models.entities.etudiant.Etudiant
import com.example.requestmanager.models.entities.etudiant.EtudiantRepository
import com.example.requestmanager.models.entities.filiere.FiliereRepository
import com.example.requestmanager.models.entities.niveau.NiveauRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EtudiantService {

    @Autowired
    private lateinit var etudiantRepository: EtudiantRepository

    @Autowired
    private lateinit var niveauRepository: NiveauRepository

    @Autowired
    private lateinit var filiereRepository: FiliereRepository

    fun createEtudiant(etudiant: Etudiant) : RequestResult<Etudiant>{

        var etudiantToCreate = etudiant.copy(studentId = System.currentTimeMillis().toString())

        val filiereId = etudiant.filiere.filiereId
        if(filiereId.isBlank() || !filiereRepository.findById(filiereId).isPresent){
            return RequestResult.error(message = "Vous devez fournir une filière valide pour cet étudiant")
        }

        val niveauId = etudiant.niveau.niveauId
        if(niveauId.isBlank() || !niveauRepository.findById(niveauId).isPresent){
            return RequestResult.error(message = "Vous devez fournir un niveau valide pour cet étudiant")
        }

        if(etudiant.studentNames.isBlank()){
            return RequestResult.error(message = "un étudiant doit avoir un nom")
        }

        if(etudiant.studentPseudo.isBlank()){
            return RequestResult.error(message = "Veuillez fournir le pseudo de cet étudiant")
        }

        etudiantToCreate = etudiantRepository.save(etudiantToCreate.apply {
            password = studentPseudo
        })

        return RequestResult.success(etudiantToCreate)
    }

    fun getAllStudent() : RequestResult<List<Etudiant>>{
        return RequestResult.success(data = etudiantRepository.findAll())
    }

}