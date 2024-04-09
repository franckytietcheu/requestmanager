package com.example.requestmanager.models.entities.etudiant

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EtudiantRepository : JpaRepository<Etudiant, String>{

    fun findByStudentPseudoAndPassword(studentPseudo : String, password : String) : Optional<Etudiant>

}