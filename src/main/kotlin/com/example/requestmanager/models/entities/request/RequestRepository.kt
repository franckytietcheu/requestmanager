package com.example.requestmanager.models.entities.request

import com.example.requestmanager.models.entities.etudiant.Etudiant
import com.example.requestmanager.models.entities.poste.Poste
import org.springframework.data.jpa.repository.JpaRepository

interface RequestRepository : JpaRepository<Request, String>{

    fun findByEtudiant(etudiant: Etudiant) : List<Request>
    fun findByPoste(poste: Poste): List<Request>

}